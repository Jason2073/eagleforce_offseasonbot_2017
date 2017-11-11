package org.usfirst.frc.team2073.robot.cmd.auton;

import org.usfirst.frc.team2073.robot.cmd.drive.MoveBackwardMpCommand;
import org.usfirst.frc.team2073.robot.cmd.drive.MoveForwardMpCommand;
import org.usfirst.frc.team2073.robot.cmd.gearintake.GearOuttakeCommand;
import org.usfirst.frc.team2073.robot.cmd.gearposition.AutonResetCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddlePegCommandGroup extends CommandGroup {
	public MiddlePegCommandGroup() {
		addParallel(new AutonResetCommand(), 8);
		addSequential(new MoveForwardMpCommand(83));
		addSequential(new DelayCommand(1));
		addSequential(new GearOuttakeCommand(), 1);
		addParallel(new GearOuttakeCommand(), 2);
		addSequential(new MoveBackwardMpCommand(22));
	}
}
