package org.usfirst.frc.team2073.robot.ctx;

import org.usfirst.frc.team2073.robot.subsys.DriveTrain;
import org.usfirst.frc.team2073.robot.subsys.TurretSubsystem;
import org.usfirst.frc.team2073.robot.svc.CameraService;

import com.ctre.CANTalon;

public class RobotMap {
	private DriveTrain driveTrain;
	private TurretSubsystem turret;
	
	// TODO: Extract talons, etc. here as PRIVATE variables and then
	// pass them into any classes that need them.
	private CANTalon talon = new CANTalon(4); // TODO: Extract talon port to constants

	protected RobotMap() {
//    	System.out.println("-> RobotMap constructor");
//    	System.out.println("<- RobotMap constructor");
		// Only allow instantiation from the AppContext
	}
	
	protected void init(CameraService camSvc) {
//    	System.out.println("-> RobotMap init()");
		driveTrain = new DriveTrain();
		turret = new TurretSubsystem();
		driveTrain.init(talon);
		turret.init(camSvc);
//    	System.out.println("<- RobotMap init()");
	}
	
	public DriveTrain getDriveTrain() {
//    	System.out.println("-> RobotMap getDriveTrain()");
//    	System.out.println("<- RobotMap getDriveTrain()");
		return driveTrain;
	}

	public TurretSubsystem getTurret() {
		return turret;
	}
}
