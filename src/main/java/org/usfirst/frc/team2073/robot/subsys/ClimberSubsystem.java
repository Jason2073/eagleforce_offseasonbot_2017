package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.Climber;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

@Singleton
public class ClimberSubsystem extends Subsystem {
	private final PWMSpeedController motor;

	@Inject
	ClimberSubsystem(@Named("Climber") PWMSpeedController motor) {
		this.motor = motor;
		LiveWindow.addActuator(Climber.NAME, Climber.ComponentNames.MOTOR, motor);
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
