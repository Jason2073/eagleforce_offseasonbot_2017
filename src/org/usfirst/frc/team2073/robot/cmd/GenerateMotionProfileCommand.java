package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.Robot;
import org.usfirst.frc.team2073.robot.subsys.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GenerateMotionProfileCommand extends Command {

	private DriveTrain dt = Robot.getCtx().getRobotMap().getDriveTrain();
	boolean finished = false;
	
    public GenerateMotionProfileCommand() {
        // Use requires() here to declare subsystem dependencies
    	super.requires(dt);
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	System.out.println("GenerateMotionProfileCommand initialize()");
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	System.out.println("GenerateMotionProfileCommand execute()");
    	dt.generateMotionProfile();
    	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	System.out.println("GenerateMotionProfileCommand isFinished()");
        return finished;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	System.out.println("GenerateMotionProfileCommand end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	System.out.println("GenerateMotionProfileCommand interrupted()");
    }
}
