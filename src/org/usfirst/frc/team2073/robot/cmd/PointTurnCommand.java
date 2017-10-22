package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.ctx.OI;
import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class PointTurnCommand extends Command {
	private final DrivetrainSubsystem drivetrain;

	public PointTurnCommand() {
		drivetrain = RobotMap.getDrivetrain();
		requires(drivetrain);
	}

	@Override
	protected void execute() {
		drivetrain.pointTurn(OI.getWheel().getX());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
