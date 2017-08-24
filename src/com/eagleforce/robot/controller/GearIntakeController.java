package com.eagleforce.robot.controller;

import com.eagleforce.robot.service.DriverStationService;
import com.eagleforce.robot.service.GearIntakeService;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;

public class GearIntakeController {

	DriverStationService dvrSvc = new DriverStationService();
	GearIntakeService gearSvc = new GearIntakeService();

	private Thread gearIntakeControl = new Thread() {
		@Override
		public void run() {
			while (RobotState.isEnabled()) {
				if (RobotState.isAutonomous()) {
					// put auto here
				} else if (RobotState.isOperatorControl()) {

					if (dvrSvc.gearIntakeButton()) {
						gearSvc.gearIn();
					} else if (dvrSvc.gearPlaceButton()) {
						gearSvc.gearOut();
					} else {
						gearSvc.gearHold();
					}
					if(dvrSvc.controllerJoystickAngle()==90){
						gearSvc.startFilling(gearSvc.upToDownTpList);
					}

				}
				Timer.delay(.005);
			}

		}
	};

	public void init() {
		gearIntakeControl.start();
	}

}
