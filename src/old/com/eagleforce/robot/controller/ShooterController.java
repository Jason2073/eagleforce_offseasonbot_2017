package old.com.eagleforce.robot.controller;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import old.com.eagleforce.robot.model.CameraMessage;
import old.com.eagleforce.robot.service.DriverStationService;
import old.com.eagleforce.robot.service.GyroService;
import old.com.eagleforce.robot.service.MotionProfileShooterService;
import old.com.eagleforce.robot.service.ShooterService;

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
//					if (drvSvc.controllerJoystickAngle() != 0) {
//						double rotate = drvSvc.controllerJoystickAngle();
//						if (rotate < 0)
//							rotate += 360;
//						rotate += gyroSvc.gyroAngle();
//						shootSvc.moveTurret(rotate);
//					}
//					shootSvc.moveTurret(mpShooter.angle());
					if (drvSvc.alignTurret() && !mpShooter.targetLocked()){
						if (!pushedPoints){
							System.out.println("pressed button");
							mpShooter.generateProfile();
							mpShooter.pushPoints(mpShooter.turretProfile);
							pushedPoints = true;
						}else if(!mpShooter.isToTheRight()){
							mpShooter.setDirection(false);
						}else {
							mpShooter.processPoints();
						}
					}else{
						mpShooter.stopMotionProfile();
						pushedPoints = false;
//						System.out.println("stopped");
					}
					
					mpShooter.printCameraInfo();
					
					

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
//		mpShooter.printTalonInfo();
	}

}
