package org.usfirst.frc.team2073.robot.util;

import java.util.List;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.MotionProfileStatus;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotionProfileHelper {
	public static void initTalon(CANTalon talon, double fGain) {
		talon.setF(fGain);
		talon.changeControlMode(TalonControlMode.MotionProfile);
		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talon.set(CANTalon.SetValueMotionProfile.Disable.value);
		talon.clearMotionProfileTrajectories();
	}
	
	public static void changeF(CANTalon talon, double f, String dashboardKey) {
		talon.setF(SmartDashboard.getNumber(dashboardKey, 1) + f);
		SmartDashboard.putNumber(dashboardKey, SmartDashboard.getNumber(dashboardKey, 1)+f);
	}

	public static void resetTalon(CANTalon talon) {
		talon.changeControlMode(TalonControlMode.MotionProfile);
		talon.set(CANTalon.SetValueMotionProfile.Disable.value);
		talon.clearMotionProfileTrajectories();
	}
	
	public static void pushPoints(CANTalon talon, List<TrajectoryPoint> trajPointList) {
		trajPointList.forEach(talon::pushMotionProfileTrajectory);
	}

	public static void processPoints(CANTalon talon) {
		talon.processMotionProfileBuffer();
		talon.set(CANTalon.SetValueMotionProfile.Enable.value);
	}

	public static void resetEnc(CANTalon talon) {
		talon.setPosition(0);
		talon.setEncPosition(0);
	}

	public static void stopTalon(CANTalon talon) {
		talon.set(CANTalon.SetValueMotionProfile.Disable.value);
		talon.clearMotionProfileTrajectories();
	}

	public static void checkDirection(CANTalon talon, boolean forwards) {
		talon.reverseOutput(!forwards);
	}

	public static void resetAndPushPoints(CANTalon talon, List<TrajectoryPoint> tpList, boolean isForwards) {
		resetTalon(talon);
		pushPoints(talon, tpList);
		checkDirection(talon, isForwards);
	}

	public static boolean isFinished(CANTalon talon) {
		MotionProfileStatus talonStatus = new MotionProfileStatus();
		talon.getMotionProfileStatus(talonStatus);
		return talonStatus.topBufferCnt == 0 && talonStatus.btmBufferCnt == 0;
	}
}