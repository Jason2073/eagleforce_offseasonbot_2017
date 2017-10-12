package com.eagleforce.robot.controller;

import com.eagleforce.robot.service.DriverStationService;
import com.eagleforce.robot.service.GyroService;
import com.eagleforce.robot.service.MotionProfileShooterService;
import com.eagleforce.robot.service.ShooterService;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;

public class ShooterController {

	private ShooterService shootSvc = new ShooterService();
	private MotionProfileShooterService  mpShooter = new MotionProfileShooterService();
	private DriverStationService drvSvc = new DriverStationService();
	private GyroService gyroSvc = new GyroService();
	
	private boolean pushedPoints = false;

	private Thread shooterControl = new Thread() {
		@Override
		public void run() {
			while (true) {
				if (RobotState.isAutonomous()) {
					// put auto here
				} else if (RobotState.isOperatorControl()) {
					if (drvSvc.controllerJoystickAngle() != 0) {
						double rotate = drvSvc.controllerJoystickAngle();
						if (rotate < 0)
							rotate += 360;
						rotate += gyroSvc.gyroAngle();
						shootSvc.moveTurret(rotate);
					}
					if (drvSvc.alignTurret()){
						if (!pushedPoints){
							mpShooter.generateProfile();
							mpShooter.pushPoints(mpShooter.turretProfile);
							pushedPoints = true;
						}else{
							mpShooter.processPoints();
						}
					}else{
						mpShooter.stopMotionProfile();
					}
					

					// TODO: add camera tracking and shooter wheel control

				}
				Timer.delay(.005);
			}

		}
	};

	public void init() {
		shooterControl.start();
		mpShooter.motionProfileInit();
		mpShooter.resetEnc();
	}

}
