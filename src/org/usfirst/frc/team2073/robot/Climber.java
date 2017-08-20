package org.usfirst.frc.team2073.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;


public class Climber {
	
	Victor ClimbMotor = new Victor(4);
	Joystick Stick = new Joystick(2);
	
	public void StartClimb(){
		ClimbMotor.set(-1);
	}
	public void StopClimb(){
		ClimbMotor.set(0);
	}
	Thread Climb = new Thread(){
		public void run(){
			while (RobotState.isEnabled()||RobotState.isDisabled()){
				if(RobotState.isAutonomous()){
				}else{
					if(Stick.getRawButton(4)){
						StartClimb();
					}else{
						StopClimb();
					}
				}
				Timer.delay(.005);
			}	
		}
	};

}
