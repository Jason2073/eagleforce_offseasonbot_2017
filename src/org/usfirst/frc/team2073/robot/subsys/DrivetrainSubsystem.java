package org.usfirst.frc.team2073.robot.subsys;

import java.util.List;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.cmd.DriveWithJoystickCommand;
import org.usfirst.frc.team2073.robot.domain.MotionProfileConfiguration;
import org.usfirst.frc.team2073.robot.util.MotionProfileGenerator;
import org.usfirst.frc.team2073.robot.util.MotionProfileHelper;
import org.usfirst.frc.team2073.robot.util.TalonHelper;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainSubsystem extends Subsystem {
	private final CANTalon leftMotor;
	private final CANTalon leftMotorSlave;
	private final CANTalon rightMotor;
	private final CANTalon rightMotorSlave;
	private final Solenoid solenoid1;
	private final Solenoid solenoid2;

	private List<TrajectoryPoint> trajPointList;
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
	
	private void generateTrajPoints() {
		MotionProfileConfiguration config = new MotionProfileConfiguration();
		config.setEndDistance(1000);
		config.setForwards(true);
		config.setInterval(10);
		config.setMaxAcc(50);
		config.setMaxVel(700);
		trajPointList = MotionProfileGenerator.generatePoints(config);
	}

	public double turnSense(double ptart) {
		double sense = SmartDashboard.getNumber("Sense", .7);
		return sense * ptart * ptart * ptart + ptart * (1 - sense);
	}

	public double inverse(double start) {
		double inverse = SmartDashboard.getNumber("Inverse", .2);
		return (start - preTurn) * inverse + start;
	}

	public void pointTurn(double turn) {
		rightMotor.set(-turn);
		leftMotor.set(-turn);
	}

	public void move(double speed, double turn) {
		rightMotor.changeControlMode(TalonControlMode.PercentVbus);
		leftMotor.changeControlMode(TalonControlMode.PercentVbus);
		if (speed > 0) {
			leftMotor.set(-inverse(speed) - (inverse(speed) * turnSense(turn)));
			rightMotor.set(inverse(speed) + (inverse(speed) * turnSense(turn)));
		} else {
			leftMotor.set(inverse(speed) + (inverse(speed) * turnSense(turn)));
			rightMotor.set(-inverse(speed) - (inverse(speed) * turnSense(turn)));
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
	
	public void resetMotionProfiling() {
		MotionProfileHelper.resetAndPushPoints(leftMotor, trajPointList, true);
		MotionProfileHelper.resetAndPushPoints(rightMotor, trajPointList, true);
	}
	
	public void processMotionProfiling() {
		MotionProfileHelper.processPoints(leftMotor);
		MotionProfileHelper.processPoints(rightMotor);
	}
	
	public void stopMotionProfiling() {
		MotionProfileHelper.stop(leftMotor);
		MotionProfileHelper.stop(rightMotor);
	}
	
	public boolean isMotionProfilingFinished() {
		// TODO: decide whether to check if both are finished or if at least one is finished
		return MotionProfileHelper.isFinished(leftMotor) && MotionProfileHelper.isFinished(rightMotor);
	}
}
