package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.Robot;
import org.usfirst.frc.team2073.robot.subsys.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionProfileDrive extends Command {
	private Drivetrain dt;

    public MotionProfileDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	dt = Robot.getCtx().getRobotMap().getDriveTrain();
    	super.requires(dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.mpDrive();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
