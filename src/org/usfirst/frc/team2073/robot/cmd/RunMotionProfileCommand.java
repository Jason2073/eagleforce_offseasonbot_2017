package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.Robot;
import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunMotionProfileCommand extends Command {

	private DriveTrain dt = Robot.getCtx().getRobotMap().getDriveTrain();

    public RunMotionProfileCommand() {
        // Use requires() here to declare subsystem dependencies
    	super.requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.doMotionProfile();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return dt.isProfileComplete();
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	dt.stop();
    }
}
