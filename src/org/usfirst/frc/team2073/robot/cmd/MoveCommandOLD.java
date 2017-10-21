package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.Robot;
import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DriveTrainOLD;
import org.usfirst.frc.team2073.robot.subsys.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveCommandOLD extends Command {

	private Drivetrain dt;
	
    public MoveCommandOLD() {
    	System.out.println("-> MoveCommand Constructor");
        // Use requires() here to declare subsystem dependencies
    	dt = Robot.getCtx().getRobotMap().getDriveTrain();
    	super.requires(dt);
    	System.out.println("<- MoveCommand Constructor");
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
//    	dt.move();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	return false; // run forever while held
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
//    	dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
//    	dt.stop();
    }
    
    
}
