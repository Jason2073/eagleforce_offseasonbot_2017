package org.usfirst.frc.team2073.robot.buttons;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class JoystickPOV extends Trigger {
	private final GenericHID joystick;
	private final int pov;

	public JoystickPOV(GenericHID joystick, int pov) {
		this.joystick = joystick;
		this.pov = pov;
	}

	@Override
	public boolean get() {
		return joystick.getPOV() == pov;
	}
}
