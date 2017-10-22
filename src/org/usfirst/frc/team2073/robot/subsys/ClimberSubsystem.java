package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ClimberSubsystem extends Subsystem {
	
	private Victor climbMotor = RobotMap.getClimberMotor();

	public void startClimb() {
		climbMotor.set(-1);
	}

	public void stopClimb() {
		climbMotor.set(0);
	}

    public void initDefaultCommand() {
    	
    }
}

