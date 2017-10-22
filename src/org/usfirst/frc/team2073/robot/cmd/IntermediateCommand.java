package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.IntermediateSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntermediateCommand extends Command {
	private IntermediateSubsystem intermediate;
    public IntermediateCommand() {
    	intermediate = RobotMap.getIntermediate();
    	requires(intermediate);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	intermediate.intermediateOn();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	intermediate.intermediateOff();
    }

    protected void interrupted() {
    }
}
