package com.eagleforce.robot.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;
import com.eagleforce.robot.model.CameraMessage;
import com.eagleforce.robot.model.MotionProfileConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotionProfileShooterService {
	
	private CANTalon talon = new CANTalon(5);

	private MotionProfileGenerationService mpgSvc = new MotionProfileGenerationService();
	private CameraService cam = CameraService.getInstance();
	private MotionProfileConfiguration conf1;

	public List<TrajectoryPoint> turretProfile;
	
	
	enum MotionProfileState {
		INACTIVE, FILLING_BUFFER, RUNNING_PROFILE
	}

	private List<CANTalon> ctList = new ArrayList<>();
	private CANTalon.MotionProfileStatus talonStatus = new CANTalon.MotionProfileStatus();
	private boolean bufferFilled = false;
	private double MAX_VELOCITY = 300;
	private int INTERVAL = 5;
	private double MAX_ACCELERATION = 10;
	private double GEAR_RATIO = 10; /* this to one */

	public MotionProfileShooterService() {
		SmartDashboard.putNumber("Fgain", .7871);
		for (CANTalon ct : ctList) {
			ct.changeMotionControlFramePeriod(INTERVAL / 2);
		}

	}

	public void generateProfile() {
		conf1 = new MotionProfileConfiguration();
		conf1.setMaxVel(MAX_VELOCITY);
		conf1.setInterval(INTERVAL);
		conf1.setEndDistance(profileDistanceToTarget());
		conf1.setMaxAcc(MAX_ACCELERATION);
		turretProfile = mpgSvc.generatePoints(conf1);
	}

	public double profileDistanceToTarget() {
		double dist = 0;
		dist = GEAR_RATIO * (cam.lastCameraMessage().getAngleToTarget() / 360);
		return dist;
	}

	public void motionProfileInit() {
		talon.setF(SmartDashboard.getNumber("Fgain", .7871));
		talon.changeControlMode(TalonControlMode.MotionProfile);
		talon.set(CANTalon.SetValueMotionProfile.Disable.value);
		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talon.clearMotionProfileTrajectories();
	}

	public void pushPoints(List<TrajectoryPoint> tpList) {
		Queue<TrajectoryPoint> tpQueue = new LinkedList<>(tpList);
		for (TrajectoryPoint tp : tpQueue) {
			talon.pushMotionProfileTrajectory(tp);
		}
	}

	public void processPoints() {
		talon.processMotionProfileBuffer();
		talon.set(CANTalon.SetValueMotionProfile.Enable.value);
	}

	public void resetEnc() {
		talon.setPosition(0);
		talon.setEncPosition(0);
	}

	public void stopMotionProfile() {
		talon.set(CANTalon.SetValueMotionProfile.Disable.value);
		talon.clearMotionProfileTrajectories();
	}

	public void printTalonInfo() {
		System.out.println("Pos: " + talon.getPosition() + "topBuffer: " + talonStatus.topBufferCnt);
	}

	public void checkDirection(boolean forwards) {
		talon.reverseOutput(forwards);
	}

	private boolean isToTheRight() {
		boolean direction = false;
		if (cam.lastCameraMessage().getAngleToTarget() > 0)
			direction = true;
		else
			direction = false;
		return direction;
	}
	
	public double bufferedPoints(){
		double points = talonStatus.topBufferCnt;
		return points;
	}

}
