package org.usfirst.frc.team2073.robot.ctx;

import org.usfirst.frc.team2073.robot.svc.CameraService;

public class AppContext {
	// Singleton members/methods
	// ====================================================================================================
	private static AppContext instance = null;
	private static Object instanceLock = new Object();
	
	
	public static AppContext getInstance() {
//    	System.out.println("-> AppContext getInstance()");
		synchronized (instanceLock) {
			if(instance == null)
				instance = new AppContext();
		}
//		System.out.println("-> AppContext getInstance()");
			
    	return instance;
	}

	private AppContext() {
//    	System.out.println("-> AppContext constructor");
		// private
//    	System.out.println("<- AppContext constructor");
	}
	
	// Instance members/methods
	// ====================================================================================================

	private RobotMap robotMap;
	private OI oi;
	
	private CameraService camSvc;
	
	public void init() {
		robotMap = new RobotMap();
		oi = new OI();
		camSvc = CameraService.getInstance(); // TODO: Should this be a subsystem instead of service?
		
		robotMap.init(camSvc);
		oi.init();
	}
	
	public OI getOi() {
		return oi;
	}

	public RobotMap getRobotMap() {
//    	System.out.println("-> AppContext getRobotMap");
//    	System.out.println("<- AppContext getRobotMap");
		return robotMap;
	}
	
}
