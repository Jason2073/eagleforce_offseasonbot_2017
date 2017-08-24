package org.usfirst.frc.team2073.robot;
import edu.wpi.first.wpilibj.*;

public class Ball_Intake {
//	Victor intake = new Victor(5);
//	Victor intake2 = new Victor(6);
//	Victor belly_roller = new Victor(7);
//	Joystick controller = new Joystick(2);
//	Joystick wheel = new Joystick(0);
//	Solenoid intakeSol = new Solenoid(2);
//	Solenoid intakeSol2 = new Solenoid(3);
//
//	public void Intakeout(){
//		intakeSol.set(true);
//		intakeSol2.set(true);
//	}
//	
//	public void Intakeback(){
//		intakeSol.set(false);
//		intakeSol2.set(false);
//	}
//	
//	public void Intakemotor(){
//		intake.set(1);
//		intake2.set(1);
//	}
//	
//	public void Bellypan(){
//		belly_roller.set(1);
//	}
//	public void Reverseintake(){
//		intake.set(-1);
//		intake2.set(-1);
//	}
//	public void Stopintake(){
//		intake.set(0);
//		intake2.set(0);
//	}
	Thread Intake = new Thread(){
		public void run(){
			if(RobotState.isAutonomous()){
//				Autonomous
			}else{
//				Intakeout();
				if (controller.getRawButton(3)){
					Intakemotor();
					Bellypan();
				}
				if (controller.getRawButton(5)){
					Reverseintake();
				}
//				if (controller.getRawButton(4)){
//					Intakeback();
//				}else{
					Intakeout();
				}
				
			}
			Timer.delay(.005);
			
		}
	};
}
