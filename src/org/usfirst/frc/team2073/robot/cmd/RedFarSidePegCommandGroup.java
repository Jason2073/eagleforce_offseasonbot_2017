package org.usfirst.frc.team2073.robot.cmd;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedFarSidePegCommandGroup extends CommandGroup {
	public RedFarSidePegCommandGroup() {
		addSequential(new MoveForwardMpCommand(114));
		addSequential(new PointTurnMpCommand(30));
		addSequential(new MoveForwardMpCommand(40));
		addParallel(new GearIntakeToPlaceCommand());
		addParallel(new GearOuttakeCommand());
		addSequential(new MoveBackwardMpCommand(50));
	}
}
