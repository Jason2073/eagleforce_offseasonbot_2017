package org.usfirst.frc.team2073.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
/*
 * **Keybinds and Port info
 *
 * Motors 
 * 0: left motor
 * 1: left motor 2
 * 2: right motor
 * 3: right motor 2
 * 4: climber
 * 5: ball intake
 * 6: ball intake
 * 7: belly panRollers 
 * 8: Intermediate roller
 * 9: Pull gear in
 * 10: Turret
 * 
 * Solenoids
 * 0: transmission
 * 1: transmission
 * 2: ball intake 
 * 3: ball intake
 * 
 * Controller Buttons(2)
 * 1:point turn
 * 2: pull gear in
 * 3: Ball intake motors
 * 4: push gear out
 * 5: Reverse Ball intake
 * 6: Gearintake down
 * 
 * Joystick Buttons(0)
 * 1:
 * 2:
 * 3:
 * 4: Change gear
 * 
 * Wheel Buttons (1)
 * 1:
 * 2:
 * 
 * Talons
 * 1: gear intake angle
 * 2: shooter1
 * 3: shooter2
 * 
 * Encoders:
 * 1,2: Left Drive
 * 3,4: Right Drive
 * 5,6: Turret rotate
 */
/**
 * This is a demo program showing the use of the RobotDrive class. The
 * SampleRobot class is the base of a robot application that will automatically
 * call your Autonomous and OperatorControl methods at the right time as
 * controlled by the switches on the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends SampleRobot {
/*	RobotDrive myRobot = new RobotDrive(0, 1);
	Joystick stick = new Joystick(0);
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	SendableChooser<String> chooser = new SendableChooser<>();
*/
	Ball_Intake BallIntake = new Ball_Intake();
	Climber Scaler = new Climber();
	Drivetrain Drive = new Drivetrain();
	Intermediate Shake = new Intermediate();
	GearIntake GimmieGear = new GearIntake();
	Shooter Bang = new Shooter();
	
	public Robot() {
//		myRobot.setExpiration(0.1);
	}

	@Override
	public void robotInit() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(20);
		camera.setResolution(320, 240);
		Bang.gyro.calibrate();
		BallIntake.Intake.start();
		Scaler.Climb.start();
		Drive.MainDrive.start();
		Shake.Transition.start();
		GimmieGear.Gear.start();
		Bang.Shoot.start();
		
		
		
		
/*		Climber.Climb.start();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto modes", chooser);
*/
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * if-else structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomous() {
/*		String autoSelected = chooser.getSelected();
		// String autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);

		switch (autoSelected) {
		case customAuto:
			myRobot.setSafetyEnabled(false);
			myRobot.drive(-0.5, 1.0); // spin at half speed
			Timer.delay(2.0); // for 2 seconds
			myRobot.drive(0.0, 0.0); // stop robot
			break;
		case defaultAuto:
		default:
			myRobot.setSafetyEnabled(false);
			myRobot.drive(-0.5, 0.0); // drive forwards half speed
			Timer.delay(2.0); // for 2 seconds
			myRobot.drive(0.0, 0.0); // stop robot
			break;
		}
		*/
	}

	/**
	 * Runs the motors with arcade steering.
	 */
	@Override
	public void operatorControl() {
/*		myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			myRobot.arcadeDrive(stick); // drive with arcade style (use right
										// stick)
			Timer.delay(0.005); // wait for a motor update time
		}
		*/
	}

	/**
	 * Runs during test mode
	 */
	@Override
	public void test() {
	}
}
