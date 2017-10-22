package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.conf.AppConstants.RobotPorts;
import org.usfirst.frc.team2073.robot.subsys.BallIntakeSubsystem;
import org.usfirst.frc.team2073.robot.subsys.ClimberSubsystem;
import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;
import org.usfirst.frc.team2073.robot.subsys.TurretSubsystem;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap {
	private static CANTalon leftMotor = new CANTalon(RobotPorts.LEFT_MOTOR);
	private static CANTalon leftMotorSlave = new CANTalon(RobotPorts.LEFT_MOTOR_SLAVE);
	private static CANTalon rightMotor = new CANTalon(RobotPorts.RIGHT_MOTOR);
	private static CANTalon rightMotorSlave = new CANTalon(RobotPorts.RIGHT_MOTOR_SLAVE);
	private static CANTalon gearIntakeTalon = new CANTalon(RobotPorts.GEAR_INTAKE_TALON);
	
	private static Solenoid solenoid1 = new Solenoid(RobotPorts.DRIVE_SOLENOID_1);
	private static Solenoid solenoid2 = new Solenoid(RobotPorts.DRIVE_SOLENOID_2);
	private static Solenoid solenoid3 = new Solenoid(RobotPorts.BALL_INTAKE_SOLENOID_1);
	private static Solenoid solenoid4 = new Solenoid(RobotPorts.BALL_INTAKE_SOLENOID_2);
	
	private static Victor gearIntakeMotor = new Victor(RobotPorts.GEAR_INTAKE_MOTOR);
	private static Victor ballIntakeMotor1 = new Victor(RobotPorts.BALL_INTAKE_MOTOR_1);
	private static Victor ballIntakeMotor2 = new Victor(RobotPorts.BALL_INTAKE_MOTOR_2);
	private static Victor climberMotor = new Victor(RobotPorts.CLIMBER_MOTOR);
	
	private static DigitalInput lightSensor = new DigitalInput(RobotPorts.LIGHT_SENSOR);
	private static DigitalInput magnetZeroer = new DigitalInput(RobotPorts.MAGNET_ZEROER);

	private static DrivetrainSubsystem drivetrain;
	private static TurretSubsystem turret;
	private static GearIntakeSubsystem gearIntake;
	private static BallIntakeSubsystem ballIntake;
	private static ClimberSubsystem climber;

	static void init() {
		drivetrain = new DrivetrainSubsystem();
		turret = new TurretSubsystem();
		gearIntake = new GearIntakeSubsystem();
		ballIntake = new BallIntakeSubsystem();
		SmartDashboard.putData("Drivetrain", drivetrain);
		SmartDashboard.putData("Turret", turret);
		SmartDashboard.putData("Gear Intake", gearIntake);
		SmartDashboard.putData("Ball Intake", ballIntake);
		
	}

	public static CANTalon getLeftMotor() {
		return leftMotor;
	}

	public static CANTalon getLeftMotorSlave() {
		return leftMotorSlave;
	}

	public static CANTalon getRightMotor() {
		return rightMotor;
	}

	public static CANTalon getRightMotorSlave() {
		return rightMotorSlave;
	}

	public static Solenoid getSolenoid1() {
		return solenoid1;
	}

	public static Solenoid getSolenoid2() {
		return solenoid2;
	}

	public static DrivetrainSubsystem getDrivetrain() {
		return drivetrain;
	}

	public static TurretSubsystem getTurret() {
		return turret;
	}

	public static GearIntakeSubsystem getGearIntake() {
		return gearIntake;
	}

	public static Victor getGearIntakeMotor() {
		return gearIntakeMotor;
	}

	public static CANTalon getGearIntakeTalon() {
		return gearIntakeTalon;
	}

	public static DigitalInput getLightSensor() {
		return lightSensor;
	}

	public static DigitalInput getMagnetZeroer() {
		return magnetZeroer;
	}

	public static Solenoid getSolenoid3() {
		return solenoid3;
	}

	public static Solenoid getSolenoid4() {
		return solenoid4;
	}

	public static Victor getBallIntakeMotor1() {
		return ballIntakeMotor1;
	}

	public static Victor getBallIntakeMotor2() {
		return ballIntakeMotor2;
	}

	public static BallIntakeSubsystem getBallIntake() {
		return ballIntake;
	}

	public static Victor getClimberMotor() {
		return climberMotor;
	}

	public static ClimberSubsystem getClimber() {
		return climber;
	}
}
