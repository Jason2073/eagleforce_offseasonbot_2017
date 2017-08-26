package com.eagleforce.robot.service;

import java.util.List;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TrajectoryPoint;
import com.eagleforce.robot.model.MotionProfileConfiguration;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class GearIntakeService {

	private MotionProfileGenerationService mps = new MotionProfileGenerationService();
	private MotionProfileConfiguration conf1;
	private MotionProfileConfiguration conf2;
	private MotionProfileConfiguration conf3;
	private MotionProfileConfiguration conf4;
	private MotionProfileConfiguration conf5;
	private MotionProfileConfiguration conf6;

	private List<TrajectoryPoint> upToDownTpList;
	private List<TrajectoryPoint> upToPlaceTpList;
	private List<TrajectoryPoint> placeToUpTpList;
	private List<TrajectoryPoint> placeToDownTpList;
	private List<TrajectoryPoint> downToPlaceTpList;
	private List<TrajectoryPoint> downToUpTpList;

	private Victor intakeMotor = new Victor(9);
	private CANTalon talon = new CANTalon(1);
	private DigitalInput lightSensor = new DigitalInput(3);

	// private CANTalon.MotionProfileStatus status = new
	// CANTalon.MotionProfileStatus();

	public GearIntakeService() {

		talon.setF(.7871);

		conf1 = new MotionProfileConfiguration();
		// up to down
		conf1.setForwards(false);
		conf1.setMaxVel(3);
		conf1.setInterval(5);
		conf1.setEndDistance(.25);
		conf1.setMaxAcc(60);

		conf2 = new MotionProfileConfiguration();
		// up to place
		conf2.setForwards(false);
		conf2.setMaxVel(3);
		conf2.setInterval(5);
		conf2.setEndDistance(.125);
		conf2.setMaxAcc(60);

		conf3 = new MotionProfileConfiguration();
		// place to up (reverse)
		conf3.setForwards(false);
		conf3.setMaxVel(3);
		conf3.setInterval(5);
		conf3.setEndDistance(.125);
		conf3.setMaxAcc(60);

		conf4 = new MotionProfileConfiguration();
		// place to down
		conf4.setForwards(true);
		conf4.setMaxVel(3);
		conf4.setInterval(5);
		conf4.setEndDistance(.125);
		conf4.setMaxAcc(60);

		conf5 = new MotionProfileConfiguration();
		// down to place (reverse)
		conf5.setForwards(false);
		conf5.setMaxVel(3);
		conf5.setInterval(5);
		conf5.setEndDistance(.125);
		conf5.setMaxAcc(60);

		conf6 = new MotionProfileConfiguration();
		// down to up (reverse)
		conf6.setForwards(false);
		conf6.setMaxVel(3);
		conf6.setInterval(5);
		conf6.setEndDistance(.25);
		conf6.setMaxAcc(60);

		upToDownTpList = mps.generatePoints(conf1);
		upToPlaceTpList = mps.generatePoints(conf2);
		placeToUpTpList = mps.generatePoints(conf3);
		placeToDownTpList = mps.generatePoints(conf4);
		downToPlaceTpList = mps.generatePoints(conf5);
		downToUpTpList = mps.generatePoints(conf6);

	}

	public void gearIn() {
		intakeMotor.set(-1);
	}

	public void gearOut() {
		intakeMotor.set(1);
	}

	public void gearHold() {
		intakeMotor.set(.1);
	}

	public void gearStop() {
		intakeMotor.set(0);
	}

	public double getApproxAngle() {
		double angle;
		angle = Math.round(talon.getPosition());
		return angle;
	}

	public void runDownToUp() {

	}

	// Getters / Setters
	// =======================================================================================================================================================

	public List<TrajectoryPoint> getUpToDownTpList() {
		return upToDownTpList;
	}

	public void setUpToDownTpList(List<TrajectoryPoint> upToDownTpList) {
		this.upToDownTpList = upToDownTpList;
	}

	public List<TrajectoryPoint> getUpToPlaceTpList() {
		return upToPlaceTpList;
	}

	public void setUpToPlaceTpList(List<TrajectoryPoint> upToPlaceTpList) {
		this.upToPlaceTpList = upToPlaceTpList;
	}

	public List<TrajectoryPoint> getPlaceToUpTpList() {
		return placeToUpTpList;
	}

	public void setPlaceToUpTpList(List<TrajectoryPoint> placeToUpTpList) {
		this.placeToUpTpList = placeToUpTpList;
	}

	public List<TrajectoryPoint> getPlaceToDownTpList() {
		return placeToDownTpList;
	}

	public void setPlaceToDownTpList(List<TrajectoryPoint> placeToDownTpList) {
		this.placeToDownTpList = placeToDownTpList;
	}

	public List<TrajectoryPoint> getDownToPlaceTpList() {
		return downToPlaceTpList;
	}

	public void setDownToPlaceTpList(List<TrajectoryPoint> downToPlaceTpList) {
		this.downToPlaceTpList = downToPlaceTpList;
	}

	public List<TrajectoryPoint> getDownToUpTpList() {
		return downToUpTpList;
	}

	public void setDownToUpTpList(List<TrajectoryPoint> downToUpTpList) {
		this.downToUpTpList = downToUpTpList;
	}

	public boolean lightSensor() {
		boolean sense;
		sense = lightSensor.get();
		return sense;
	}

}
