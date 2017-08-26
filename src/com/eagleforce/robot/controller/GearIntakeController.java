package com.eagleforce.robot.controller;

import com.eagleforce.robot.service.DriverStationService;
import com.eagleforce.robot.service.GearIntakeService;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;

public class GearIntakeController {

	private DriverStationService dvrSvc = new DriverStationService();
	private GearIntakeService gearSvc = new GearIntakeService();

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
					} else if (gearSvc.lightSensor()) {
						gearSvc.gearHold();
					} else {
						gearSvc.gearStop();
					}

					if (dvrSvc.controllerJoystickAngle() == 90 && gearSvc.getApproxAngle() == 0) {
						gearSvc.runDownToUp();
					}
					if (dvrSvc.controllerJoystickAngle() == 90 && gearSvc.getApproxAngle() == 45) {
						// gearSvc.runPlaceToDown;
					}
					if (dvrSvc.controllerJoystickAngle() == 45 && gearSvc.getApproxAngle() == 0) {
						// gearSvc.runUpToPlace;
					}
					if (dvrSvc.controllerJoystickAngle() == 45 && gearSvc.getApproxAngle() == 90) {
						// gearSvc.runDownToPlace;
					}
					if (dvrSvc.controllerJoystickAngle() == 0 && gearSvc.getApproxAngle() == 45) {
						// gearSvc.runPlaceToUp;
					}
					if (dvrSvc.controllerJoystickAngle() == 0 && gearSvc.getApproxAngle() == 90) {
						// gearSvc.runDownToUp;
					}
					// TODO: Finalize motion profiles and configure selector here

				}
				Timer.delay(.005);
			}

		}
	};

	public void init() {
		gearIntakeControl.start();
	}

}
