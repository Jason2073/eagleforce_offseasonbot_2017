package org.usfirst.frc.team2073.robot.cmd;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DelayCommand extends Command {
	private int delay;
	
	public DelayCommand(int delayInSec) {
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
