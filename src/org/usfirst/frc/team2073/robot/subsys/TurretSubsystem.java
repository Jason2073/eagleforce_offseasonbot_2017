package org.usfirst.frc.team2073.robot.subsys;


import java.awt.List;
import java.util.ArrayList;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.domain.MotionProfileConfiguration;
import org.usfirst.frc.team2073.robot.util.MotionProfileGenerator;
import org.usfirst.frc.team2073.robot.util.MotionProfileHelper;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class TurretSubsystem extends Subsystem {
	
	private CANTalon turretPos = RobotMap.getTurretPosition();
	private CANTalon shooter1 = RobotMap.getShooter1();
	private CANTalon shooter2 = RobotMap.getShooter2();
	private MotionProfileConfiguration conf = new MotionProfileConfiguration();
	private static final double F = 4.0;
	private static final double P = 30;
	private static final double I = 0;
	private static final double D = 170;
	private static final double MAX_ACCELERATION = 800;
	private static final double MAX_VELOCITY = 4000;
	private java.util.List<TrajectoryPoint> accelerate;
	
	public void turretMove(int angle){
		turretPos.changeControlMode(TalonControlMode.Position);
		double rotations = angle/360;
		turretPos.set(rotations);
	}
	
	public void accelerateToRPM(){
		MotionProfileHelper.resetAndPushPoints(shooter1, accelerate, true);
		while(!MotionProfileHelper.isFinished(shooter1)){
			MotionProfileHelper.processPoints(shooter1);
		}
		
	}
	
	public void sustainRPM(){
//		TODO: FIND A method for this
	}
	
	
    public void initDefaultCommand() {
    	SmartDashboard.putNumber("Set F", F);
    	SmartDashboard.putNumber("Set p", P);
    	SmartDashboard.putNumber("Set i", I);
    	SmartDashboard.putNumber("Set d", D);
    	SmartDashboard.putNumber("RPM", MAX_VELOCITY);
    	turretPos.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        turretPos.reverseSensor(false);
        turretPos.configEncoderCodesPerRev(RobotMap.getTurretPositionCodesPerRev());
        turretPos.configNominalOutputVoltage(+0.0f, -0.0f);
        turretPos.configPeakOutputVoltage(+12.0f, 0.0f);
        turretPos.setProfile(0);
        turretPos.setF(SmartDashboard.getNumber("Set F", F));
        turretPos.setP(SmartDashboard.getNumber("Set P", P));
        turretPos.setI(SmartDashboard.getNumber("Set I", I));
        turretPos.setD(SmartDashboard.getNumber("Set F", D));
    	
        conf.setVelocityOnly(true);
        conf.setForwards(true);
        conf.setInterval(10);
        conf.setMaxAcc(MAX_ACCELERATION);
        conf.setMaxVel(SmartDashboard.getNumber("RPM", MAX_VELOCITY)*60);
        accelerate = MotionProfileGenerator.generatePoints(conf);
        
        shooter2.changeControlMode(TalonControlMode.Follower);
        shooter2.set(shooter1.getDeviceID());
        
    }
}

