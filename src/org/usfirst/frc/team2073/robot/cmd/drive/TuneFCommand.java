package org.usfirst.frc.team2073.robot.cmd.drive;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class TuneFCommand extends Command {
	private final DrivetrainSubsystem drive;
	private final Command moveForward;
	private double startingGyro = 0;

	public TuneFCommand(Command moveForward) {
		this.moveForward = moveForward;
		drive = RobotMap.getDrivetrain();
	}

	@Override
	protected void initialize() {
		startingGyro = drive.getGyroAngle();
	}

	@Override
	protected void execute() {
		drive.adjustF(startingGyro);
	}

	@Override
	protected boolean isFinished() {
		return !moveForward.equals(drive.getCurrentCommand());
	}
}
