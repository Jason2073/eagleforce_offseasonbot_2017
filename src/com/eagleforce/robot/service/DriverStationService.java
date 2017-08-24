package com.eagleforce.robot.service;

import edu.wpi.first.wpilibj.Joystick;

public class DriverStationService {

	private Joystick controller = new Joystick(2);
	private Joystick joystick = new Joystick(1);
	private Joystick wheel = new Joystick(0);

	// CONTROLLER
	// ==============================================================================================================================================================================
	// gear intake
	public boolean gearIntakeButton() {
		if (controller.getRawButton(2))
			return true;
		return false;
	}

	public boolean gearPlaceButton() {
		if (controller.getRawButton(7))
			return true;
		return false;
	}

	public int gearPositionDPad() {
		return controller.getPOV();
	}

	// Ball Intake
	public boolean ballIntakeButton() {
		// TODO: add logic here
		if (controller.getRawButton(3))
			return true;
		return false;
	}

	public boolean reverseBallIntakeButton() {
		if (controller.getRawButton(5))
			return true;
		return false;
	}

	// climber
	public boolean climberButton() {
		if (controller.getRawButton(4))
			return true;
		return false;
	}

	// shooter
	public double controllerJoystickAngle() {
		return controller.getDirectionDegrees();
	}

	// STEERING WHEEL
	// ==============================================================================================================================================================================

	public boolean pointTurnButton() {
		if (wheel.getRawButton(1))
			return true;
		return false;
	}

	public double wheelX() {
		return wheel.getX();
	}

	// JOYSTICK
	// ==============================================================================================================================================================================

	public boolean gearShiftButton() {
		if (joystick.getRawButton(4))
			return true;
		return false;
	}

	public double joystickY() {
		return joystick.getY();
	}

	public boolean shiftButton() {
		if (joystick.getRawButton(4))
			return true;
		return false;
	}

}
