package org.usfirst.frc.team2073.robot.cmd.gearintake;

import org.usfirst.frc.team2073.robot.OI;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeRumbleCommand extends Command{
	private Joystick controller;
	private Joystick wheel;

	public GearIntakeRumbleCommand() {
		controller = OI.getController();
		wheel = OI.getWheel();
	}
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		controller.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
		controller.setRumble(GenericHID.RumbleType.kRightRumble, 1);
		wheel.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
		wheel.setRumble(GenericHID.RumbleType.kRightRumble, 1);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}
	
}
