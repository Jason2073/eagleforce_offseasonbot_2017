package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.MPTurretSubsystemOLD;

import edu.wpi.first.wpilibj.command.Command;

public class FollowTurretTargetCommand extends Command {
	private final MPTurretSubsystemOLD turret;
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
		turret = RobotMap.getTurretOLD();
		requires(turret);
	}

	@Override
	protected void initialize() {
		state = CommandState.INITIALIZING;
	}

	@Override
	protected void execute() {
		processPeriodic();

		if (turret.targetLocked()) {
			state = CommandState.TARGET_ACQUIRED;
		} else if (turret.targetOffScreen()) {
			state = CommandState.TARGET_OFF_SCREEN;
		}
		
		switch (state) {
			case INITIALIZING: {
				state = CommandState.ACQUIRING_TARGET;
				break;
			}
			case ACQUIRING_TARGET: {
				turret.acquireTarget();
				break;
			}
			case TARGET_ACQUIRED: {
				if (!turret.targetLocked()) {
					state = CommandState.ACQUIRING_TARGET;
				}
				break;
			}
			case TARGET_OFF_SCREEN: {
				// TODO: Scan to the right a little bit here
				break;
			}
			case STOPPING: {
				// Do nothing
				break;
			}
			case OFF:
				// Error, this should never be called (This is assuming after end() is called
				// execute() will not be called again)
			default: {
				// Developer error
				break;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return false; // Runs forever while holding button
	}

	@Override
	protected void end() {
		state = CommandState.STOPPING;
		turret.stopMotionProfileMode();
		state = CommandState.OFF;
	}

    @Override
    protected void interrupted() {
    	end();
    }
	
	private void processPeriodic() {
		// TODO: Print to smartdashboard
	}
}
