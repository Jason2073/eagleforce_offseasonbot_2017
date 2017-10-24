package org.usfirst.frc.team2073.robot.subsys;

import java.util.List;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.domain.MotionProfileConfiguration;
import org.usfirst.frc.team2073.robot.util.MotionProfileGenerator;
import org.usfirst.frc.team2073.robot.util.MotionProfileHelper;
import org.usfirst.frc.team2073.robot.util.TalonHelper;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretSubsystem extends Subsystem {
	private static final double F = 4.0;
	private static final double P = 30;
	private static final double I = 0;
	private static final double D = 170;
	private static final double MAX_ACCELERATION = 800;
	private static final double MAX_VELOCITY = 4000;
	
	private final CANTalon turretPos;
	private final CANTalon shooter1;
	private final CANTalon shooter2;
	
	private List<TrajectoryPoint> trajPoints;
	
	public TurretSubsystem() {
		turretPos = RobotMap.getTurretPosition();
		shooter1 = RobotMap.getShooter1();
		shooter2 = RobotMap.getShooter2();
		
		initTurretPos();
		generateTrajPoints();
		TalonHelper.setFollowerOf(shooter2, shooter1);
		
		LiveWindow.addActuator("Turret", "Pos", turretPos);
		LiveWindow.addActuator("Turret", "Shooter 1", shooter1);
		LiveWindow.addActuator("Turret", "Shooter 2", shooter2);
	}
	
	@Override
	public void initDefaultCommand() {
	}
	
	private void initTurretPos() {
		SmartDashboard.putNumber("Set F", F);
		SmartDashboard.putNumber("Set P", P);
		SmartDashboard.putNumber("Set I", I);
		SmartDashboard.putNumber("Set D", D);
		SmartDashboard.putNumber("RPM", MAX_VELOCITY);
		
		turretPos.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		turretPos.reverseSensor(false);
		turretPos.configEncoderCodesPerRev(RobotMap.getTurretPositionCodesPerRev());
		turretPos.configNominalOutputVoltage(+0.0f, -0.0f);
		turretPos.configPeakOutputVoltage(+12.0f, 0.0f);
		turretPos.setProfile(0);
		turretPos.setF(SmartDashboard.getNumber("Set F", F));
		turretPos.setP(SmartDashboard.getNumber("Set P", P));
		turretPos.setI(SmartDashboard.getNumber("Set I", I));
		turretPos.setD(SmartDashboard.getNumber("Set D", D));
	}
	
	private void generateTrajPoints() {
		MotionProfileConfiguration config = new MotionProfileConfiguration();
		config.setVelocityOnly(true);
		config.setForwards(true);
		config.setInterval(10);
		config.setMaxAcc(MAX_ACCELERATION);
		config.setMaxVel(SmartDashboard.getNumber("RPM", MAX_VELOCITY) * 60);
		trajPoints = MotionProfileGenerator.generatePoints(config);
	}
	
	public void turretMove(int angle) {
		turretPos.changeControlMode(TalonControlMode.Position);
		double rotations = angle / 360;
		turretPos.set(rotations);
	}
	
	public void accelerateToRPM() {
		MotionProfileHelper.resetAndPushPoints(shooter1, trajPoints, true);
		while(!MotionProfileHelper.isFinished(shooter1)) {
			MotionProfileHelper.processPoints(shooter1);
		}
	}
	
	public void sustainRPM() {
		// TODO: FIND A method for this
	}
}

