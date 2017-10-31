package org.usfirst.frc.team2073.robot.cmd.auton;

import org.usfirst.frc.team2073.robot.cmd.drive.MoveBackwardMpCommand;
import org.usfirst.frc.team2073.robot.cmd.drive.MoveForwardMpCommand;
import org.usfirst.frc.team2073.robot.cmd.drive.PointTurnMpCommand;
import org.usfirst.frc.team2073.robot.cmd.gearintake.GearOuttakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueBoilerSideCommandGroup extends CommandGroup {
	public BlueBoilerSideCommandGroup() {
		addSequential(new MoveForwardMpCommand(100));
		addSequential(new DelayCommand(.5));
		addSequential(new PointTurnMpCommand(78.));
		addSequential(new DelayCommand(.5));
		addSequential(new MoveForwardMpCommand(25));
		addSequential(new DelayCommand(1));
		addSequential(new GearOuttakeCommand(), 1);
		addParallel(new GearOuttakeCommand(), 2);
		addSequential(new MoveBackwardMpCommand(25));
	}
}