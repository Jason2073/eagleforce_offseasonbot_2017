package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class IntermediateSubsystem extends Subsystem {
	private final Victor main;
	private final Victor bellyRoller;

	public IntermediateSubsystem(){
		main = RobotMap.getIntermediateBelts();
		bellyRoller = RobotMap.getBellyRoller();

		LiveWindow.addActuator("Intermediate", "Up To Turret", main);
		LiveWindow.addActuator("Intermediate", "Belly Rollers ", bellyRoller);
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
