package com.eagleforce.robot.model;

public class MotionProfileConfiguration {

	private double maxVel = 3;
	private double endDistance = 1.;
	private int interval = 10;
	private double maxAcc = 15;
	
	public double getMaxVel() {
		return maxVel;
	}
	public void setMaxVel(double maxVel) {
		this.maxVel = maxVel;
	}
	public double getEndDistance() {
		return endDistance;
	}
	public void setEndDistance(double endDistance) {
		this.endDistance = endDistance;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public double getMaxAcc() {
		return maxAcc;
	}
	public void setMaxAcc(double maxAcc) {
		this.maxAcc = maxAcc;
	}


}
