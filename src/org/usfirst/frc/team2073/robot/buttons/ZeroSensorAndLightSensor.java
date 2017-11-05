package org.usfirst.frc.team2073.robot.buttons;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class ZeroSensorAndLightSensor extends Trigger {
	
	private final DigitalInput magnet;
	private final DigitalInput lightSensor;

	public ZeroSensorAndLightSensor(DigitalInput magnet, DigitalInput lightSensor) {
		this.magnet = magnet;
		this.lightSensor = lightSensor;
	}

	@Override
	public boolean get() {
		return !magnet.get() && lightSensor.get();
	}
}
