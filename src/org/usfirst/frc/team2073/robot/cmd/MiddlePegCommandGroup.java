package org.usfirst.frc.team2073.robot.cmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddlePegCommandGroup extends CommandGroup {
	public MiddlePegCommandGroup() {
//		addParallel(new GearIntakeAutonomousResetCommand());
		addSequential(new MoveForwardMpCommand(83));
		addSequential(new DelayCommand(1));
		addSequential(new GearOuttakeCommand(), 1);
		addParallel(new GearOuttakeCommand(), 2);
		addSequential(new MoveBackwardMpCommand(22));
	}
}
