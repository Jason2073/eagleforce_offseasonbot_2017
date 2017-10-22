package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MotionProfileDriveCommand extends Command {
	private final DrivetrainSubsystem drivetrain;

	public MotionProfileDriveCommand() {
		drivetrain = RobotMap.getDrivetrain();
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		drivetrain.resetMotionProfiling();
	}

	@Override
	protected void execute() {
		drivetrain.processMotionProfiling();
	}

	@Override
	protected boolean isFinished() {
		return drivetrain.isMotionProfilingFinished();
	}

	@Override
	protected void end() {
		drivetrain.stopMotionProfiling();
	}
}
