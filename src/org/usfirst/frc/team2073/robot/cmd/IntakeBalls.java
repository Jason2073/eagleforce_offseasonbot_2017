package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.BallIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeBalls extends Command {
	private BallIntakeSubsystem ballIntake;
		
    public IntakeBalls() {
    	ballIntake = RobotMap.getBallIntake();
		requires(ballIntake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	ballIntake.intakeBalls();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	ballIntake.stopIntake();
    }

    protected void interrupted() {
    }
}
