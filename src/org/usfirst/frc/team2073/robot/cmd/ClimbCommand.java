package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.ClimberSubsystem;

import edu.wpi.first.wpilibj.command.Command;


public class ClimbCommand extends Command {
	private ClimberSubsystem climb;

	public ClimbCommand() {
		climb = RobotMap.getClimber();
		requires(climb);
	}

	protected void initialize() {

	}

	protected void execute() {
		climb.startClimb();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		climb.stopClimb();
	}

	protected void interrupted() {
	}
}
