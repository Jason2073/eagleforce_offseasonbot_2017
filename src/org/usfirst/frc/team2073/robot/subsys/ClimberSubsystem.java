package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class ClimberSubsystem extends Subsystem {
	private final Victor motor;

	public ClimberSubsystem() {
		motor = RobotMap.getClimberMotor();
		LiveWindow.addActuator("Climber", "Motor", motor);
	}

	@Override
	public void initDefaultCommand() {
	}

	public void startClimb() {
		motor.set(-1);
	}

	public void stopClimb() {
		motor.set(0);
	}
}
