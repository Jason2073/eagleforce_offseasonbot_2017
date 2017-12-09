package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.GearIntake;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

@Singleton
public class GearIntakeSubsystem extends Subsystem {
	private final PWMSpeedController intakeMotor;

	@Inject
	GearIntakeSubsystem(@Named("Gear Intake") PWMSpeedController intakeMotor) {
		this.intakeMotor = intakeMotor;
		LiveWindow.addActuator(GearIntake.NAME, GearIntake.ComponentNames.INTAKE_MOTOR, intakeMotor);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public void gearIn() {
		intakeMotor.set(-.65);
	}

	public void gearOut() {
		intakeMotor.set(.65);
	}

	public void gearHold() {
		intakeMotor.set(-.3);
	}

	public void gearStop() {
		intakeMotor.set(0);
	}
}
