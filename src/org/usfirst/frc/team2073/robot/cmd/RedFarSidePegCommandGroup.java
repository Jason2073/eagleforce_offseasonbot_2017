package org.usfirst.frc.team2073.robot.cmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedFarSidePegCommandGroup extends CommandGroup {
	public RedFarSidePegCommandGroup() {
		addSequential(new MoveForwardMpCommand(114.939));
		addSequential(new PointTurnMpCommand(65.13));
		addSequential(new MoveForwardMpCommand(22));
//		addParallel(new GearIntakeToPlaceCommand());
		addParallel(new GearOuttakeCommand());
		addSequential(new MoveBackwardMpCommand(22));
	}
}
