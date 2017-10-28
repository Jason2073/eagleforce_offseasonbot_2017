package org.usfirst.frc.team2073.robot.buttons;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class DpadAndCheckZero extends Trigger {
	private Joystick controller;
	private int pov;
	private boolean sensor;
	
	public DpadAndCheckZero(Joystick controller, int pov, boolean sensor) {
		this.controller = controller;
		this.pov = pov;
		this.sensor = !sensor;
	}
	
	@Override
	public boolean get() {
		return controller.getPOV() == pov && sensor ;
	}
}
