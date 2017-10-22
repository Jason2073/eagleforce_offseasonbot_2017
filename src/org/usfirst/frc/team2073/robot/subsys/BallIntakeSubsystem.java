package org.usfirst.frc.team2073.robot.subsys;

import org.usfirst.frc.team2073.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class BallIntakeSubsystem extends Subsystem {

	private Victor intake = RobotMap.getBallIntakeMotor1();
	private Victor intake2 = RobotMap.getBallIntakeMotor2();
	private Solenoid intakeSol = RobotMap.getSolenoid3();
	private Solenoid intakeSol2 = RobotMap.getSolenoid4();
	
	public BallIntakeSubsystem(){
		LiveWindow.addActuator("BallIntake", "IntakeMotor1", intake);
		LiveWindow.addActuator("BallIntake", "IntakeMotor2", intake2);
		LiveWindow.addActuator("BallIntake", "IntakeSolenoid1", intakeSol);
		LiveWindow.addActuator("BallIntake", "IntakeSolenoid2", intakeSol2);
	}

	public void deployIntake() {
		intakeSol.set(true);
		intakeSol2.set(true);
	}

	public void intakeBalls() {
		intake.set(1);
		intake2.set(1);
	}

	public void reverseIntake() {
		intake.set(-1);
		intake2.set(-1);
	}

	public void stopIntake() {
		intake.set(0);
		intake2.set(0);
	}

	public void initDefaultCommand() {

	}
}
