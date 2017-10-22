package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.BallIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeployBallIntakeCommand extends Command {
	private BallIntakeSubsystem ballIntake;
		
    public DeployBallIntakeCommand() {
    	ballIntake = RobotMap.getBallIntake();
		requires(ballIntake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	ballIntake.deployIntake();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
