package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.GearIntake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class GearIntakeSubsystem extends Subsystem {
	private final Victor intakeMotor;
	private final DigitalInput lightSensor;

	public GearIntakeSubsystem() {
		intakeMotor = RobotMap.getGearIntakeMotor();
		lightSensor = RobotMap.getLightSensor();

		LiveWindow.addActuator(GearIntake.NAME, GearIntake.ComponentNames.INTAKE_MOTOR, intakeMotor);
		LiveWindow.addSensor(GearIntake.NAME, GearIntake.ComponentNames.LIGHT_SENSOR, lightSensor);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public void gearIn() {
		intakeMotor.set(-.4);
	}

	public void gearOut() {
		intakeMotor.set(.4);
	}

	public void gearHold() {
		intakeMotor.set(-.2);
	}

	public void gearStop() {
		intakeMotor.set(0);
	}

	public boolean lightSensor() {
		return lightSensor.get();
	}
}
