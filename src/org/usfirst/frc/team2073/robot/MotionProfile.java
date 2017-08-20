package org.usfirst.frc.team2073.robot;

import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import java.util.List;

import com.ctre.CANTalon;



public class MotionProfile {
	
	CANTalon talon = new CANTalon(1);
//	make sure enough info is passed to talon before we execute profile
	int state = 0;
//	-1 is disabled, nonzero will count to 0, 0 is an error msg 
	int timeout =-1;
	boolean bStart = false;
//	controlling motion profile on/off
	public CANTalon.SetValueMotionProfile setValue = CANTalon.SetValueMotionProfile.Disable;
//	status of motion profile
	public CANTalon.MotionProfileStatus status = new CANTalon.MotionProfileStatus();
//	# points stored before profile beings
	public static final int minPointsIntalon = 5;
//	used to make sure a single point doesnt stop the profile
	public static final int  numLoopsTimeout =10;
	
	public MotionProfile(CANTalon talon) {
		this.talon = talon;
		talon.changeMotionControlFramePeriod(5);
	} 
//	clears buffer and resets profile (hard reset)
	public void reset(){
		talon.clearMotionProfileTrajectories();
		setValue = CANTalon.SetValueMotionProfile.Disable;
		state = 0;
		timeout = -1;
		bStart = false;
	}
	public void startFilling(){
		startFilling(GeneratedMotionProfile.Points, GeneratedMotionProfile.numPoints);
	}
	public void startFilling(List<TrajectoryPoint> tpList, int totalCnt){
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
		if (status.hasUnderrun){
			System.out.println("Has underrun");
			talon.clearMotionProfileHasUnderrun();
		}
		talon.clearMotionProfileTrajectories();
		for (TrajectoryPoint tp : tpList) {
			talon.pushMotionProfileTrajectory(tp);
		}
//		for (int i = 0; i <totalCnt; ++i){
//			point.position = profile[i][0];
//			point.velocity = profile[i][1];
//			point.timeDurMs = (int) profile[i][2];
//			point.profileSlotSelect = 0;
//			point.velocityOnly = false;
//			point.zeroPos = false;
//			if (i ==0)
//				point.zeroPos = true;
//			point.isLastPoint = false;
//			if ((i+1) == totalCnt)
//				point.isLastPoint = true;
//			
//			talon.pushMotionProfileTrajectory(point);
//			
//		}
	}
	public void control(){
		talon.getMotionProfileStatus(status);
		if (timeout < 0){
//			it's disabled
		}else{
			if (timeout == 0){
				System.out.println("timeout=0 erorr");
			}else{
				--timeout;
			}
		}
		if (talon.getControlMode() != TalonControlMode.MotionProfile){
//			not in motion profile mode
			state = 0;
			timeout = -1;
		}else{
			switch (state){
			case 0:
				if (bStart){
					bStart = false;
					setValue = CANTalon.SetValueMotionProfile.Disable;
					startFilling();
					state = 1;
					timeout = numLoopsTimeout;
				}
				break;
			case 1:
				
				if (status.btmBufferCnt > minPointsIntalon){
					setValue = CANTalon.SetValueMotionProfile.Enable;
					state = 2;
					timeout = numLoopsTimeout;
				}
				break;
			case 2:
				if (status.isUnderrun == false) {
					timeout = numLoopsTimeout;
				}
				if (status.activePointValid && status.activePoint.isLastPoint) {
					setValue = CANTalon.SetValueMotionProfile.Hold;
					state = 0;
					timeout = -1;
				}
				break;
			
			}
			
		}
	}
	public void startMotionProfile(){
		bStart = true;
	}
	CANTalon.SetValueMotionProfile getSetValue(){
		return setValue;
	}
}