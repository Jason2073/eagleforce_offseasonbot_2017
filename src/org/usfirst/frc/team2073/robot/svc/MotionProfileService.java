package org.usfirst.frc.team2073.robot.svc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotionProfileService {

		private CANTalon talon;
		
		public MotionProfileService(CANTalon talon) {
			this.talon = talon;
		}

		public void motionProfileInit() {
			talon.setF(SmartDashboard.getNumber("Fgain", .7871));
			talon.changeControlMode(TalonControlMode.MotionProfile);
			talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			talon.set(CANTalon.SetValueMotionProfile.Disable.value);
			talon.clearMotionProfileTrajectories();	
		}
		public void motionProfileReset(){
			talon.changeControlMode(TalonControlMode.MotionProfile);
			talon.set(CANTalon.SetValueMotionProfile.Disable.value);
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
		
		
		public void checkDirection(boolean forwards){
			talon.reverseOutput(forwards);
		}
		
		public void generalRun(List<TrajectoryPoint> tpList, boolean isForwards){
			motionProfileReset();
			pushPoints(tpList);
			checkDirection(isForwards);
			Notifier notifer = new Notifier(new MotionProfileBufferProcesser(talon));
			notifer.startPeriodic(tpList.get(1).timeDurMs/2);
			for (TrajectoryPoint tPoint : tpList) {
				if (tPoint.isLastPoint)
					System.out.println("DONE Running Profile");
				talon.set(CANTalon.SetValueMotionProfile.Enable.value);
			}
			notifer.stop();
			stopMotionProfile();
		}
}
