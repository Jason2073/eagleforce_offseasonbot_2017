package com.eagleforce.robot.controller;

import com.eagleforce.robot.service.DriverStationService;
import com.eagleforce.robot.service.MotionProfileGearIntakeService;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;

public class GearIntakeController {

	private DriverStationService dvrSvc = new DriverStationService();
	private MotionProfileGearIntakeService gearSvc = new MotionProfileGearIntakeService();
	private boolean filledPoints = false;

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
					if (gearSvc.isZero()) {
						gearSvc.resetEnc();
					}
					if (dvrSvc.controllerJoystickAngle() == 90 && gearSvc.getApproxAngle() == 0) {
						if (!filledPoints) {
							gearSvc.startFilling(gearSvc.upToDownTpList);
							filledPoints = true;
						} else {
							gearSvc.startMotionProfile();
							filledPoints = false;
						}
					} else if (dvrSvc.controllerJoystickAngle() == 90 && gearSvc.getApproxAngle() == 45) {
						if (!filledPoints) {
							gearSvc.startFilling(gearSvc.placeToDownTpList);
							filledPoints = true;
						} else {
							gearSvc.startMotionProfile();
							filledPoints = false;
						}
					} else if (dvrSvc.controllerJoystickAngle() == 45 && gearSvc.getApproxAngle() == 0) {
						if (!filledPoints) {
							gearSvc.startFilling(gearSvc.upToPlaceTpList);
							filledPoints = true;
						} else {
							gearSvc.startMotionProfile();
							filledPoints = false;
						}
					} else if (dvrSvc.controllerJoystickAngle() == 45 && gearSvc.getApproxAngle() == 90) {
						if (!filledPoints) {
							gearSvc.startFilling(gearSvc.downToPlaceTpList);
							filledPoints = true;
						} else {
							gearSvc.startMotionProfile();
							filledPoints = false;
						}
					} else if (dvrSvc.controllerJoystickAngle() == 0 && gearSvc.getApproxAngle() == 45) {
						if (!filledPoints) {
							gearSvc.startFilling(gearSvc.placeToUpTpList);
							filledPoints = true;
						} else {
							gearSvc.startMotionProfile();
							filledPoints = false;
						}
					} else if (dvrSvc.controllerJoystickAngle() == 0 && gearSvc.getApproxAngle() == 90) {
						if (!filledPoints) {
							gearSvc.startFilling(gearSvc.downToUpTpList);
							filledPoints = true;
						} else {
							gearSvc.startMotionProfile();
							filledPoints = false;
						}
					} else if (gearSvc.getApproxAngle() != 0 && gearSvc.getApproxAngle() != 45
							&& gearSvc.getApproxAngle() != 90) {
						System.out.println("ruh roh shaggy, we've been hit");
						gearSvc.resetGearIntake();
					} else {
						System.out.println("GearIntake is broke af dude");
					}
					// TODO: Finalize motion profiles and configure selector
					// here

				}
				Timer.delay(.005);
			}

		}
	};

	public void init() {
		gearIntakeControl.start();
	}

}
