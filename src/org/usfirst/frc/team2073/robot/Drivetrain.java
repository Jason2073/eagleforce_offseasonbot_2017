package org.usfirst.frc.team2073.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;



public class Drivetrain{
	
	Victor l_motor = new Victor(0);
	Victor l_motor2 = new Victor(1);
	Victor r_motor = new Victor(2);
	Victor r_motor2 = new Victor(3);
	Joystick pwr_joystick = new Joystick(0);
	Joystick wheel = new Joystick(1);
	Encoder l_enc = new Encoder(3,4);
	Encoder r_enc = new Encoder(1,2);
	Solenoid Sol1 = new Solenoid(0);
	Solenoid Sol2 = new Solenoid(1);
	
	double preTurn;

	/**
	 * Standard drive, contains encoder distance
	 * TODO Add distance per pulse
	 */
	public Drivetrain(){
		l_enc.setDistancePerPulse(.01227);
		r_enc.setDistancePerPulse(.01227);
	}
	public double TurnSense(double Ptart){
		double sTurn;
		sTurn = SmartDashboard.getNumber("Sense", .7)*Ptart*Ptart*Ptart+Ptart*(1-SmartDashboard.getNumber("Sense", .7));
		return sTurn;
	}
	
	public double Inverse(double Start){
		double inv;
		inv = (Start-preTurn)*SmartDashboard.getNumber("Inverse", .2)+Start;
		return inv;
	}
	
	public void pTurn(double turn){
		r_motor.set(-turn);
		l_motor.set(-turn);
		r_motor2.set(-turn);
		l_motor2.set(-turn);
	}
	
	public void steerwheel(double speed, double turn){
		r_motor.set(-(Inverse(speed) - (Inverse(speed)*TurnSense(turn))));
		l_motor.set(Inverse(speed) + (Inverse(speed)*TurnSense(turn)));
		r_motor2.set(-(Inverse(speed) - (Inverse(speed)*TurnSense(turn))));
		l_motor2.set(Inverse(speed) + (Inverse(speed)*TurnSense(turn)));
	}
	
	Thread MainDrive = new Thread(){
		public void run(){
			
			while (RobotState.isEnabled()||RobotState.isDisabled()){
				while(RobotState.isDisabled()&&RobotState.isAutonomous()){
//					Auton prep/startup
				}
				if (RobotState.isAutonomous()){
//					AUTONOMOUS
				}else{
					if(wheel.getRawButton(1)){
						pTurn(wheel.getX());
					}else{
						steerwheel(pwr_joystick.getY(), wheel.getX());
					}
					if(pwr_joystick.getRawButton(4)){
//						change to shiftHighGear
						Sol1.set(true);
						Sol2.set(false);
					}else{
						Sol1.set(false);
						Sol2.set(true);
					}
				}
				Timer.delay(.005);
			}
		}
	};
}