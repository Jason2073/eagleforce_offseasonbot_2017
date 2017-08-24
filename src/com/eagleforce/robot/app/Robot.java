package com.eagleforce.robot.app;

import com.eagleforce.robot.controller.BallIntakeController;

import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	private BallIntakeController ballCtr = new BallIntakeController();

	@Override
	protected void robotInit() {
		ballCtr.init();
//		call other controller.init here
	}

}
