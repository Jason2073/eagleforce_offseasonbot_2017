package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.ctx.OI;
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
		gearIntake.toDown(OI.getController().getPOV());
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
