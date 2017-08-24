package com.eagleforce.robot.app;

import java.util.List;

import com.ctre.CANTalon.TrajectoryPoint;
import com.eagleforce.robot.model.MotionProfileConfiguration;
//import com.eagleforce.robot.model.MotionProfilePoint;
import com.eagleforce.robot.service.MotionProfileGenerationService;

public class ApplicationRunner {

	MotionProfileGenerationService mpService = new MotionProfileGenerationService();

	public void run() {
		// TODO: Extract to properties or something
		MotionProfileConfiguration mpc = new MotionProfileConfiguration();
		mpc.setMaxVel(3);
		mpc.setEndDistance(1);
		mpc.setInterval(10);
		mpc.setMaxAcc(10);
		
		List<TrajectoryPoint> tpList = mpService.generatePoints(mpc);
		
//		TODO: Automatically Check end position and adjust maxAcc accordingly 
		int i = 1;
		for (TrajectoryPoint tp : tpList) {
			System.out.println(" " + "\t" + "Velocity"+ "\t" + "Position"+ "\t" + "interval");
			System.out.println(i + "\t" + tp.velocity + "\t" + tp.position + "\t" + tp.timeDurMs );
			i++;
		}
	}

}
