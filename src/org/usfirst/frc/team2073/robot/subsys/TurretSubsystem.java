package org.usfirst.frc.team2073.robot.subsys;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team2073.robot.Robot;
import org.usfirst.frc.team2073.robot.domain.CameraMessage;
import org.usfirst.frc.team2073.robot.domain.CameraMessageReceiver;
import org.usfirst.frc.team2073.robot.domain.MotionProfileConfiguration;
import org.usfirst.frc.team2073.robot.domain.MotionProfileGenerator;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretSubsystem extends Subsystem {
	
	private abstract class Defaults {
		private static final double MAX_VELOCITY = 300;
		private static final int INTERVAL = 10;
		private static final double MAX_ACCELERATION = 30;
		private static final double PROFILE_DISTANCE = 10;
		private static final double LOCKED_ZONE_DEGREES = 1.5;
		private static final double F_GAIN = .7871;
		private static final double DEFAULT_GEAR_RATIO = 10.*(7./3.);
		private static final double MIN_BUFFER_THRESHOLD = 5;
	}

	private abstract class DashboardKeys {
		private static final String MAX_VELOCITY 		= "VisionConf: Max Vel";
		private static final String INTERVAL 			= "VisionConf: Interval";
		private static final String MAX_ACCELERATION 	= "VisionConf: Max Acc";
		private static final String LOCKED_ZONE_DEGREES = "VisionConf: Lock Zone Degrees";
		private static final String F_GAIN 				= "VisionConf: F Gain";
		private static final String GEAR_RATIO			= "VisionConf: Gear Ratio";
		
		private static final String PROFILE_DISTANCE 	= "VisionStat: Distance";
		private static final String CAMERA_ANGLE 		= "VisionStat: Angle";
		private static final String CAMERA_TIME 		= "VisionStat: Time";
//		private static final String CAMERA_TARGET_ON_SCREEN = "Vision: Target on Sc";
	}
	
	private enum SubsystemState {
		MOTION_PROFILE_MODE,
		VBUS_MODE
	}

	private enum MotionProfileState {
		INACTIVE,
		GENERATING_PROFILE, 
		FILLING_BUFFER, 
		RUNNING_PROFILE,
		PROFILE_COMPLETE,
		PROFILE_STOPPED
	}
	
	// Internal helper classes
	private final Helper helper = new Helper();
	private final Info info = new Info();

	// Model objects
	private MotionProfileConfiguration conf1 = new MotionProfileConfiguration();
	private List<TrajectoryPoint> currentProfile;
	private List<CANTalon> ctList = new ArrayList<>();
	private CANTalon.MotionProfileStatus talonStatus = new CANTalon.MotionProfileStatus();
	private SubsystemState sysState;
	private MotionProfileState mpState = MotionProfileState.INACTIVE;

	// Internal model
	
	/** The degrees left and right of the center of the target to consider 'locked'.
	 * For example if set to 1.5 the target is deemed to be 'locked' if target is 
	 * anywhere from -1.5 to 1.5 degrees off center. */
	private double lockZone = 	Defaults.LOCKED_ZONE_DEGREES;
	private double maxVel = 	Defaults.MAX_VELOCITY;
	private int interval = 		Defaults.INTERVAL;
	private double distance = 	Defaults.PROFILE_DISTANCE;
	private double maxAcc = 	Defaults.MAX_ACCELERATION;
	private double fGain = 		Defaults.F_GAIN;
	private double gearRatio = 	Defaults.DEFAULT_GEAR_RATIO;
	
	public TurretSubsystem() {
		initTalonList();
		helper.refreshData();
		helper.updateDashboard();
		helper.defaultProfileConfig();
		helper.initTalons();
	}

	/**
	 * Create the talons here, add them to a list and then ONLY access them
	 * from this list. This way we can dynamically switch between 1, 2 or 4 talons
	 * without changing any other part of the code.<p>
	 * 
	 * The talon objects should be declared INSIDE this method so they are scoped 
	 * only to this method (i.e., they are not available to access outside this method. <p>
	 * 
	 * In the future we will probably either add a <Code>List<CANTalon></code> argument
	 * to the constructor or add a public method to set this list. That way the number of
	 * CanTalons can be changed at runtime without changing code at all.
	 */
	private void initTalonList() {
		ctList.add(new CANTalon(4));
		// Add other talons here
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	// Functional methods (these perform some type of action)
	// ====================================================================================================
	
	private void processPeriodic() {
		helper.processTalonPeriodic();
		helper.updateMotionProfileStatus();
	}

	/**
	 * This method is meant to be called repeatedly to acquire a target.
	 */
	public void acquireTarget() {
		
		processPeriodic();
		
		switch (mpState) {
			case INACTIVE: {
				mpState = MotionProfileState.GENERATING_PROFILE;
				break;
			}
			case GENERATING_PROFILE: {
				helper.generateVisionTrackingProfile();
				helper.pushPoints(currentProfile);
				mpState = MotionProfileState.FILLING_BUFFER;
				break;
			}
			case FILLING_BUFFER: {
				helper.processBufferPeriodic();
				if (info.bufferFilled()) {
					mpState = MotionProfileState.RUNNING_PROFILE;
				}
				break;
			}
			case RUNNING_PROFILE: {
				helper.processBufferPeriodic();
				helper.processProfilePeriodic();
				if (info.bufferEmpty()) {
					mpState = MotionProfileState.PROFILE_COMPLETE;
				}
				break;
			}
			case PROFILE_COMPLETE: {
				stopMotionProfileMode();
				mpState = MotionProfileState.INACTIVE;
				break;
			}
			default: {
				break;
			}
		}
	}
	
	public void startMotionProfileMode() {
//		mpState = MotionProfileState.STARTING_MOTIONG_PROFILE_MODE;
		ctList.forEach(talon -> talon.changeControlMode(TalonControlMode.MotionProfile));
	}

	public void stopMotionProfileMode() {
		mpState = MotionProfileState.PROFILE_STOPPED;
		ctList.forEach(talon -> {
			talon.changeControlMode(TalonControlMode.PercentVbus);
			talon.set(CANTalon.SetValueMotionProfile.Disable.value);
			talon.clearMotionProfileTrajectories();
		});
	}

	// Informational methods (these return the mpState of this subsystem)
	// ====================================================================================================
	
	public boolean targetOffScreen() {
		return !CameraMessageReceiver.getLastMessage().isTracking();
	}
	
	public boolean targetLocked() {
		if (targetOffScreen())
			return false;
		
		boolean locked = Math.abs(CameraMessageReceiver.getLastMessage().getAngleToTarget()) < lockZone;
		if (locked)
			System.out.println("Target locked!");
		return locked;
	}

//	private void resetEnc() {
//		talon.setPosition(0);
//		talon.setEncPosition(0);
//	}

//	private void printTalonInfo() {
//		System.out.println("Pos: " + talon.getPosition() + "topBuffer: " + talonStatus.topBufferCnt);
//		talon.changeControlMode(TalonControlMode.PercentVbus);
//	}
//
//	private void setDirection(boolean forwards) {
//		talon.reverseOutput(forwards);
//	}
//
//	private boolean isToTheRight() {
//		return cam.lastCameraMessage().getAngleToTarget() > 0;
//	}
//	
//	private double bufferedPoints(){
//		return talonStatus.topBufferCnt;
//	}
//	
//	private void printCameraInfo() {
//	}
//	
//	private double angle() {
//		return cam.lastCameraMessage().getAngleToTarget();
//	}

	private class Info {

		/**
		 * Checks if the buffer is either past the set threshold or the entire
		 * profile has been loaded but it is still smaller than the set
		 * threshold.
		 */
		public boolean bufferFilled() {
			int buffer = talonStatus.btmBufferCnt;
			return buffer > Defaults.MIN_BUFFER_THRESHOLD || buffer == currentProfile.size();
		}
		
		public boolean bufferEmpty() {
			return talonStatus.btmBufferCnt == 0;
		}

		// TODO: We're currently not using this cause it ends up being too short of
		// a profile. In the future we need to use this to create real profiles.
		private double profileDistanceToTarget() {
			double dist = 0;
			double camDist = Math.abs(CameraMessageReceiver.getLastMessage().getAngleToTarget()/360);
			camDist *= gearRatio;
			camDist = Math.max(camDist, Defaults.PROFILE_DISTANCE);
//			dist *= GEAR_RATIO;
			dist = camDist;
			return dist;
		}
	}
	
	private class Helper {

		private void initTalons() {
			for (CANTalon tal : ctList) {
				tal.setF(fGain);
				tal.set(CANTalon.SetValueMotionProfile.Disable.value);
				tal.changeControlMode(TalonControlMode.PercentVbus);
				sysState = SubsystemState.VBUS_MODE;
				tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
				tal.changeMotionControlFramePeriod(Defaults.INTERVAL / 2);
				tal.clearMotionProfileTrajectories();
			}
		}
		
		private void defaultProfileConfig() {
//			conf1 = new MotionProfileConfiguration();
			conf1.setMaxVel(Defaults.MAX_VELOCITY);
			conf1.setInterval(Defaults.INTERVAL);
			conf1.setEndDistance(Defaults.PROFILE_DISTANCE);
			conf1.setMaxAcc(Defaults.MAX_ACCELERATION);
//			currentProfile = MotionProfileGenerator.generatePoints(conf1);
//			System.out.println("generated");
		}

		private void generateVisionTrackingProfile() {
//			conf1 = new MotionProfileConfiguration();
			conf1.setMaxVel(maxVel);
			conf1.setInterval(interval);
			conf1.setMaxAcc(maxAcc);
			
			// Method 1: Use a default distance
			conf1.setEndDistance(distance);
			// Method 2: Get distance from camera
//			conf1.setEndDistance(profileDistanceToTarget());
			
			currentProfile = MotionProfileGenerator.generatePoints(conf1);
		}

		private void pushPoints(List<TrajectoryPoint> tpList) {
			ctList.forEach(talon -> {
				tpList.forEach(tp -> talon.pushMotionProfileTrajectory(tp));
			});
		}

		public void updateMotionProfileStatus() {
			// TODO Auto-generated method stub
			ctList.get(0).getMotionProfileStatus(talonStatus);
		}

		public void processBufferPeriodic() {
			ctList.forEach(talon -> talon.processMotionProfileBuffer());
		}
		
		private void processProfilePeriodic() {
			ctList.forEach(talon -> talon.set(CANTalon.SetValueMotionProfile.Enable.value));
		}

		private void processTalonPeriodic() {
//			talon.set(CANTalon.SetValueMotionProfile.Enable.value);
//			talon.setF(fGain);
//			System.out.println("Processing");
		}
		
		private void refreshData() {
			maxVel = SmartDashboard.getNumber(						DashboardKeys.MAX_VELOCITY, 		Defaults.MAX_VELOCITY);
			interval = (int) Math.round(SmartDashboard.getNumber(	DashboardKeys.INTERVAL, 			Defaults.INTERVAL));
			distance = SmartDashboard.getNumber(					DashboardKeys.PROFILE_DISTANCE, 	Defaults.PROFILE_DISTANCE);
			maxAcc = SmartDashboard.getNumber(						DashboardKeys.MAX_ACCELERATION, 	Defaults.MAX_ACCELERATION);
			lockZone = SmartDashboard.getNumber(					DashboardKeys.LOCKED_ZONE_DEGREES, 	Defaults.LOCKED_ZONE_DEGREES);
			fGain = SmartDashboard.getNumber(						DashboardKeys.F_GAIN, 				Defaults.F_GAIN);
			gearRatio = SmartDashboard.getNumber(					DashboardKeys.GEAR_RATIO, 			Defaults.DEFAULT_GEAR_RATIO);
		}
		
		/**
		 * Must call {@link #refreshData()} before calling this!
		 */
		private void updateDashboard() {
			SmartDashboard.putNumber(DashboardKeys.MAX_VELOCITY, 		maxVel);
			SmartDashboard.putNumber(DashboardKeys.INTERVAL, 			interval);
			SmartDashboard.putNumber(DashboardKeys.PROFILE_DISTANCE, 	distance);
			SmartDashboard.putNumber(DashboardKeys.MAX_ACCELERATION, 	maxAcc);
			SmartDashboard.putNumber(DashboardKeys.LOCKED_ZONE_DEGREES, lockZone);
			SmartDashboard.putNumber(DashboardKeys.F_GAIN, 				fGain);
			SmartDashboard.putNumber(DashboardKeys.GEAR_RATIO,			gearRatio);
			
			CameraMessage cameraMessage = CameraMessageReceiver.getLastMessage();
			SmartDashboard.putNumber(DashboardKeys.PROFILE_DISTANCE, cameraMessage.getDistanceToTarget());
			SmartDashboard.putNumber(DashboardKeys.CAMERA_ANGLE, cameraMessage.getAngleToTarget());
			SmartDashboard.putNumber(DashboardKeys.CAMERA_TIME, cameraMessage.getTimeOfImage());
//			SmartDashboard.putBoolean("CamTracking", cameraMessage.isTracking());
		}
	}
}

