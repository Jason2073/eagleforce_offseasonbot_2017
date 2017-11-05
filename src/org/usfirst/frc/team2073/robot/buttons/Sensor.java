package org.usfirst.frc.team2073.robot.buttons;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class Sensor extends Button {
	private final DigitalInput sensor;

	public Sensor(DigitalInput sensor) {
		this.sensor = sensor;
	}

	@Override
	public boolean get() {
		return sensor.get();
	}

	@Override
	public void whenPressed(Command command) {
		whenActive(command);
	}
			
}
