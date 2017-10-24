package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.ctx.RobotMap;
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
		if(gearIntake.getAngle() == 0)
			gearIntake.resetGearIntake();
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
