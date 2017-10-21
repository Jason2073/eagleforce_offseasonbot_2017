package org.usfirst.frc.team2073.robot.subsys;

import java.util.List;

import org.usfirst.frc.team2073.robot.svc.MotionProfileService;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import old.com.eagleforce.robot.model.MotionProfileConfiguration;
import old.com.eagleforce.robot.service.MotionProfileGenerationService;

/**
 *
 */
public class Drivetrain extends Subsystem {

	private CANTalon lMotor = new CANTalon(4);
	private CANTalon lMotor2 = new CANTalon(1);
	private CANTalon rMotor = new CANTalon(7);
	private CANTalon rMotor2 = new CANTalon(8);
	private Solenoid sol1 = new Solenoid(0);
	private Solenoid sol2 = new Solenoid(1);
	private MotionProfileService leftMp = new MotionProfileService(lMotor);
	private MotionProfileService rightMp = new MotionProfileService(rMotor);
	private MotionProfileGenerationService mpGen = new MotionProfileGenerationService();
	private MotionProfileConfiguration conf = new MotionProfileConfiguration();
	private List<TrajectoryPoint> basicProfile;
	double preTurn;


	public void setSlave() {
		lMotor2.changeControlMode(TalonControlMode.Follower);
		rMotor2.changeControlMode(TalonControlMode.Follower);
		rMotor2.set(rMotor.getDeviceID());
		lMotor2.set(lMotor.getDeviceID());
	}

	public double turnSense(double Ptart) {
		double sTurn = SmartDashboard.getNumber("Sense", .7) * Ptart * Ptart * Ptart
				+ Ptart * (1 - SmartDashboard.getNumber("Sense", .7));
		return sTurn;
	}

	public double inverse(double Start) {
		double inv = (Start - preTurn) * SmartDashboard.getNumber("Inverse", .2) + Start;
		return inv;
	}

	public void pTurn(double turn) {
		rMotor.set(-turn);
		lMotor.set(-turn);
	}

	public void move(double speed, double turn) {
		if (speed > 0) {
			rMotor.set(-inverse(speed) - (inverse(speed) * turnSense(turn)));
			lMotor.set(inverse(speed) + (inverse(speed) * turnSense(turn)));
		} else {
			rMotor.set(inverse(speed) + (inverse(speed) * turnSense(turn)));
			lMotor.set(-inverse(speed) - (inverse(speed) * turnSense(turn)));
		}
	}

	public void shiftHighGear() {
		sol1.set(true);
		sol2.set(true);
	}

	public void shiftLowGear() {
		sol1.set(false);
		sol2.set(true);
	}
	
	public void mpDrive(){
		leftMp.generalRun(basicProfile, true);
		rightMp.generalRun(basicProfile, true);
		
	}

    public void initDefaultCommand() {
    	setSlave();
    	conf.setEndDistance(100);
    	conf.setForwards(true);
    	conf.setInterval(10);
    	conf.setMaxAcc(50);
    	conf.setMaxVel(700);
    	basicProfile = mpGen.generatePoints(conf);
    }
}

