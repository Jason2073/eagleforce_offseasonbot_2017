package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RunMotionProfileCommand extends Command {
	private final DriveTrainSubsystem driveTrain;

	public RunMotionProfileCommand() {
		driveTrain = RobotMap.getDriveTrain();
		requires(driveTrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		driveTrain.doMotionProfile();
	}

	@Override
	protected boolean isFinished() {
		return driveTrain.isProfileComplete();
	}

	@Override
	protected void end() {
		driveTrain.stop();
	}
}
