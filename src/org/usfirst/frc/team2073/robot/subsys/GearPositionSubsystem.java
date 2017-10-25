package org.usfirst.frc.team2073.robot.subsys;

import java.util.List;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.conf.AppConstants.DashboardKeys;
import org.usfirst.frc.team2073.robot.conf.AppConstants.Defaults;
import org.usfirst.frc.team2073.robot.domain.MotionProfileConfiguration;
import org.usfirst.frc.team2073.robot.util.MotionProfileGenerator;
import org.usfirst.frc.team2073.robot.util.MotionProfileHelper;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearPositionSubsystem extends Subsystem {
	private final CANTalon talon;
	private final DigitalInput magnetZeroer;

	private List<TrajectoryPoint> upToDownTpList;
	private List<TrajectoryPoint> upToPlaceTpList;
	private List<TrajectoryPoint> placeToUpTpList;
	private List<TrajectoryPoint> placeToDownTpList;
	private List<TrajectoryPoint> downToPlaceTpList;
	private List<TrajectoryPoint> downToUpTpList;
	
	public GearPositionSubsystem() {
		talon = RobotMap.getGearIntakeTalon();
		magnetZeroer = RobotMap.getMagnetZeroer();

		SmartDashboard.putNumber(DashboardKeys.FGAIN, Defaults.FGAIN);

		// generatePoints(isForwards, maxVel, interval, endDistance, maxAcc)
		// TODO: Extract method args to constants? Would this help or hurt?
		upToDownTpList = generatePoints(true, 500, 10, 40, 600);
		upToPlaceTpList = generatePoints(true, 500, 10, 25, 600);
//		shouldnt be called
		placeToUpTpList = generatePoints(false, 3, 10, .125, 60);
		placeToDownTpList = generatePoints(true, 3, 10, .125, 60);
		downToPlaceTpList = generatePoints(false, 3, 10, .125, 60);
		downToUpTpList = generatePoints(false, 3, 10, .25, 60);

//		talon.changeMotionControlFramePeriod(5);
		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talon.configEncoderCodesPerRev(1024);

		MotionProfileHelper.initTalon(talon);

		// TODO: Extract to constants
		LiveWindow.addActuator("Gear Intake", "Talon", talon);
		LiveWindow.addSensor("Gear Intake", "Magnet Zeroer", magnetZeroer);
	}

	@Override
	public void initDefaultCommand() {
	}

	private List<TrajectoryPoint> generatePoints(boolean isForwards, double maxVel, int interval, double endDistance, double maxAcc) {
		MotionProfileConfiguration config = new MotionProfileConfiguration();
		config.setForwards(isForwards);
		config.setMaxVel(maxVel);
		config.setInterval(interval);
		config.setEndDistance(endDistance);
		config.setMaxAcc(maxAcc);
		return MotionProfileGenerator.generatePoints(config);
	}
	
	public double getAngle() {
		return talon.getPosition();
	}

	public void readPos() {
		System.out.println(talon.getPosition());
	}

	public void stop() {
		talon.changeControlMode(TalonControlMode.PercentVbus);
		talon.set(0);
	}

	public double getApproxAngle() {
		return Math.round(talon.getPosition());
	}

	public void resetGearIntake() {
		talon.changeControlMode(TalonControlMode.PercentVbus);
		talon.set(.3);
	}

	public void zeroIntake() {
		talon.setPosition(0);
		talon.setEncPosition(0);
	}

	public boolean isZero() {
		return !magnetZeroer.get();
	}

	public void upToDown() {
		MotionProfileHelper.resetAndPushPoints(talon, upToDownTpList, false);
	}

	public void upToPlace() {
		MotionProfileHelper.resetAndPushPoints(talon, upToPlaceTpList, false);
	}

	public void placeToDown() {
		MotionProfileHelper.resetAndPushPoints(talon, placeToDownTpList, true);
	}

	public void placeToUp() {
		MotionProfileHelper.resetAndPushPoints(talon, placeToUpTpList, false);
	}

	public void downToUp() {
		MotionProfileHelper.resetAndPushPoints(talon, downToUpTpList, false);
	}

	public void downToPlace() {
		MotionProfileHelper.resetAndPushPoints(talon, downToPlaceTpList, false);
	}

	public void toDown(int input) {
		if (-5 < getApproxAngle() && getApproxAngle() < 5)
			upToDown();
		if (40 < getApproxAngle() && getApproxAngle() < 50)
			placeToDown();
	}

	public void toPlace(int input) {
		if (-5 < getApproxAngle() && getApproxAngle() < 5)
			upToPlace();
		if (85 < getApproxAngle() && getApproxAngle() < 95)
			downToPlace();
	}

	public void toUp(int input) {
		if (85 < getApproxAngle() && getApproxAngle() < 95)
			downToUp();
		if (40 < getApproxAngle() && getApproxAngle() < 50)
			placeToUp();
	}

	public void processMotionProfiling() {
		MotionProfileHelper.processPoints(talon);
	}

	public boolean isMotionProfilingFinished() {
		return MotionProfileHelper.isFinished(talon);
	}

	public void stopMotionProfiling() {
		MotionProfileHelper.stopTalon(talon);
	}
}
