package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.ctx.OI;
import org.usfirst.frc.team2073.robot.ctx.RobotMap;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	@Override
	public void robotInit() {
		RobotMap.init();
		OI.init();
	}

	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
	}
}
