package org.usfirst.frc.team2073.robot.cmd;

import org.omg.PortableInterceptor.ObjectIdHelper;
import org.usfirst.frc.team2073.robot.Robot;
import org.usfirst.frc.team2073.robot.ctx.OI;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearIntakeReset extends Command {
	
	private GearIntakeSubsystem gearIntake;

    public GearIntakeReset() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	gearIntake =  Robot.getCtx().getRobotMap().getGearIntake();
    	super.requires(gearIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearIntake.resetGearIntake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return gearIntake.isZero();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("gearIntakeInterrupted");
    }
}
