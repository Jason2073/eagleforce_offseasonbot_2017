package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Subsystems.BallIntake;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class BallIntakeSubsystem extends Subsystem {
	private final Victor motor1;
	private final Victor motor2;
	private final Solenoid solenoid1;
	private final Solenoid solenoid2;

	public BallIntakeSubsystem(){
		motor1 = RobotMap.getBallIntakeMotor1();
		motor2 = RobotMap.getBallIntakeMotor2();
		solenoid1 = RobotMap.getBallIntakeSolenoid1();
		solenoid2 = RobotMap.getBallIntakeSolenoid2();

		LiveWindow.addActuator(BallIntake.NAME, BallIntake.ComponentNames.MOTOR_1, motor1);
		LiveWindow.addActuator(BallIntake.NAME, BallIntake.ComponentNames.MOTOR_2, motor2);
		LiveWindow.addActuator(BallIntake.NAME, BallIntake.ComponentNames.SOLENOID_1, solenoid1);
		LiveWindow.addActuator(BallIntake.NAME, BallIntake.ComponentNames.SOLENOID_2, solenoid2);
	}

	@Override
	public void initDefaultCommand() {
	}

	public void deployIntake() {
		solenoid1.set(true);
		solenoid2.set(true);
	}

	public void intakeBalls() {
		motor1.set(1);
		motor2.set(1);
	}

	public void reverseIntake() {
		motor1.set(-1);
		motor2.set(-1);
	}

	public void stopIntake() {
		motor1.set(0);
		motor2.set(0);
	}
}
