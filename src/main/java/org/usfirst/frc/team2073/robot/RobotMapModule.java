package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.conf.AppConstants.RobotPorts;
import org.usfirst.frc.team2073.robot.subsys.BallIntakeSubsystem;
import org.usfirst.frc.team2073.robot.subsys.ClimberSubsystem;
import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;
import org.usfirst.frc.team2073.robot.subsys.GearPositionSubsystem;
import org.usfirst.frc.team2073.robot.subsys.IntermediateSubsystem;
import org.usfirst.frc.team2073.robot.subsys.TurretSubsystem;

import com.ctre.CANTalon;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class RobotMapModule extends AbstractModule {
	@Override
	protected void configure() {
		requestStaticInjection(RobotMap.class);

		bind(DrivetrainSubsystem.class);
		bind(CANTalon.class)
			.annotatedWith(Names.named("Drivetrain Left"))
			.toInstance(new CANTalon(RobotPorts.LEFT_MOTOR));
		bind(CANTalon.class)
			.annotatedWith(Names.named("Drivetrain Left Slave"))
			.toInstance(new CANTalon(RobotPorts.LEFT_MOTOR_SLAVE));
		bind(CANTalon.class)
			.annotatedWith(Names.named("Drivetrain Right"))
			.toInstance(new CANTalon(RobotPorts.RIGHT_MOTOR));
		bind(CANTalon.class)
			.annotatedWith(Names.named("Drivetrain Right Slave"))
			.toInstance(new CANTalon(RobotPorts.RIGHT_MOTOR_SLAVE));
		bind(Solenoid.class)
			.annotatedWith(Names.named("Drivetrain 1"))
			.toInstance(new Solenoid(RobotPorts.DRIVE_SOLENOID_1));
		bind(Solenoid.class)
			.annotatedWith(Names.named("Drivetrain 2"))
			.toInstance(new Solenoid(RobotPorts.DRIVE_SOLENOID_2));
		bind(GyroBase.class).toInstance(new ADXRS450_Gyro());

		bind(TurretSubsystem.class);
		bind(CANTalon.class)
			.annotatedWith(Names.named("Turret Position"))
			.toInstance(new CANTalon(RobotPorts.TURRET_POSITION));
		bind(CANTalon.class)
			.annotatedWith(Names.named("Turret Shooter 1"))
			.toInstance(new CANTalon(RobotPorts.TURRET_SHOOTER_1));
		bind(CANTalon.class)
			.annotatedWith(Names.named("Turret Shooter 2"))
			.toInstance(new CANTalon(RobotPorts.TURRET_SHOOTER_2));

		bind(BallIntakeSubsystem.class);
		bind(PWMSpeedController.class)
			.annotatedWith(Names.named("Ball Intake 1"))
			.toInstance(new Victor(RobotPorts.BALL_INTAKE_MOTOR_1));
		bind(PWMSpeedController.class)
			.annotatedWith(Names.named("Ball Intake 2"))
			.toInstance(new Victor(RobotPorts.BALL_INTAKE_MOTOR_2));
		bind(Solenoid.class)
			.annotatedWith(Names.named("Ball Intake"))
			.toInstance(new Solenoid(RobotPorts.BALL_INTAKE_SOLENOID));

		bind(ClimberSubsystem.class);
		bind(PWMSpeedController.class)
			.annotatedWith(Names.named("Climber"))
			.toInstance(new Victor(RobotPorts.CLIMBER_MOTOR));

		bind(IntermediateSubsystem.class);
		bind(PWMSpeedController.class)
			.annotatedWith(Names.named("Belly Roller"))
			.toInstance(new Victor(RobotPorts.BELLY_ROLLERS));
		bind(PWMSpeedController.class)
			.annotatedWith(Names.named("Intermediate"))
			.toInstance(new Victor(RobotPorts.INTERMEDIATE));

		bind(GearPositionSubsystem.class);
		bind(GearIntakeSubsystem.class);
		bind(PWMSpeedController.class)
			.annotatedWith(Names.named("Gear Intake"))
			.toInstance(new Victor(RobotPorts.GEAR_INTAKE_MOTOR));
		bind(CANTalon.class)
			.annotatedWith(Names.named("Gear Position"))
			.toInstance(new CANTalon(RobotPorts.GEAR_INTAKE_TALON));
		bind(DigitalInput.class)
			.annotatedWith(Names.named("Light Sensor"))
			.toInstance(new DigitalInput(RobotPorts.LIGHT_SENSOR));
		bind(DigitalInput.class)
			.annotatedWith(Names.named("Magnet Zeroer"))
			.toInstance(new DigitalInput(RobotPorts.MAGNET_ZEROER));
	}
}
