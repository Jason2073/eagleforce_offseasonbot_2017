package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.cmd.ClimbCommand;
import org.usfirst.frc.team2073.robot.cmd.MoveForwardMpCommand;
import org.usfirst.frc.team2073.robot.cmd.RedFarSidePegCommandGroup;
import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;
import org.usfirst.frc.team2073.robot.subsys.GearPositionSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	SendableChooser<Command> chooser = new SendableChooser<>();
	RedFarSidePegCommandGroup redFarSideAuto;
	@Override
	public void robotInit() {
		RobotMap.init();
		OI.init();
		chooser.addDefault("default", new ClimbCommand());
		chooser.addObject("RedFarSidePeg", new RedFarSidePegCommandGroup());
		redFarSideAuto = new RedFarSidePegCommandGroup();
	}

	@Override
	public void autonomousInit() {
//		move.start();
		redFarSideAuto.start();
//		autonomousCommand.start();
//		chooser.getSelected().start();
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		redFarSideAuto.cancel();
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
//		autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		GearPositionSubsystem gear = RobotMap.getGearPosition();
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
