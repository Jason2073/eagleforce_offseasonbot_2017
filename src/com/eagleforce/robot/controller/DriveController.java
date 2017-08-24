package com.eagleforce.robot.controller;

import com.eagleforce.robot.service.DriverStationService;
import com.eagleforce.robot.service.DrivetrainService;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;

public class DriveController {

	private DriverStationService dvrSvc = new DriverStationService();
	private DrivetrainService dTrainSvc = new DrivetrainService();

	private Thread driveControl = new Thread() {
		@Override
		public void run() {
			while (RobotState.isEnabled()) {
				if (RobotState.isAutonomous()) {
					// put auto here
				} else if (RobotState.isOperatorControl()) {
					if (dvrSvc.pointTurnButton()) {
						dTrainSvc.pTurn(dvrSvc.wheelX());
					} else {
						dTrainSvc.steerwheel(dvrSvc.joystickY(), dvrSvc.wheelX());
					}
					if (dvrSvc.shiftButton()) {
						dTrainSvc.shiftHighGear();
					} else {
						dTrainSvc.shiftLowGear();
					}
				}
				Timer.delay(.005);
			}
		}
	};

	public void init() {
		driveControl.start();
	}

}
