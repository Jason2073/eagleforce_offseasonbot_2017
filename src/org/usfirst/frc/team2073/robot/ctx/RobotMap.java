package org.usfirst.frc.team2073.robot.ctx;

import org.usfirst.frc.team2073.robot.conf.AppConstants.RobotPorts;
import org.usfirst.frc.team2073.robot.subsys.DriveTrainSubsystem;
import org.usfirst.frc.team2073.robot.subsys.TurretSubsystem;

import com.ctre.CANTalon;

public class RobotMap {
	private static CANTalon driveTrainTalon;

	private static DriveTrainSubsystem driveTrain;
	private static TurretSubsystem turret;
	
	public static void init() {
		driveTrainTalon = new CANTalon(RobotPorts.DRIVE_TRAIN_TALON);
		
		driveTrain = new DriveTrainSubsystem();
		turret = new TurretSubsystem();
	}

	public static CANTalon getDriveTrainTalon() {
		return driveTrainTalon;
	}
	
	public static DriveTrainSubsystem getDriveTrain() {
		return driveTrain;
	}

	public static TurretSubsystem getTurret() {
		return turret;
	}
}
