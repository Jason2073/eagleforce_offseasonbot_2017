package org.usfirst.frc.team2073.robot.subsys;

import java.util.List;

import org.usfirst.frc.team2073.robot.svc.DPadPosition;
import org.usfirst.frc.team2073.robot.svc.MotionProfileService;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import old.com.eagleforce.robot.model.MotionProfileConfiguration;
import old.com.eagleforce.robot.service.MotionProfileGenerationService;

public class GearIntakeSubsystem extends Subsystem {
	private Victor intakeMotor = new Victor(9);
	private CANTalon talon = new CANTalon(0);
	private DigitalInput lightSensor = new DigitalInput(2);
	private DigitalInput magnetZeroer = new DigitalInput(1);
	private MotionProfileService mpGear = new MotionProfileService(talon);

	private MotionProfileGenerationService mpgSvc = new MotionProfileGenerationService();
	private MotionProfileConfiguration conf1;
	private MotionProfileConfiguration conf2;
	private MotionProfileConfiguration conf3;
	private MotionProfileConfiguration conf4;
	private MotionProfileConfiguration conf5;
	private MotionProfileConfiguration conf6;

	private List<TrajectoryPoint> upToDownTpList;
	private List<TrajectoryPoint> upToPlaceTpList;
	private List<TrajectoryPoint> placeToUpTpList;
	private List<TrajectoryPoint> placeToDownTpList;
	private List<TrajectoryPoint> downToPlaceTpList;
	private List<TrajectoryPoint> downToUpTpList;

	public void gearIn() {
		intakeMotor.set(-1);
	}

	public void gearOut() {
		intakeMotor.set(1);
	}

	public void gearHold() {
		intakeMotor.set(.1);
	}

	public void gearStop() {
		intakeMotor.set(0);
	}

	public double getApproxAngle() {
		double angle;
		angle = Math.round(talon.getPosition());
		return angle;
	}

	public boolean lightSensor() {
		boolean sense;
		sense = lightSensor.get();
		return sense;
	}

	public void resetGearIntake() {
		talon.changeControlMode(TalonControlMode.PercentVbus);
		talon.set(-.5);
	}

	public boolean isZero() {
		return magnetZeroer.get();
	}

	public void upToDown() {
		mpGear.generalRun(upToDownTpList, true);
	}

	public void upToPlace() {
		mpGear.generalRun(upToPlaceTpList, true);
	}

	public void placeToDown() {
		mpGear.generalRun(placeToDownTpList, true);
	}

	public void placeToUp() {
		mpGear.generalRun(placeToUpTpList, false);
	}

	public void downToUp() {
		mpGear.generalRun(downToUpTpList, false);
	}

	public void downToPlace() {
		mpGear.generalRun(downToPlaceTpList, false);
	}
	
	public void toDown(int input) {
		if (-5 < getApproxAngle()  && getApproxAngle() < 5)
			upToDown();
		if (40 < getApproxAngle()  && getApproxAngle() < 50)
			placeToDown();
	}

	public void toPlace(int input) {
		if (-5 < getApproxAngle()  && getApproxAngle() < 5)
			upToPlace();
		if (85 < getApproxAngle()  && getApproxAngle() < 95)
			downToPlace();
	}

	public void toUp(int input) {
		if (85 < getApproxAngle()  && getApproxAngle() < 95)
			downToUp();
		if (40 < getApproxAngle()  && getApproxAngle() < 50)
			placeToUp();
	}

	public void initDefaultCommand() {
		SmartDashboard.putNumber("Fgain", .7871);

		conf1 = new MotionProfileConfiguration();
		// up to down
		conf1.setForwards(false);
		conf1.setMaxVel(300);
		conf1.setInterval(10);
		conf1.setEndDistance(250);
		conf1.setMaxAcc(60);

		conf2 = new MotionProfileConfiguration();
		// up to place
		conf2.setForwards(false);
		conf2.setMaxVel(3);
		conf2.setInterval(5);
		conf2.setEndDistance(.125);
		conf2.setMaxAcc(60);

		conf3 = new MotionProfileConfiguration();
		// place to up (reverse)
		conf3.setForwards(false);
		conf3.setMaxVel(3);
		conf3.setInterval(10);
		conf3.setEndDistance(.125);
		conf3.setMaxAcc(60);

		conf4 = new MotionProfileConfiguration();
		// place to down
		conf4.setForwards(true);
		conf4.setMaxVel(3);
		conf4.setInterval(10);
		conf4.setEndDistance(.125);
		conf4.setMaxAcc(60);

		conf5 = new MotionProfileConfiguration();
		// down to place (reverse)
		conf5.setForwards(false);
		conf5.setMaxVel(3);
		conf5.setInterval(10);
		conf5.setEndDistance(.125);
		conf5.setMaxAcc(60);

		conf6 = new MotionProfileConfiguration();
		// down to up (reverse)
		conf6.setForwards(false);
		conf6.setMaxVel(3);
		conf6.setInterval(10);
		conf6.setEndDistance(.25);
		conf6.setMaxAcc(60);

		upToDownTpList = mpgSvc.generatePoints(conf1);
		upToPlaceTpList = mpgSvc.generatePoints(conf2);
		placeToUpTpList = mpgSvc.generatePoints(conf3);
		placeToDownTpList = mpgSvc.generatePoints(conf4);
		downToPlaceTpList = mpgSvc.generatePoints(conf5);
		downToUpTpList = mpgSvc.generatePoints(conf6);

//		talon.changeMotionControlFramePeriod(5);

		mpGear.motionProfileInit();

	}
}
