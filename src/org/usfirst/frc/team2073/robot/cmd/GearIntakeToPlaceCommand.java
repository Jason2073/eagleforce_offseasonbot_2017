package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.OI;
import org.usfirst.frc.team2073.robot.subsys.GearPositionSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeToPlaceCommand extends Command {
	private final GearPositionSubsystem gearIntake;

	public GearIntakeToPlaceCommand() {
		gearIntake = RobotMap.getGearPosition();
		requires(gearIntake);
	}

	@Override
	protected void initialize() {
		gearIntake.upToPlace();
	}

	@Override
	protected void execute() {
		gearIntake.processMotionProfiling();
	}

	@Override
	protected boolean isFinished() {
			return gearIntake.isMotionProfilingFinished();
	}

	@Override
	protected void end() {
		gearIntake.stopMotionProfiling();
	}
}
