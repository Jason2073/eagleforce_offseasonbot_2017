package org.usfirst.frc.team2073.robot.buttons;

import org.usfirst.frc.team2073.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;


public class DPadDown extends Trigger {
	Joystick controller = OI.getController();
    public boolean get() {
    	if(controller.getPOV() == 180) {
    		return true;
    	}
        return false;
    }
}
