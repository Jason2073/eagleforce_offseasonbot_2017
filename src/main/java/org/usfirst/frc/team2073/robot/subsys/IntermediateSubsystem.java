package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.Intermediate;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

@Singleton
public class IntermediateSubsystem extends Subsystem {
	private final PWMSpeedController main;
	private final PWMSpeedController bellyRoller;

	@Inject
	IntermediateSubsystem(@Named("Intermediate") PWMSpeedController main,
			@Named("Belly Roller") PWMSpeedController bellyRoller) {
		this.main = main;
		this.bellyRoller = bellyRoller;

		LiveWindow.addActuator(Intermediate.NAME, Intermediate.ComponentNames.MAIN, main);
		LiveWindow.addActuator(Intermediate.NAME, Intermediate.ComponentNames.BELL_ROLLER, bellyRoller);
	}

	@Override
	public void initDefaultCommand() {
	}

	public void intermediateOn() {
		main.set(1);
	}

	public void intermediateOff() {
		main.set(0);
	}

	public void bellyPanOn() {
		bellyRoller.set(1);
	}

	public void bellyPanOff() {
		bellyRoller.set(0);
	}
}
