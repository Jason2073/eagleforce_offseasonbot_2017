package org.usfirst.frc.team2073.robot.subsys;

import java.util.List;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.cmd.drive.DriveWithJoystickCommand;
import org.usfirst.frc.team2073.robot.conf.AppConstants.DashboardKeys;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.Drivetrain;
import org.usfirst.frc.team2073.robot.domain.MotionProfileConfiguration;
import org.usfirst.frc.team2073.robot.util.MotionProfileGenerator;
import org.usfirst.frc.team2073.robot.util.MotionProfileHelper;
import org.usfirst.frc.team2073.robot.util.TalonHelper;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
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
	private final ADXRS450_Gyro gyro;

	public DrivetrainSubsystem() {
		leftMotor = RobotMap.getLeftMotor();
		leftMotorSlave = RobotMap.getLeftMotorSlave();
		rightMotor = RobotMap.getRightMotor();
		rightMotorSlave = RobotMap.getRightMotorSlave();
		solenoid1 = RobotMap.getDriveSolenoid1();
		solenoid2 = RobotMap.getDriveSolenoid2();
		gyro = RobotMap.getGyro();

		setSlaves();
		shiftLowGear();
		configEncoders();
		initTalons();
		enableBrakeMode();

		LiveWindow.addActuator(Drivetrain.NAME, Drivetrain.ComponentNames.LEFT_MOTOR, leftMotor);
		LiveWindow.addActuator(Drivetrain.NAME, Drivetrain.ComponentNames.LEFT_MOTOR_SLAVE, leftMotorSlave);
		LiveWindow.addActuator(Drivetrain.NAME, Drivetrain.ComponentNames.RIGHT_MOTOR, rightMotor);
		LiveWindow.addActuator(Drivetrain.NAME, Drivetrain.ComponentNames.RIGHT_MOTOR_SLAVE, rightMotorSlave);
		LiveWindow.addActuator(Drivetrain.NAME, Drivetrain.ComponentNames.SOLENOID_1, solenoid1);
		LiveWindow.addActuator(Drivetrain.NAME, Drivetrain.ComponentNames.SOLENOID_2, solenoid2);
		LiveWindow.addActuator(Drivetrain.NAME, Drivetrain.ComponentNames.GYRO, gyro);
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
		leftMotor.configEncoderCodesPerRev(1024);
		rightMotor.configEncoderCodesPerRev(1024);
	}

	private void initTalons() {
		MotionProfileHelper.initTalon(leftMotor);
		MotionProfileHelper.initTalon(rightMotor);
	}

	public MotionProfileConfiguration driveStraightConfig(double linearDistInInches) {
		MotionProfileConfiguration configuration = new MotionProfileConfiguration();
		// TODO: check if high gear is enabled
		double rotationDist = (8 * Drivetrain.LOW_GEAR_RATIO * linearDistInInches) / (Drivetrain.WHEEL_DIAMETER * 5);
		configuration.setEndDistance(rotationDist);
		configuration.setInterval(10);
		configuration.setMaxVel(Drivetrain.AUTONOMOUS_MAX_VELOCITY);
		configuration.setMaxAcc(Drivetrain.AUTONOMOUS_MAX_ACCELERATION);
		configuration.setVelocityOnly(false);
		return configuration;
	}

	public MotionProfileConfiguration pointTurnConfig(double angleTurn) {
		MotionProfileConfiguration configuration = new MotionProfileConfiguration();
		double linearDist = (angleTurn / 360) * (Drivetrain.ROBOT_WIDTH * Math.PI);
		double rotationDist = (8 * Drivetrain.LOW_GEAR_RATIO * linearDist) / (Drivetrain.WHEEL_DIAMETER * 5);
		configuration.setEndDistance(rotationDist);
		configuration.setInterval(10);
		configuration.setMaxVel(Drivetrain.AUTONOMOUS_MAX_VELOCITY);
		configuration.setMaxAcc(Drivetrain.AUTONOMOUS_MAX_ACCELERATION);
		configuration.setVelocityOnly(false);
		return configuration;
	}

	public double turnSense(double ptart) {
		double sense = SmartDashboard.getNumber(DashboardKeys.SENSE, DEFAULT_SENSE);
		return sense * ptart * ptart * ptart + ptart * (1 - sense);
	}

	public double inverse(double start) {
		double inverse = SmartDashboard.getNumber(DashboardKeys.INVERSE, DEFAULT_INVERSE);
		return (start) * inverse + start;
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
		solenoid1.set(true);// TODO: rename misleading
								// shiftHighGear/shiftLowGear names
		solenoid2.set(false);
	}

	public void shiftLowGear() {
		solenoid1.set(false);
		solenoid2.set(true);
	}

	public void resetMotionProfiling(MotionProfileConfiguration config, boolean leftForwards, boolean rightForwards) {
		List<TrajectoryPoint> trajPointList = MotionProfileGenerator.generatePoints(config);
		MotionProfileHelper.resetAndPushPoints(leftMotor, trajPointList, leftForwards);
		MotionProfileHelper.resetAndPushPoints(rightMotor, trajPointList, rightForwards);
		leftMotor.setPosition(0);
		rightMotor.setPosition(0);
		MotionProfileHelper.setDefaultF(leftMotor);
		MotionProfileHelper.setFRightSide(rightMotor);

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
		// TODO: decide whether to check if both are finished or if at least one
		// is finished
		return MotionProfileHelper.isFinished(leftMotor) && MotionProfileHelper.isFinished(rightMotor);
	}

	public void autonDriveForward(double linearDistInInches) {
		resetMotionProfiling(driveStraightConfig(linearDistInInches), true, false);
	}

	public void autonPointTurn(double angle) {
		if (angle > 0)
			resetMotionProfiling(pointTurnConfig(Math.abs(angle)), false, false);
		else
			resetMotionProfiling(pointTurnConfig(Math.abs(angle)), true, true);

	}

	public void autonDriveBackward(double linearDistInInches) {
		resetMotionProfiling(driveStraightConfig(linearDistInInches), false, true);
	}

	public void stopBrakeMode() {
		leftMotor.enableBrakeMode(false);
		rightMotor.enableBrakeMode(false);
	}

	public void enableBrakeMode() {
		leftMotor.enableBrakeMode(true);
		rightMotor.enableBrakeMode(true);
	}

	public double getGyroAngle() {
		return gyro.getAngle();
	}

	public void changeFGain(CANTalon motor, double value) {
		MotionProfileHelper.changeF(motor, value);
	}

	public void adjustF(double startingGryo) {
		if (getGyroAngle() < startingGryo - 2) {
			changeFGain(leftMotor, .01);
		} else if (getGyroAngle() > startingGryo + 2) {
			changeFGain(rightMotor, .01);
		}
	}
}
