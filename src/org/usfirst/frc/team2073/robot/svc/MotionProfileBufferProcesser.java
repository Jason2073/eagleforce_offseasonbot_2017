package org.usfirst.frc.team2073.robot.svc;

import com.ctre.CANTalon;

public class MotionProfileBufferProcesser implements java.lang.Runnable {
	
//		private Notifier _notifer = new Notifier(new CANTalonBufferProcessor(ctList));
		private CANTalon talon;
		
		public MotionProfileBufferProcesser (CANTalon talon) {
			this.talon = talon;
		}
		
	    public void run() {
	    		talon.processMotionProfileBuffer();
	    }
	

}
