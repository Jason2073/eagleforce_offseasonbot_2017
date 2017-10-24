package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.OI;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeToDownCommand extends Command {
	private final GearIntakeSubsystem gearIntake;

	public GearIntakeToDownCommand() {
		gearIntake = RobotMap.getGearIntake();
		requires(gearIntake);
	}

	@Override
	protected void initialize() {
		gearIntake.upToDown();
	}

	@Override
	protected void execute() {
		gearIntake.processMotionProfiling();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
//		gearIntake.stopMotionProfiling();
	}
}
