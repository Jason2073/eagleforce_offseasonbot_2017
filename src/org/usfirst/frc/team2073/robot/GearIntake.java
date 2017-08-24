package org.usfirst.frc.team2073.robot;

import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.List;

import com.ctre.CANTalon;
//import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;
import com.eagleforce.robot.model.MotionProfileConfiguration;
import com.eagleforce.robot.service.MotionProfileGenerationService;

public class GearIntake {
	// can i get a uhhhhhhhhhhhhhhhhhhhhh... cadd for this
	MotionProfileGenerationService mps = new MotionProfileGenerationService();
	MotionProfileConfiguration conf1;
	MotionProfileConfiguration conf2;
	MotionProfileConfiguration conf3;
	MotionProfileConfiguration conf4;
	MotionProfileConfiguration conf5;
	MotionProfileConfiguration conf6;

	public List<TrajectoryPoint> upToDownTpList;
	public List<TrajectoryPoint> upToPlaceTpList;
	public List<TrajectoryPoint> placeToUpTpList;
	public List<TrajectoryPoint> placeToDownTpList;
	public List<TrajectoryPoint> downToPlaceTpList;
	public List<TrajectoryPoint> downToUpTpList;

	Victor motor = new Victor(9);
	Joystick controller = new Joystick(2);
	CANTalon talon = new CANTalon(1);
	MotionProfile gearDown = new MotionProfile(talon);
	double pos;
	double p = .999;
	double i = 0;
	double d = .001;
	// Ticks per 1 rev of encoder
	double encT = 1024;
	double down = encT / 4;
	double place = encT / 8;

	public GearIntake() {

		upToDownTpList = mps.generatePoints(conf1);
		upToPlaceTpList = mps.generatePoints(conf2);
		placeToUpTpList = mps.generatePoints(conf3);
		placeToDownTpList = mps.generatePoints(conf4);
		downToPlaceTpList = mps.generatePoints(conf5);
		downToUpTpList = mps.generatePoints(conf6);

		conf1 = new MotionProfileConfiguration();
		// up to down
		conf1.setMaxVel(3);
		conf1.setInterval(5);
		conf1.setEndDistance(.25);
		conf1.setMaxAcc(60);

		conf2 = new MotionProfileConfiguration();
		// up to place
		conf2.setMaxVel(3);
		conf2.setInterval(5);
		conf2.setEndDistance(.125);
		conf2.setMaxAcc(60);

		conf3 = new MotionProfileConfiguration();
		// place to up (reverse)
		conf3.setMaxVel(3);
		conf3.setInterval(5);
		conf3.setEndDistance(.125);
		conf3.setMaxAcc(60);

		conf4 = new MotionProfileConfiguration();
		// place to down
		conf4.setMaxVel(3);
		conf4.setInterval(5);
		conf4.setEndDistance(.125);
		conf4.setMaxAcc(60);

		conf5 = new MotionProfileConfiguration();
		// down to place (reverse)
		conf5.setMaxVel(3);
		conf5.setInterval(5);
		conf5.setEndDistance(.125);
		conf5.setMaxAcc(60);

		conf6 = new MotionProfileConfiguration();
		// down to up (reverse)
		conf6.setMaxVel(3);
		conf6.setInterval(5);
		conf6.setEndDistance(.25);
		conf6.setMaxAcc(60);

	}

	/*
	 * public void GearPrep(){ // double p = SmartDashboard.getNumber("Gear P",
	 * 0), i = SmartDashboard.getNumber("Gear I", 0), d =
	 * SmartDashboard.getNumber("Gear D", 0); int exact =
	 * talon.getPulseWidthPosition() & 0xFFF; talon.setEncPosition(exact);
	 * SmartDashboard.putNumber("Position", pos);
	 * SmartDashboard.putNumber("ERR", 4); talon.reverseSensor(true);
	 * talon.configEncoderCodesPerRev(1024);
	 * talon.configNominalOutputVoltage(+0f, -0f);
	 * talon.configPeakOutputVoltage(+5f, -5f);
	 * talon.setAllowableClosedLoopErr(4); talon.setP(p); talon.setI(i);
	 * talon.setD(d); talon.setProfile(0);
	 * 
	 * }
	 */
	public void GearIn() {
		motor.set(-1);
	}

	public void GearOut() {
		motor.set(1);
	}

	public void GearHold() {
		motor.set(0);
	}

	Thread Gear = new Thread() {
		public void run() {
			// GearPrep();
			if (RobotState.isAutonomous()) {
				// AUTON
			} else {

				if (controller.getRawButton(2)) {
					GearIn();
				} else if (controller.getRawButton(4)) {
					GearOut();
				} else {
					GearHold();
				}
				if (controller.getPOV() == 90) {
					gearDown.startFilling(upToDownTpList);
				}
				/*
				 * if (controller.getPOV() == 0){
				 * talon.changeControlMode(TalonControlMode.Position);
				 * talon.set(0); }else if (controller.getPOV() == 90){
				 * talon.changeControlMode(TalonControlMode.Position); // TODO
				 * how to find talon position set values talon.set(down); }else
				 * if (controller.getPOV() == 45){
				 * talon.changeControlMode(TalonControlMode.Position);
				 * talon.set(place); }
				 */
			}
			Timer.delay(.005);
		}
	};
}
