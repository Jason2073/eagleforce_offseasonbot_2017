package org.usfirst.frc.team2073.robot.svc;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class GyroService {

	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	public double gyroAngle() {
		return gyro.getAngle();
	}
}