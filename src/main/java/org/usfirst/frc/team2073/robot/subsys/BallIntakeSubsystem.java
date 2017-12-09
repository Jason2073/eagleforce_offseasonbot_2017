package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.BallIntake;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

@Singleton
public class BallIntakeSubsystem extends Subsystem {
	private final PWMSpeedController motor1;
	private final PWMSpeedController motor2;
	private final Solenoid solenoid;

	@Inject
	BallIntakeSubsystem(@Named("Ball Intake 1") PWMSpeedController motor1,
			@Named("Ball Intake 2") PWMSpeedController motor2,
			@Named("Ball Intake") Solenoid solenoid) {
		this.motor1 = motor1;
		this.motor2 = motor2;
		this.solenoid = solenoid;

		holdIntake();

		LiveWindow.addActuator(BallIntake.NAME, BallIntake.ComponentNames.MOTOR_1, motor1);
		LiveWindow.addActuator(BallIntake.NAME, BallIntake.ComponentNames.MOTOR_2, motor2);
		LiveWindow.addActuator(BallIntake.NAME, BallIntake.ComponentNames.SOLENOID, solenoid);
	}

	@Override
	public void initDefaultCommand() {
	}

	public void deployIntake() {
		solenoid.set(false);
	}
	
	public void holdIntake() {
		solenoid.set(true);
	}

	public void intakeBalls() {
		motor1.set(.8);
		motor2.set(.8);
	}

	public void reverseIntake() {
		motor1.set(-.8);
		motor2.set(-.8);
	}

	public void stopIntake() {
		motor1.set(0);
		motor2.set(0);
	}
}
