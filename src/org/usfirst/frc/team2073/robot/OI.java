package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.buttons.JoystickPOV;
import org.usfirst.frc.team2073.robot.buttons.Sensor;
import org.usfirst.frc.team2073.robot.cmd.ballintake.IntakeBallsCommand;
import org.usfirst.frc.team2073.robot.cmd.ballintake.OuttakeBallsCommand;
import org.usfirst.frc.team2073.robot.cmd.climb.ClimbCommand;
import org.usfirst.frc.team2073.robot.cmd.drive.InvertDriveCommand;
import org.usfirst.frc.team2073.robot.cmd.drive.MoveForwardAndTuneF;
import org.usfirst.frc.team2073.robot.cmd.drive.PointTurnCommand;
import org.usfirst.frc.team2073.robot.cmd.drive.ShiftCommand;
import org.usfirst.frc.team2073.robot.cmd.gearintake.GearIntakeCommand;
import org.usfirst.frc.team2073.robot.cmd.gearintake.GearIntakeHoldCommand;
import org.usfirst.frc.team2073.robot.cmd.gearintake.GearOuttakeCommand;
import org.usfirst.frc.team2073.robot.cmd.gearposition.GearIntakeHardResetCommand;
import org.usfirst.frc.team2073.robot.cmd.gearposition.GearIntakeResetCommand;
import org.usfirst.frc.team2073.robot.cmd.gearposition.GearIntakeToDownCommand;
import org.usfirst.frc.team2073.robot.cmd.gearposition.GearIntakeToPlaceCommand;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.DriveWheel;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.PowerStick;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.Xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OI {
	private static Joystick controller = new Joystick(Xbox.PORT);
	private static Joystick joystick = new Joystick(PowerStick.PORT);
	private static Joystick wheel = new Joystick(DriveWheel.PORT);

	static void init() {
		Command gearDown = new GearIntakeToDownCommand();		
		Command gearPlace = new GearIntakeToPlaceCommand();
		Command gearReset = new GearIntakeResetCommand();
		Command gearIntake = new GearIntakeCommand();
		Command gearOuttake = new GearOuttakeCommand();
		Command shift = new ShiftCommand();
		Command pointTurn = new PointTurnCommand();
		Command intakeBalls = new IntakeBallsCommand();
		Command outtakeBalls = new OuttakeBallsCommand();
		Command climb = new ClimbCommand();
		Command toggleDriveDirection = new InvertDriveCommand(); 
		Command gearHold = new GearIntakeHoldCommand();
		Command moveForward = new MoveForwardAndTuneF(100);
		
		CommandGroup gearHardResetAndHold = new CommandGroup();
		gearHardResetAndHold.addParallel(new GearIntakeHardResetCommand());
		gearHardResetAndHold.addParallel(new GearIntakeHoldCommand());
		
		JoystickButton x = new JoystickButton(controller, Xbox.ButtonPorts.X);
		JoystickButton a = new JoystickButton(controller, Xbox.ButtonPorts.A);
		JoystickButton b = new JoystickButton(controller, Xbox.ButtonPorts.B);
		JoystickButton y = new JoystickButton(controller, Xbox.ButtonPorts.Y);
		JoystickButton leftBumper = new JoystickButton(controller, Xbox.ButtonPorts.L1);
		JoystickButton leftJoy = new JoystickButton(joystick, PowerStick.ButtonPorts.LEFT);
		JoystickButton lPaddle = new JoystickButton(wheel, DriveWheel.ButtonPorts.LPADDLE);
		JoystickButton rightBumper = new JoystickButton(controller, Xbox.ButtonPorts.R1);
		JoystickButton leftTrigger = new JoystickButton(controller, Xbox.ButtonPorts.L2);
		JoystickButton joystickCenter = new JoystickButton(joystick, PowerStick.ButtonPorts.CENTER);
		JoystickPOV dPadDown = new JoystickPOV(controller, 180);
		JoystickPOV dPadRight = new JoystickPOV(controller, 90);
		JoystickPOV dPadNone = new JoystickPOV(controller, -1);
		Sensor sensor = new Sensor(RobotMap.getLightSensor());
		
		joystickCenter.toggleWhenPressed(toggleDriveDirection);
		a.whileHeld(gearIntake);
		b.whileHeld(gearOuttake);
		dPadDown.whileActive(gearDown);
		dPadNone.whileActive(gearReset);
		dPadRight.whileActive(gearPlace);
		leftTrigger.whileHeld(outtakeBalls);
		x.whenPressed(moveForward);
		y.whileHeld(climb);
		leftBumper.whenPressed(intakeBalls);
		leftJoy.toggleWhenPressed(shift);
		lPaddle.whileHeld(pointTurn);
		rightBumper.whenPressed(gearHold);
		sensor.whileActive(gearHardResetAndHold);
	}

	public static Joystick getController() {
		return controller;
	}

	public static Joystick getWheel() {
		return wheel;
	}

	public static Joystick getJoystick() {
		return joystick;
	}
}
