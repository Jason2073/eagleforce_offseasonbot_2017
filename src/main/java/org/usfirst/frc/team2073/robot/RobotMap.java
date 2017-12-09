package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.BallIntake;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.Climber;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.Drivetrain;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.GearIntake;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.GearPosition;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.Intermediate;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.Turret;
import org.usfirst.frc.team2073.robot.subsys.BallIntakeSubsystem;
import org.usfirst.frc.team2073.robot.subsys.ClimberSubsystem;
import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;
import org.usfirst.frc.team2073.robot.subsys.GearPositionSubsystem;
import org.usfirst.frc.team2073.robot.subsys.IntermediateSubsystem;
import org.usfirst.frc.team2073.robot.subsys.TurretSubsystem;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap {
	@Inject private static DrivetrainSubsystem drivetrain;
	private static boolean ballIntakeForwards = false;
	@Inject private static GyroBase gyro;
	@Inject private static TurretSubsystem turret;
	@Inject private static BallIntakeSubsystem ballIntake;
	@Inject private static ClimberSubsystem climber;
	@Inject private static IntermediateSubsystem intermediate;
	@Inject private static GearPositionSubsystem gearPosition;
	@Inject private static GearIntakeSubsystem gearIntake;
	@Inject @Named("Light Sensor") private static DigitalInput lightSensor;

	static void init() {
		SmartDashboard.putData(Drivetrain.NAME, drivetrain);
		SmartDashboard.putData(Turret.NAME, turret);
		SmartDashboard.putData(GearPosition.NAME, gearPosition);
		SmartDashboard.putData(GearIntake.NAME, gearIntake);
		SmartDashboard.putData(BallIntake.NAME, ballIntake);
		SmartDashboard.putData(Intermediate.NAME, intermediate);
		SmartDashboard.putData(Climber.NAME, climber);
	}

	@Deprecated
	public static DrivetrainSubsystem getDrivetrain() {
		return drivetrain;
	}

	@Deprecated
	public static GyroBase getGyro() {
		return gyro;
	}

	@Deprecated
	public static GearIntakeSubsystem getGearIntake() {
		return gearIntake;
	}

	@Deprecated
	public static GearPositionSubsystem getGearPosition() {
		return gearPosition;
	}

	@Deprecated
	public static DigitalInput getLightSensor() {
		return lightSensor;
	}

	@Deprecated
	public static BallIntakeSubsystem getBallIntake() {
		return ballIntake;
	}

	public static void setBallIntakeForwards(boolean ballIntakeForwards) {
		RobotMap.ballIntakeForwards = ballIntakeForwards;
	}

	public static boolean isBallIntakeForwards() {
		return ballIntakeForwards;
	}

	@Deprecated
	public static ClimberSubsystem getClimber() {
		return climber;
	}

	@Deprecated
	public static IntermediateSubsystem getIntermediate() {
		return intermediate;
	}

	@Deprecated
	public static TurretSubsystem getTurret() {
		return turret;
	}
}
