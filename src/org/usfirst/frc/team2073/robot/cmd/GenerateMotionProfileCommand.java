package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GenerateMotionProfileCommand extends Command {
	private final DriveTrainSubsystem driveTrain;
	private boolean finished = false;
	
	public GenerateMotionProfileCommand() {
		driveTrain = RobotMap.getDriveTrain();
		requires(driveTrain);
	}

	@Override
	protected void initialize() {
		System.out.println("GenerateMotionProfileCommand initialize()");
		finished = false;
	}

	@Override
	protected void execute() {
		System.out.println("GenerateMotionProfileCommand execute()");
		driveTrain.generateMotionProfile();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		System.out.println("GenerateMotionProfileCommand isFinished()");
		return finished;
	}

	@Override
	protected void end() {
		System.out.println("GenerateMotionProfileCommand end()");
	}

	@Override
	protected void interrupted() {
		System.out.println("GenerateMotionProfileCommand interrupted()");
		end();
	}
}
