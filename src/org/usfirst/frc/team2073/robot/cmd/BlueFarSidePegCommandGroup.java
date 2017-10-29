package org.usfirst.frc.team2073.robot.cmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueFarSidePegCommandGroup extends CommandGroup {
	public BlueFarSidePegCommandGroup() {
		addSequential(new MoveForwardMpCommand(100));
		addSequential(new PointTurnMpCommand(-60.));
		addSequential(new MoveForwardMpCommand(22));
//		addParallel(new GearIntakeToPlaceCommand());
		addSequential(new DelayCommand(1));
		addSequential(new GearOuttakeCommand(), 1);
		addParallel(new GearOuttakeCommand(), 2);
		addSequential(new MoveBackwardMpCommand(22));
	}
}
