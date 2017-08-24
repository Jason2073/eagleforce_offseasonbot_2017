package org.usfirst.frc.team2073.robot;
import edu.wpi.first.wpilibj.*;

public class Intermediate {
	
//	Victor main = new Victor(8);
	Joystick controller = new Joystick(2);
//	
//	public void intermediateOn(){
//		main.set(1);
//	}
//	
//	public void intermediateoff(){
//		main.set(0);
//	}
//	
	Thread Transition = new Thread(){
		public void run(){
			if(RobotState.isAutonomous()){
//				autonomous
			}else{
				if(controller.getRawButton(2)){
					intermediateOn();
				}else{
					intermediateoff();
				}
			}
			Timer.delay(.005);
		}
	};

}
