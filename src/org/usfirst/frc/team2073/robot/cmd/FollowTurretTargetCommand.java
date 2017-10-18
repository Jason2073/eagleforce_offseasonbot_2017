package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.Robot;
import org.usfirst.frc.team2073.robot.subsys.TurretSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FollowTurretTargetCommand extends Command {

	private TurretSubsystem tur;
	private CommandState state = CommandState.OFF;
	
	private enum CommandState {
		INITIALIZING,
		ACQUIRING_TARGET,
		TARGET_ACQUIRED,
		TARGET_OFF_SCREEN,
		STOPPING,
		OFF
	}
	
    public FollowTurretTargetCommand() {
    	tur = Robot.getCtx().getRobotMap().getTurret();
        // Use requires() here to declare subsystem dependencies
    	super.requires(tur);
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
		state = CommandState.INITIALIZING;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	processPeriodic();

		if (tur.targetLocked())
			state = CommandState.TARGET_ACQUIRED;
		else if (tur.targetOffScreen())
    		state = CommandState.TARGET_OFF_SCREEN;
		
		
    	switch (state) {
		case INITIALIZING:
    		state = CommandState.ACQUIRING_TARGET;
			break;
			
		case ACQUIRING_TARGET:
	    	tur.acquireTarget();
			break;
			
		case TARGET_ACQUIRED:
			if (!tur.targetLocked())
				state = CommandState.ACQUIRING_TARGET;
			break;

		case TARGET_OFF_SCREEN:
	    	// TODO: Scan to the right a little bit here
			break;

		case STOPPING:
	    	// Do nothing
			break;

		case OFF:
			// Error, this should never be called (This is assuming after end() is called
			// execute() will not be called again)
		default:
			// Developer error
			break;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false; // Runs forever while holding button
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	state = CommandState.STOPPING;
    	tur.stopMotionProfileMode();
    	state = CommandState.OFF;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	end();
    }
    
    private void processPeriodic() {
    	// TODO: Print to smartdashboard
    }
    
}
