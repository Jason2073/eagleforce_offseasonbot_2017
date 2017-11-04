package org.usfirst.frc.team2073.robot.cmd.drive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveForwardAndTuneF extends CommandGroup{
	
	public MoveForwardAndTuneF(double distance){
		addParallel(new MoveForwardMpCommand(distance));
		addParallel(new TuneFCommand( new MoveForwardMpCommand(distance)));
	}
}
