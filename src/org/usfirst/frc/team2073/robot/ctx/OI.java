package org.usfirst.frc.team2073.robot.ctx;

import org.usfirst.frc.team2073.robot.cmd.FollowTurretTargetCommand;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeReset;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeToDown;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeToPlace;
import org.usfirst.frc.team2073.robot.cmd.GenerateMotionProfileCommand;
import org.usfirst.frc.team2073.robot.cmd.MoveCommand;
import org.usfirst.frc.team2073.robot.cmd.RunMotionProfileCommand;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.DriveWheel;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.PowerStick;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.Xbox;
import org.usfirst.frc.team2073.robot.svc.DPadPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {

	public Joystick controller;
	public Joystick wheel;
	public Joystick joystick;
	
	protected OI() {
    	System.out.println("OI constructor");
		// Only allow instantiation from the AppContext
	}
	
	protected void init() {
    	System.out.println("OI init");
		controller = new Joystick(Xbox.PORT);
		joystick = new Joystick(PowerStick.PORT);
		wheel = new Joystick(DriveWheel.PORT);
		
		Command move = new MoveCommand();
		Command genMP = new GenerateMotionProfileCommand();
		Command runMP = new RunMotionProfileCommand();
		Command followTurret = new FollowTurretTargetCommand();
		Command gearDown = new GearIntakeToDown();
		Command gearPlace = new GearIntakeToPlace();
		Command gearReset = new GearIntakeReset();
		
		JoystickButton x = new JoystickButton(controller, Xbox.ButtonPorts.X);
		JoystickButton a = new JoystickButton(controller, Xbox.ButtonPorts.A);
		JoystickButton b = new JoystickButton(controller, Xbox.ButtonPorts.B);
		JoystickButton y = new JoystickButton(controller, Xbox.ButtonPorts.Y);
//		DPadPosition dPadEast = new DPadPosition(controller, Xbox.ButtonPorts.EAST);
//		DPadPosition dPadNorthEast = new DPadPosition(controller, Xbox.ButtonPorts.NORTHEAST);
//		DPadPosition dPadNone = new DPadPosition(controller, Xbox.ButtonPorts.NONE);
		
//		a.whileHeld(move);
//		b.whenPressed(genMP);
//		x.toggleWhenPressed(runMP);
//		y.toggleWhenPressed(followTurret);
		a./*dPadEast.*/whileHeld(gearDown);
		b./*dPadNorthEast.*/whileHeld(gearPlace);
		x./*dPadNone.*/whileHeld(gearReset);
		
		
	}
}
