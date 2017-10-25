package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.subsys.GearPositionSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
	@Override
	public void robotInit() {
		RobotMap.init();
		OI.init();
	}

	@Override
	public void autonomousInit() {
//		autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
//		autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		GearPositionSubsystem gear = RobotMap.getGearIntake();
//		gear.readPos();
//		gear.isZero();
		Joystick joy = OI.getWheel();
		System.out.println(joy.getX());
//		System.out.println(joy.getY());
		
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
