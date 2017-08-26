package com.eagleforce.robot.service;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class ShooterService {

	// private CANTalon shoot1 = new CANTalon(2);
	// private CANTalon shoot2 = new CANTalon(3);
	// private IntermediateService ammo = new IntermediateService();
	private Victor turret = new Victor(1);
	private Encoder enc = new Encoder(5, 6);

	public void moveTurret(double rotate) {
		enc.setDistancePerPulse(45 / 128);
		if (Math.round(rotate) > Math.round(enc.getDistance())) {
			turret.set(.25);
		} else if (Math.round(rotate) < Math.round(enc.getDistance())) {
			turret.set(-.25);
		} else {
			turret.set(0);
		}

	}

}
