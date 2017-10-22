package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.cmd.ClimbCommand;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeResetCommand;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeToDownCommand;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeToPlaceCommand;
import org.usfirst.frc.team2073.robot.cmd.IntakeBallsCommand;
import org.usfirst.frc.team2073.robot.cmd.MotionProfileDriveCommand;
import org.usfirst.frc.team2073.robot.cmd.OuttakeBallsCommand;
import org.usfirst.frc.team2073.robot.cmd.PointTurnCommand;
import org.usfirst.frc.team2073.robot.cmd.ShiftCommand;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.DriveWheel;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.PowerStick;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.Xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
	private static Joystick controller;
	private static Joystick wheel;
	private static Joystick joystick;

	static void init() {
		controller = new Joystick(Xbox.PORT);
		joystick = new Joystick(PowerStick.PORT);
		wheel = new Joystick(DriveWheel.PORT);
		
		Command gearDown = new GearIntakeToDownCommand();
		Command gearPlace = new GearIntakeToPlaceCommand();
//		Command gearReset = new GearIntakeResetCommand();
		Command shift = new ShiftCommand();
		Command pointTurn = new PointTurnCommand();
		Command mpDrive = new MotionProfileDriveCommand();
		Command intakeBalls = new IntakeBallsCommand();
		Command outtakeBalls = new OuttakeBallsCommand();
		Command climb = new ClimbCommand();
		
		JoystickButton x = new JoystickButton(controller, Xbox.ButtonPorts.X);
		JoystickButton a = new JoystickButton(controller, Xbox.ButtonPorts.A);
		JoystickButton b = new JoystickButton(controller, Xbox.ButtonPorts.B);
		JoystickButton y = new JoystickButton(controller, Xbox.ButtonPorts.Y);
		JoystickButton leftBumper = new JoystickButton(controller, Xbox.ButtonPorts.L1);
		JoystickButton leftJoy = new JoystickButton(joystick, PowerStick.ButtonPorts.LEFT);
		JoystickButton lPaddle = new JoystickButton(wheel, DriveWheel.ButtonPorts.LPADDLE);
		JoystickButton rightBumper = new JoystickButton(controller, Xbox.ButtonPorts.R1);
		
		a.whileHeld(gearDown);
		b.whileHeld(gearPlace);
		x.whileHeld(intakeBalls);
		y.whileHeld(climb);
		leftBumper.whileHeld(outtakeBalls);
		leftJoy.toggleWhenPressed(shift);
		lPaddle.whileHeld(pointTurn);
		rightBumper.whileHeld(mpDrive);
		
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
