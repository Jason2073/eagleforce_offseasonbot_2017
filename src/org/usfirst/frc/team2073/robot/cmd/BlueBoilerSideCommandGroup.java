package org.usfirst.frc.team2073.robot.cmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueBoilerSideCommandGroup extends CommandGroup {
	public BlueBoilerSideCommandGroup() {
		addSequential(new MoveForwardMpCommand(100));
		addSequential(new PointTurnMpCommand(78.));
		addSequential(new MoveForwardMpCommand(25));
//		addParallel(new GearIntakeToPlaceCommand());
		addSequential(new DelayCommand(1));
		addSequential(new GearOuttakeCommand(), 1);
		addParallel(new GearOuttakeCommand(), 2);
		addSequential(new MoveBackwardMpCommand(25));
	}
}
