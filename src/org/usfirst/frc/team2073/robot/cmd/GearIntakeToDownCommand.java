package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.OI;
import org.usfirst.frc.team2073.robot.subsys.GearPositionSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeToDownCommand extends Command {
	private final GearPositionSubsystem gearIntake;

	public GearIntakeToDownCommand() {
		gearIntake = RobotMap.getGearPosition();
		requires(gearIntake);
	}

	@Override
	protected void initialize() {
		gearIntake.upToDown();
	}

	@Override
	protected void execute() {
		if(!gearIntake.isMotionProfilingFinished())
			gearIntake.processMotionProfiling();
		else
			gearIntake.stopMotionProfiling();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
