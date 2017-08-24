package com.eagleforce.robot.service;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class ShooterService {

	CANTalon shoot1 = new CANTalon(2);
	CANTalon shoot2 = new CANTalon(3);
	IntermediateService ammo = new IntermediateService();
	Victor turret = new Victor(1);
	Encoder enc = new Encoder(5, 6);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	// ticks per revolution
	int t = 1024;
	double angPP = t / 360;

	public void spinTurret(double rotate) {

		if (Math.round(enc.getDistance()) < Math.round(angPP * rotate)) {
			turret.set(.25);
		} else {
			// converts units to encoder distance
			if (Math.round(enc.getDistance()) > Math.round(angPP * rotate)) {
				turret.set(-.25);
			} else {
				turret.set(0);
			}
		}

	}

	public void turretReset() {
		if (enc.getDistance() >= 360) {
			while (enc.getDistance() <= 180) {
				turret.set(-1);
			}

		}
	}

}
