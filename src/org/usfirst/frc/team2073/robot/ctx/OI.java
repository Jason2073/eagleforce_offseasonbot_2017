package org.usfirst.frc.team2073.robot.ctx;

import org.usfirst.frc.team2073.robot.cmd.FollowTurretTargetCommand;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeReset;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeToDown;
import org.usfirst.frc.team2073.robot.cmd.GearIntakeToPlace;
import org.usfirst.frc.team2073.robot.cmd.GenerateMotionProfileCommand;
import org.usfirst.frc.team2073.robot.cmd.MotionProfileDrive;
import org.usfirst.frc.team2073.robot.cmd.MoveCommandOLD;
import org.usfirst.frc.team2073.robot.cmd.PointTurnCommand;
import org.usfirst.frc.team2073.robot.cmd.RunMotionProfileCommand;
import org.usfirst.frc.team2073.robot.cmd.ShiftCommand;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.DriveWheel;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.PowerStick;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.Xbox;
import org.usfirst.frc.team2073.robot.svc.DPadPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {

	public static Joystick controller;
	public static Joystick wheel;
	public static Joystick joystick;
	
	protected OI() {
    	System.out.println("OI constructor");
		// Only allow instantiation from the AppContext
	}
	
	protected void init() {
    	System.out.println("OI init");
		controller = new Joystick(Xbox.PORT);
		joystick = new Joystick(PowerStick.PORT);
		wheel = new Joystick(DriveWheel.PORT);
		
		Command move = new MoveCommandOLD();
		Command genMP = new GenerateMotionProfileCommand();
		Command runMP = new RunMotionProfileCommand();
		Command followTurret = new FollowTurretTargetCommand();
		Command gearDown = new GearIntakeToDown();
		Command gearPlace = new GearIntakeToPlace();
		Command gearReset = new GearIntakeReset();
		Command shift = new ShiftCommand();
		Command pointTurn = new PointTurnCommand();
		Command mpDrive = new MotionProfileDrive();
		
		JoystickButton x = new JoystickButton(controller, Xbox.ButtonPorts.X);
		JoystickButton a = new JoystickButton(controller, Xbox.ButtonPorts.A);
		JoystickButton b = new JoystickButton(controller, Xbox.ButtonPorts.B);
		JoystickButton y = new JoystickButton(controller, Xbox.ButtonPorts.Y);
		JoystickButton centerJoy = new JoystickButton(joystick, PowerStick.ButtonPorts.CENTER);
		JoystickButton lPaddle = new JoystickButton(wheel, DriveWheel.ButtonPorts.LPADDLE);
		JoystickButton rightBumper = new JoystickButton(controller, Xbox.ButtonPorts.R1);
		
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
		centerJoy.toggleWhenPressed(shift);
		lPaddle.whenPressed(pointTurn);
		rightBumper.whenPressed(mpDrive);
				
		
		
	}
}
