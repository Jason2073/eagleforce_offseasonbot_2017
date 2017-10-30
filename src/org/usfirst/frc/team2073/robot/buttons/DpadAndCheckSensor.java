package org.usfirst.frc.team2073.robot.buttons;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class DpadAndCheckSensor extends Trigger {
	private Joystick controller;
	private int pov;
	private DigitalInput sensor;
	
	public DpadAndCheckSensor(Joystick controller, int pov, DigitalInput sensor) {
		this.controller = controller;
		this.pov = pov;
		this.sensor = sensor;
	}
	
	@Override
	public boolean get() {
		return controller.getPOV() == pov && sensor.get() ;
	}
}
