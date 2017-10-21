package org.usfirst.frc.team2073.robot.svc;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class DPadPosition extends Button{
	public int inputAngle;

	public DPadPosition(Joystick joy, int angle){
		this.inputAngle = joy.getPOV();
		
	}
	
	
	public void whileHeld(final Command command) {
	    command.start();
	  }

	@Override
	public boolean get() {
		return false;
	}

}
