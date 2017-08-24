package com.eagleforce.robot.service;

import java.util.List;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;
import com.eagleforce.robot.model.MotionProfileConfiguration;

import edu.wpi.first.wpilibj.Victor;

public class GearIntakeService {

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

	private Victor motor = new Victor(9);
	private CANTalon talon = new CANTalon(1);

	public CANTalon.MotionProfileStatus status = new CANTalon.MotionProfileStatus();

	public void startFilling(List<TrajectoryPoint> tpList) {
		talon.changeControlMode(TalonControlMode.MotionProfile);
		// CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
		if (status.hasUnderrun) {
			System.out.println("Has underrun");
			talon.clearMotionProfileHasUnderrun();
		}
		talon.clearMotionProfileTrajectories();
		for (TrajectoryPoint tp : tpList) {
			talon.pushMotionProfileTrajectory(tp);
		}
	}

	public GearIntakeService() {

		upToDownTpList = mps.generatePoints(conf1);
		upToPlaceTpList = mps.generatePoints(conf2);
		placeToUpTpList = mps.generatePoints(conf3);
		placeToDownTpList = mps.generatePoints(conf4);
		downToPlaceTpList = mps.generatePoints(conf5);
		downToUpTpList = mps.generatePoints(conf6);

		talon.setF(.7871);

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

	public void gearIn() {
		motor.set(-1);
	}

	public void gearOut() {
		motor.set(1);
	}

	public void gearHold() {
		motor.set(0);
	}

}
