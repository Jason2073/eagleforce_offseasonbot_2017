package org.usfirst.frc.team2073.robot.cmd.drive;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightThenTurnCommand extends Command {
	private final double linearDistance;
	private final double angleTurn;
	private final DrivetrainSubsystem drivetrain;

	public DriveStraightThenTurnCommand(double linearDistance, double angleTurn) {
		drivetrain = RobotMap.getDrivetrain();
		this.angleTurn = angleTurn;
		this.linearDistance = linearDistance;
	}

	@Override
	protected void initialize() {
		drivetrain.autonStraightDriveIntoTurn(linearDistance, angleTurn);
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
