package org.usfirst.frc.team2073.robot.cmd.gearintake;

import org.usfirst.frc.team2073.robot.cmd.gearposition.GearIntakeHardResetCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FullHoldCommandGroup extends CommandGroup{
	
	public FullHoldCommandGroup() {
		addParallel(new GearIntakeHoldCommand());
		addParallel(new GearIntakeHardResetCommand());
		
	}
}
