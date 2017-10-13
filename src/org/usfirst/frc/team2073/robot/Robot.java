package org.usfirst.frc.team2073.robot;

import com.eagleforce.robot.controller.ShooterController;

import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
//	private BallIntakeController ballCtr = new BallIntakeController();
//	private ClimberController climbCtr = new ClimberController();
//	private DriveController driveCtr = new DriveController();
//	private GearIntakeController gearCtr = new GearIntakeController();
	private ShooterController shootCtr = new ShooterController();
	

	@Override
	protected void robotInit() {
		
//		ballCtr.init();
//		climbCtr.init();
//		driveCtr.init();
//		gearCtr.init();
		shootCtr.init();
		
//		call other controller.init here
	}

}
