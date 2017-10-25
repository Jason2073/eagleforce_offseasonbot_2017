package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class InvertDriveCommand extends Command {
	@Override
	protected void execute() {
		RobotMap.ballIntakeForwards = false;
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		RobotMap.ballIntakeForwards = true;
	}
}
