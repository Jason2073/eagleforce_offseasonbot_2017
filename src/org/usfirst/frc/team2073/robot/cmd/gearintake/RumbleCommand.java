package org.usfirst.frc.team2073.robot.cmd.gearintake;

import org.usfirst.frc.team2073.robot.OI;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RumbleCommand extends Command{

	private Joystick controller = OI.getController();
	private Joystick wheel = OI.getWheel();
	
	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		controller.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
		controller.setRumble(GenericHID.RumbleType.kRightRumble, 1);
		wheel.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
		wheel.setRumble(GenericHID.RumbleType.kRightRumble, 1);
		Timer.delay(1);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}


}
