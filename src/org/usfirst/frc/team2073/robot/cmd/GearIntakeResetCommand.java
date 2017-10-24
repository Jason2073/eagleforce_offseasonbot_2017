package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeResetCommand extends Command {
	private final GearIntakeSubsystem gearIntake;

	public GearIntakeResetCommand() {
		gearIntake = RobotMap.getGearIntake();
		requires(gearIntake);
	}

	@Override
	protected void execute() {
		if (!gearIntake.isZero()) {
			gearIntake.resetGearIntake();
		} else {
			gearIntake.zeroIntake();
			gearIntake.stop();
		}
	}

	@Override
	protected boolean isFinished() {
		return gearIntake.isZero();
	}
	
	@Override
	protected void end() {
		gearIntake.zeroIntake();
	}
}
