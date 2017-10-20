package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MoveCommand extends Command {
	private final DriveTrainSubsystem driveTrain;
	
	public MoveCommand() {
		driveTrain = RobotMap.getDriveTrain();
		requires(driveTrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		driveTrain.move();
	}

	@Override
	protected boolean isFinished() {
		return false; // run forever while held
	}

	@Override
	protected void end() {
		driveTrain.stop();
	}
}
