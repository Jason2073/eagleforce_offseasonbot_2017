package org.usfirst.frc.team2073.robot.svc;

import java.util.List;

import com.ctre.CANTalon;

public class MotionProfileBufferProcesser implements java.lang.Runnable {
	
//		private Notifier _notifer = new Notifier(new CANTalonBufferProcessor(ctList));
	
		private List<CANTalon> ctList;
		public MotionProfileBufferProcesser (List<CANTalon> ctList) {
			this.ctList = ctList;
		}
		
	    public void run() {
	    	for(CANTalon ct : ctList) {
	    		ct.processMotionProfileBuffer();
	    	}
	    }
	

}
