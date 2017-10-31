package org.usfirst.frc.team2073.robot.buttons;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickPOVAndSensor extends JoystickPOV {
	private final DigitalInput sensor;

	public JoystickPOVAndSensor(Joystick controller, int pov, DigitalInput sensor) {
		super(controller, pov);
		this.sensor = sensor;
	}

	@Override
	public boolean get() {
		return super.get() && sensor.get();
	}
}
