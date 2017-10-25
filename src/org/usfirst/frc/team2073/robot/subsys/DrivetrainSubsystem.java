package org.usfirst.frc.team2073.robot.subsys;

import java.util.List;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.cmd.DriveWithJoystickCommand;
import org.usfirst.frc.team2073.robot.conf.AppConstants.DashboardKeys;
import org.usfirst.frc.team2073.robot.domain.MotionProfileConfiguration;
import org.usfirst.frc.team2073.robot.util.MotionProfileGenerator;
import org.usfirst.frc.team2073.robot.util.MotionProfileHelper;
import org.usfirst.frc.team2073.robot.util.TalonHelper;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainSubsystem extends Subsystem {
	public static final double DEFAULT_INVERSE = .2;
	public static final double DEFAULT_SENSE = .7;

	private final CANTalon leftMotor;
	private final CANTalon leftMotorSlave;
	private final CANTalon rightMotor;
	private final CANTalon rightMotorSlave;
	private final Solenoid solenoid1;
	private final Solenoid solenoid2;
	private double MotionProfileDriveDistance = 0;
	private double MotionProfilePointTurnAngle = 0;

	public double getMotionProfilePointTurnAngle() {
		return MotionProfilePointTurnAngle;
	}

	public void setMotionProfilePointTurnAngle(double motionProfilePointTurnAngle) {
		MotionProfilePointTurnAngle = motionProfilePointTurnAngle;
	}

	public double getMotionProfileDriveDistance() {
		return MotionProfileDriveDistance;
	}

	public void setMotionProfileDriveDistance(double motionProfileDriveDistance) {
		MotionProfileDriveDistance = motionProfileDriveDistance;
	}

	List<TrajectoryPoint> trajPointList;
	MotionProfileConfiguration config = new MotionProfileConfiguration();
	private double preTurn;

	public DrivetrainSubsystem() {
		leftMotor = RobotMap.getLeftMotor();
		leftMotorSlave = RobotMap.getLeftMotorSlave();
		rightMotor = RobotMap.getRightMotor();
		rightMotorSlave = RobotMap.getRightMotorSlave();
		solenoid1 = RobotMap.getDriveSolenoid1();
		solenoid2 = RobotMap.getDriveSolenoid2();

		setSlaves();
		shiftLowGear();
		generateTrajPoints();
		configEncoders();

		// TODO: Extract to constants
		LiveWindow.addActuator("Drivetrain", "Left Motor", leftMotor);
		LiveWindow.addActuator("Drivetrain", "Left Motor Slave", leftMotorSlave);
		LiveWindow.addActuator("Drivetrain", "Left Motor", rightMotor);
		LiveWindow.addActuator("Drivetrain", "Left Motor Slave", rightMotorSlave);
		LiveWindow.addActuator("Drivetrain", "Solenoid 1", solenoid1);
		LiveWindow.addActuator("Drivetrain", "Solenoid 2", solenoid2);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystickCommand());
	}

	private void setSlaves() {
		TalonHelper.setFollowerOf(leftMotorSlave, leftMotor);
		TalonHelper.setFollowerOf(rightMotorSlave, rightMotor);
	}

	private void configEncoders() {
		leftMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		rightMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
	}

	private void generateTrajPoints() {
		config.setEndDistance(100);
		config.setForwards(true);
		config.setInterval(10);
		config.setMaxAcc(50);
		config.setMaxVel(700);
		trajPointList = MotionProfileGenerator.generatePoints(config);
	}

	public List<TrajectoryPoint> generatePoints(MotionProfileConfiguration conf) {
		return trajPointList = MotionProfileGenerator.generatePoints(conf);
	}

	public MotionProfileConfiguration driveForwardConfig(double linearDistInInches) {
		MotionProfileConfiguration configuration = new MotionProfileConfiguration();
		double rotationDist = linearDistInInches / RobotMap.getWheelDiameter();
		configuration.setEndDistance(rotationDist);
		configuration.setInterval(10);
		configuration.setMaxVel(RobotMap.getAutonomousMaxVelocity());
		configuration.setMaxAcc(RobotMap.getAutonomousMaxAcceleratoin());
		configuration.setVelocityOnly(false);
		return configuration;
	}

	public MotionProfileConfiguration pointTurnConfig(double angleTurn) {
		MotionProfileConfiguration configuration = new MotionProfileConfiguration();
		double rotationDist = (angleTurn / 360) * (RobotMap.getRobotWidth() * Math.PI);
		configuration.setEndDistance(rotationDist);
		configuration.setInterval(10);
		configuration.setMaxVel(RobotMap.getAutonomousMaxVelocity());
		configuration.setMaxAcc(RobotMap.getAutonomousMaxAcceleratoin());
		configuration.setVelocityOnly(false);
		return configuration;
	}

	public double turnSense(double ptart) {
		double sense = SmartDashboard.getNumber(DashboardKeys.SENSE, DEFAULT_SENSE);
		return sense * ptart * ptart * ptart + ptart * (1 - sense);
	}

	public double inverse(double start) {
		double inverse = SmartDashboard.getNumber(DashboardKeys.INVERSE, DEFAULT_INVERSE);
		return (start - preTurn) * inverse + start;
	}

	public void pointTurn(double turn) {
		rightMotor.set(-turn);
		leftMotor.set(-turn);
	}

	public void move(double speed, double turn) {
		rightMotor.changeControlMode(TalonControlMode.PercentVbus);
		leftMotor.changeControlMode(TalonControlMode.PercentVbus);

		double rightSide = -(inverse(speed) - (inverse(speed) * turnSense(turn)));
		double leftSide = inverse(speed) + (inverse(speed) * turnSense(turn));

		if (RobotMap.isBallIntakeForwards()) {
			rightMotor.set(rightSide);
			leftMotor.set(leftSide);
		} else {
			leftMotor.set(rightSide);
			rightMotor.set(leftSide);
		}
	}

	public void shiftHighGear() {
		solenoid1.set(true);
		solenoid2.set(true);
	}

	public void shiftLowGear() {
		solenoid1.set(false);
		solenoid2.set(false);
	}

	public void resetMotionProfiling(List<TrajectoryPoint> trajectoryList, boolean leftForwards, boolean rightForwards) {
		MotionProfileHelper.resetAndPushPoints(leftMotor, trajectoryList, false);
		MotionProfileHelper.resetAndPushPoints(rightMotor, trajectoryList, true);
	}
	

	public void processMotionProfiling() {
		MotionProfileHelper.processPoints(leftMotor);
		MotionProfileHelper.processPoints(rightMotor);
	}

	public void stopMotionProfiling() {
		MotionProfileHelper.stopTalon(leftMotor);
		MotionProfileHelper.stopTalon(rightMotor);
	}

	public boolean isMotionProfilingFinished() {
		// TODO: decide whether to check if both are finished or if at least one is
		// finished
		return MotionProfileHelper.isFinished(leftMotor) && MotionProfileHelper.isFinished(rightMotor);
	}

	public void autonDriveForward(double linearDistInInches) {
		resetMotionProfiling(generatePoints(driveForwardConfig(linearDistInInches)), false, true);
	}
	public void autonPointTurn(double angle) {
		resetMotionProfiling(generatePoints(pointTurnConfig(angle)), false, false);
	}
	
	public void autonDriveBackward(double linearDistInInches) {
		resetMotionProfiling(generatePoints(driveForwardConfig(linearDistInInches)), true, false);
	}

}
