package org.usfirst.frc.team2073.robot.subsys;

import java.util.List;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.domain.MotionProfileConfiguration;
import org.usfirst.frc.team2073.robot.util.MotionProfileGenerator;
import org.usfirst.frc.team2073.robot.util.MotionProfileHelper;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntakeSubsystem extends Subsystem {
	private final Victor intakeMotor;
	private final CANTalon talon;
	private final DigitalInput lightSensor;
	private final DigitalInput magnetZeroer;

	private List<TrajectoryPoint> upToDownTpList;
	private List<TrajectoryPoint> upToPlaceTpList;
	private List<TrajectoryPoint> placeToUpTpList;
	private List<TrajectoryPoint> placeToDownTpList;
	private List<TrajectoryPoint> downToPlaceTpList;
	private List<TrajectoryPoint> downToUpTpList;

	public GearIntakeSubsystem() {
		intakeMotor = RobotMap.getGearIntakeMotor();
		talon = RobotMap.getGearIntakeTalon();
		lightSensor = RobotMap.getLightSensor();
		magnetZeroer = RobotMap.getMagnetZeroer();

		// TODO: Extract to constant
		SmartDashboard.putNumber("Fgain", .7871);

		// generatePoints(isForwards, maxVel, interval, endDistance, maxAcc)
		// TODO: Extract method args to constants? Would this help or hurt?
		upToDownTpList = generatePoints(false, 300, 10, 250, 60);
		upToPlaceTpList = generatePoints(false, 3, 5, .125, 60);
		placeToUpTpList = generatePoints(false, 3, 10, .125, 60);
		placeToDownTpList = generatePoints(true, 3, 10, .125, 60);
		downToPlaceTpList = generatePoints(false, 3, 10, .125, 60);
		downToUpTpList = generatePoints(false, 3, 10, .25, 60);

//		talon.changeMotionControlFramePeriod(5);

		MotionProfileHelper.init(talon);
	}

	@Override
	public void initDefaultCommand() {
//		TODO setDefaultCommand(new GearIntakeResetCommand());
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

	public void gearIn() {
		intakeMotor.set(-1);
	}

	public void gearOut() {
		intakeMotor.set(1);
	}

	public void gearHold() {
		intakeMotor.set(.1);
	}

	public void gearStop() {
		intakeMotor.set(0);
	}

	public double getApproxAngle() {
		return Math.round(talon.getPosition());
	}

	public boolean lightSensor() {
		return lightSensor.get();
	}

	public void resetGearIntake() {
		talon.changeControlMode(TalonControlMode.PercentVbus);
		talon.set(.3);
	}

	public void zeroIntake() {
		talon.setPosition(0);
	}

	public boolean isZero() {
		System.out.println(magnetZeroer.get());
		return magnetZeroer.get();
	}

	public void upToDown() {
		MotionProfileHelper.resetAndPushPoints(talon, upToDownTpList, true);
	}

	public void upToPlace() {
		MotionProfileHelper.resetAndPushPoints(talon, upToPlaceTpList, true);
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
		MotionProfileHelper.stop(talon);
	}
}
