package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;

import com.google.inject.Inject;

import edu.wpi.first.wpilibj.command.Command;

public class TeleoperatedInitCommand extends Command {
	private final DrivetrainSubsystem drivetrain;

	@Inject
	TeleoperatedInitCommand(DrivetrainSubsystem drivetrain) {
		this.drivetrain = drivetrain;
		requires(drivetrain);
	}

	@Override
	protected void initialize() {
		drivetrain.stopBrakeMode();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
