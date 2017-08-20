package org.usfirst.frc.team2073.robot;
import edu.wpi.first.wpilibj.*;
import com.ctre.CANTalon;
//import com.ctre.CANTalon.FeedbackDevice;
//import com.ctre.CANTalon.TalonControlMode;

public class Shooter {
//	lmao u thought
	Joystick stick = new Joystick(0);
	Joystick controller = new Joystick(2);
	CANTalon shoot1 = new CANTalon(2);
	CANTalon shoot2 = new CANTalon(3);
//	CANTalon turret = new CANTalon(4);
	Intermediate ammo = new Intermediate();
	Victor turret = new Victor(1);
	Encoder enc = new Encoder(5,6);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
//	# of pulses per rotation of encoder
	int t = 3600;
//	changes encoder distance to degrees
	double angPP = t/360;
	
	public void Turn(){
//		rotate with vision code
	}
	
	public void Relative(){
		double rotate = controller.getDirectionDegrees();
//		distance per pulse is in degrees
		enc.setDistancePerPulse(t/360);
		if (controller.getDirectionDegrees()+gyro.getAngle()>-180 && controller.getDirectionDegrees()+gyro.getAngle()<0){
			if (enc.getDistance()<(angPP*rotate)){
				turret.set(.25);
			}else{
//				converts units to encoder distance
				if(enc.getDistance()>(angPP*rotate)){
					turret.set(-.25);
				}else{
					turret.set(0);
				}	
			}
		}
		if (controller.getDirectionDegrees()+gyro.getAngle()<180 && controller.getDirectionDegrees()+gyro.getAngle()>0){
			if (enc.getDistance()<(angPP*rotate)){
				turret.set(.25); 
			}else{
//				converts units to encoder distance
				if(enc.getDistance()>(angPP*rotate)){
					turret.set(-.25);
				}else{
					turret.set(0);
				}
			}
		}
	}
	
 
//		makes the turret follow the joystick on the controller
/*	public void ManTurn(){
		double rotate = controller.getDirectionDegrees();
//		distance per pulse is in degrees
		enc.setDistancePerPulse(t/360);
		if (controller.getDirectionDegrees()>-180 && controller.getDirectionDegrees()<0){
			if (enc.getDistance()<(angPP*rotate)){
				turret.set(.25);
			}else{
//				converts units to encoder distance
				if(enc.getDistance()>(angPP*rotate)){
					turret.set(-.25);
				}else{
					turret.set(0);
				}	
			}
		}
		if (controller.getDirectionDegrees()<180 && controller.getDirectionDegrees()>0){
			if (enc.getDistance()<(angPP*rotate)){
				turret.set(.25);
			}else{
//				converts units to encoder distance
				if(enc.getDistance()>(angPP*rotate)){
					turret.set(-.25);
				}else{
					turret.set(0);
				}
			}
		}
	}
*/	
	
	
	Thread Shoot = new Thread(){
		public void run(){
			if(RobotState.isAutonomous()){
//				AUTON
			}else{
//				TELEOP
				Relative();
			}
			Timer.delay(.005);
		}
	};
	
	
}
