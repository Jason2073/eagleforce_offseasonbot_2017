package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.ctx.OI;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeToUpCommand extends Command {
	private final GearIntakeSubsystem gearIntake;

	public GearIntakeToUpCommand() {
		gearIntake = RobotMap.getGearIntake();
		requires(gearIntake);
	}

	@Override
	protected void initialize() {
		gearIntake.toUp(OI.getController().getPOV());
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
