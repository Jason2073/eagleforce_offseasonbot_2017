package org.usfirst.frc.team2073.robot.ctx;

import org.usfirst.frc.team2073.robot.cmd.FollowTurretTargetCommand;
import org.usfirst.frc.team2073.robot.cmd.GenerateMotionProfileCommand;
import org.usfirst.frc.team2073.robot.cmd.MoveCommand;
import org.usfirst.frc.team2073.robot.cmd.RunMotionProfileCommand;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Controllers.Xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
	private static Joystick controller;

	public static void init() {
		controller = new Joystick(Xbox.PORT);
		
		Command move = new MoveCommand();
		Command genMP = new GenerateMotionProfileCommand();
		Command runMP = new RunMotionProfileCommand();
		Command followTurret = new FollowTurretTargetCommand();
		
		JoystickButton x = new JoystickButton(controller, Xbox.ButtonPorts.X);
		JoystickButton a = new JoystickButton(controller, Xbox.ButtonPorts.A);
		JoystickButton b = new JoystickButton(controller, Xbox.ButtonPorts.B);
		JoystickButton y = new JoystickButton(controller, Xbox.ButtonPorts.Y);
		
		a.whileHeld(move);
		b.whenPressed(genMP);
		x.toggleWhenPressed(runMP);
		y.toggleWhenPressed(followTurret);
	}
}
