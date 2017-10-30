package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.cmd.TeleoperatedInitCommand;
import org.usfirst.frc.team2073.robot.cmd.auton.BlueBoilerSideCommandGroup;
import org.usfirst.frc.team2073.robot.cmd.auton.BlueFarSidePegCommandGroup;
import org.usfirst.frc.team2073.robot.cmd.auton.MiddlePegCommandGroup;
import org.usfirst.frc.team2073.robot.cmd.auton.RedBoilerSideCommandGroup;
import org.usfirst.frc.team2073.robot.cmd.auton.RedFarSidePegCommandGroup;
import org.usfirst.frc.team2073.robot.cmd.drive.MoveForwardMpCommand;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private SendableChooser<Command> chooser = new SendableChooser<>();
	private TeleoperatedInitCommand teleopInitCmd = new TeleoperatedInitCommand();
	
	@Override
	public void robotInit() {
		RobotMap.init();
		OI.init();
		chooser.addDefault("Cross Baseline", new MoveForwardMpCommand(100));
		chooser.addObject("Center Peg", new MiddlePegCommandGroup());
		chooser.addObject("RedFarSidePeg", new RedFarSidePegCommandGroup());
		chooser.addObject("BlueFarSidePeg", new BlueFarSidePegCommandGroup());
		chooser.addObject("RedBoilerSide", new RedBoilerSideCommandGroup());
		chooser.addObject("BlueBoilerSide", new BlueBoilerSideCommandGroup());
		SmartDashboard.putData("Auto Mode", chooser);
	}

	@Override
	public void autonomousInit() {
		chooser.getSelected().start();
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		chooser.getSelected().cancel();
		teleopInitCmd.start();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
