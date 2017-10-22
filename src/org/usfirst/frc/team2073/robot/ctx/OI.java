package org.usfirst.frc.team2073.robot.ctx;

import org.usfirst.frc.team2073.robot.cmd.GearIntakeResetCommand;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeToDownCommand;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeToPlaceCommand;
import org.usfirst.frc.team2073.robot.cmd.MotionProfileDriveCommand;
import org.usfirst.frc.team2073.robot.cmd.PointTurnCommand;
import org.usfirst.frc.team2073.robot.cmd.ShiftCommand;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.DriveWheel;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.PowerStick;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.Xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
	private static Boolean initialized = false;
	private static Joystick controller;
	private static Joystick wheel;
	private static Joystick joystick;

	public static void init() {
		synchronized (initialized) {
			if (!initialized) {
				initialized = true;
				
				controller = new Joystick(Xbox.PORT);
				joystick = new Joystick(PowerStick.PORT);
				wheel = new Joystick(DriveWheel.PORT);
				
				Command gearDown = new GearIntakeToDownCommand();
				Command gearPlace = new GearIntakeToPlaceCommand();
				Command gearReset = new GearIntakeResetCommand();
				Command shift = new ShiftCommand();
				Command pointTurn = new PointTurnCommand();
				Command mpDrive = new MotionProfileDriveCommand();
				
				JoystickButton x = new JoystickButton(controller, Xbox.ButtonPorts.X);
				JoystickButton a = new JoystickButton(controller, Xbox.ButtonPorts.A);
				JoystickButton b = new JoystickButton(controller, Xbox.ButtonPorts.B);
				JoystickButton y = new JoystickButton(controller, Xbox.ButtonPorts.Y);
				JoystickButton leftJoy = new JoystickButton(joystick, PowerStick.ButtonPorts.LEFT);
				JoystickButton lPaddle = new JoystickButton(wheel, DriveWheel.ButtonPorts.LPADDLE);
				JoystickButton rightBumper = new JoystickButton(controller, Xbox.ButtonPorts.R1);
				
				a.whileHeld(gearDown);
				b.whileHeld(gearPlace);
				x.whileHeld(gearReset);
				leftJoy.toggleWhenPressed(shift);
				lPaddle.whileHeld(pointTurn);
				rightBumper.whileHeld(mpDrive);
			}
		}
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
