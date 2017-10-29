package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeSoftResetCommand extends Command {
	private final GearIntakeSubsystem gearIntake;

	public GearIntakeSoftResetCommand() {
		gearIntake = RobotMap.getGearIntake();
		requires(gearIntake);
//		setInterruptible(false);
	}

	@Override
	protected void execute() {
		gearIntake.gearSoftReset();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void end() {
		gearIntake.gearStop();
	}
	
}
