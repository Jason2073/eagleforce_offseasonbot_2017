package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.Robot;
import org.usfirst.frc.team2073.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class IntermediateSubsystem extends Subsystem {
	private Victor main = RobotMap.getIntermediateBelts();
	private Victor bellyRoller = RobotMap.getBellyRoller();
	
	public IntermediateSubsystem(){
		LiveWindow.addActuator("Intermediate", "UpToTurret", main);
		LiveWindow.addActuator("Intermediate", "BellyRollers ", bellyRoller);
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

	public void initDefaultCommand() {
	}
}
