package org.usfirst.frc.team2073.robot.cmd.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DelayCommand extends Command {//TODO: use WaitCommand
	private final double delay;
	
	public DelayCommand(double delayInSec) {
		this.delay = delayInSec;
	}

	@Override
	protected void initialize() {
		Timer.delay(delay);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
