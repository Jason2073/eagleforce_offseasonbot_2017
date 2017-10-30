package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.GearPositionSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeAutonomousResetCommand extends Command {
	private final GearPositionSubsystem gearIntake;

	public GearIntakeAutonomousResetCommand() {
		gearIntake = RobotMap.getGearPosition();
		requires(gearIntake);
//		setInterruptible(false);
	}

	@Override
	protected void execute() {
//		if (!gearIntake.isZero()) {
			gearIntake.resetGearIntake();
//		} else {
//			gearIntake.zeroIntake();
//		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		gearIntake.zeroIntake();
		gearIntake.stop();
	}
}
