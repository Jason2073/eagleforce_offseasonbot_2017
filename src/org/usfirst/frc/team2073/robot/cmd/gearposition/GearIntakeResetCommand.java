package org.usfirst.frc.team2073.robot.cmd.gearposition;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.GearPositionSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeResetCommand extends Command {
	private final GearPositionSubsystem gearIntake;

	public GearIntakeResetCommand() {
		gearIntake = RobotMap.getGearPosition();
		requires(gearIntake);
		 setInterruptible(true);
	}

	@Override
	protected void execute() {
		if (!gearIntake.isZero()) {
//			System.out.println("Reset.execute() -> Intake not zero");
			gearIntake.resetGearIntake();
		} else {
//			System.out.println("Reset.execute() -> Intake is zero");
			gearIntake.zeroIntake();
		}
	}

	@Override
	protected boolean isFinished() {
		return gearIntake.isZero();
	}

	@Override
	protected void end() {
		gearIntake.zeroIntake();
		gearIntake.stop();
	}
}
