package com.eagleforce.robot.service;

import java.util.ArrayList;
import java.util.List;

import com.ctre.CANTalon;
import com.ctre.CANTalon.MotionProfileStatus;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;
import com.eagleforce.robot.model.MotionProfileConfiguration;
import com.eagleforce.robot.util.SuppressibleLogger;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MotionProfileGearIntakeService extends GearIntakeService {
	private CANTalon talon = new CANTalon(1);

	private MotionProfileGenerationService mpgSvc = new MotionProfileGenerationService();
	private MotionProfileConfiguration conf1;
	private MotionProfileConfiguration conf2;
	private MotionProfileConfiguration conf3;
	private MotionProfileConfiguration conf4;
	private MotionProfileConfiguration conf5;
	private MotionProfileConfiguration conf6;

	public List<TrajectoryPoint> upToDownTpList;
	public List<TrajectoryPoint> upToPlaceTpList;
	public List<TrajectoryPoint> placeToUpTpList;
	public List<TrajectoryPoint> placeToDownTpList;
	public List<TrajectoryPoint> downToPlaceTpList;
	public List<TrajectoryPoint> downToUpTpList;
	
	enum MotionProfileState {
		INACTIVE, FILLING_BUFFER, RUNNING_PROFILE
	}
	private final SuppressibleLogger log = new SuppressibleLogger(this.getClass());
	private List<CANTalon> ctList = new ArrayList<>();
	private List<MotionProfileStatus> mpsList = new ArrayList<>();
//	private List<TrajectoryPoint> tpList = new ArrayList<>();
	private CANTalon.MotionProfileStatus talonStatus = new CANTalon.MotionProfileStatus();
	private MotionProfileState _state = MotionProfileState.INACTIVE;
	private int _loopTimeout = -1;
	private boolean _bStart = false;
	private CANTalon.SetValueMotionProfile _setValue = CANTalon.SetValueMotionProfile.Disable;
	private static final int kMinPointsInTalon = 0;
	private static final int kNumLoopsTimeout = 10;
	private Notifier _notifer = new Notifier(new CANTalonBufferProcessor(ctList));

	public MotionProfileGearIntakeService() {

		talon.setF(.7871);

		conf1 = new MotionProfileConfiguration();
		// up to down
		conf1.setForwards(false);
		conf1.setMaxVel(3);
		conf1.setInterval(5);
		conf1.setEndDistance(.25);
		conf1.setMaxAcc(60);

		conf2 = new MotionProfileConfiguration();
		// up to place
		conf2.setForwards(false);
		conf2.setMaxVel(3);
		conf2.setInterval(5);
		conf2.setEndDistance(.125);
		conf2.setMaxAcc(60);

		conf3 = new MotionProfileConfiguration();
		// place to up (reverse)
		conf3.setForwards(false);
		conf3.setMaxVel(3);
		conf3.setInterval(5);
		conf3.setEndDistance(.125);
		conf3.setMaxAcc(60);

		conf4 = new MotionProfileConfiguration();
		// place to down
		conf4.setForwards(true);
		conf4.setMaxVel(3);
		conf4.setInterval(5);
		conf4.setEndDistance(.125);
		conf4.setMaxAcc(60);

		conf5 = new MotionProfileConfiguration();
		// down to place (reverse)
		conf5.setForwards(false);
		conf5.setMaxVel(3);
		conf5.setInterval(5);
		conf5.setEndDistance(.125);
		conf5.setMaxAcc(60);

		conf6 = new MotionProfileConfiguration();
		// down to up (reverse)
		conf6.setForwards(false);
		conf6.setMaxVel(3);
		conf6.setInterval(5);
		conf6.setEndDistance(.25);
		conf6.setMaxAcc(60);

		upToDownTpList = mpgSvc.generatePoints(conf1);
		upToPlaceTpList = mpgSvc.generatePoints(conf2);
		placeToUpTpList = mpgSvc.generatePoints(conf3);
		placeToDownTpList = mpgSvc.generatePoints(conf4);
		downToPlaceTpList = mpgSvc.generatePoints(conf5);
		downToUpTpList = mpgSvc.generatePoints(conf6);
		
		for (CANTalon ct : ctList) {
			ct.changeMotionControlFramePeriod((int) SmartDashboard.getNumber("Interval", 10) / 2);
		}

	}


	
//	public MotionProfileDriveTrainService(CANTalon lMotor, CANTalon lMotorSlave, CANTalon rMotor,
//			CANTalon rMotorSlave) {
//		log.log("Constructing MotionProfileDriveTrainService.");
//
//		// Initialize config
//		generateTrajPointList();
//
//		this.lMotor = lMotor;
//		this.rMotor = rMotor;
//
//		// Create a list of the talons so we can easily process repetitive
//		// functions
//		ctList.add(this.lMotor);
//		// ctList.add(this.lMotorSlave); // Comment once setup with master/slave
//		// properly
//		ctList.add(this.rMotor);
//		// ctList.add(this.rMotorSlave); // Comment once setup with master/slave
//		// properly
//
//		mpsList.add(lMotorStatus);
//		mpsList.add(rMotorStatus);
//		SmartDashboard.putNumber("Fgain", 60);
//		lMotor.setF(SmartDashboard.getNumber("Fgain", 1));
//		rMotor.setF(SmartDashboard.getNumber("Fgain", 1));
//		lMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//		rMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//		// TODO: Gear Ratios
//		// high gear ratio 4.89:1
//		// low gear ratio 15.41:1
//		lMotor.configEncoderCodesPerRev(encTpr);
//		rMotor.configEncoderCodesPerRev(encTpr);
//		lMotor.reverseOutput(true);
//		lMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
//		rMotorSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
//		lMotorSlave.set(lMotor.getDeviceID());
//		rMotorSlave.set(rMotor.getDeviceID());
//		SmartDashboard.putNumber("Max Velocity", 100);
//		SmartDashboard.putNumber("Max Acceleration", 2000);
//		SmartDashboard.putNumber("Distance", 10);
//		SmartDashboard.putNumber("Interval", 1);
//		SmartDashboard.putBoolean("Log MotionProfile Stats", true);
//		SmartDashboard.putBoolean("isHighGear", false);
//
//		/*
//		 * since our MP is 10ms per point, set the control frame rate and the notifer to
//		 * half that
//		 */
//		for (CANTalon ct : ctList) {
//			ct.changeMotionControlFramePeriod((int) SmartDashboard.getNumber("Interval", 10) / 2);
//		}
//
//		log.log("Constructing MotionProfileDriveTrainService finished successfully.");
//	}

	/**
	 * Called by application to signal Talon to start the buffered MP (when it's
	 * able to).
	 */
	public void startMotionProfile() {
		log.log("in startMotionProfile()");
		_bStart = true;
	}

	public void moveMotionProfile() {
		for (CANTalon ct : ctList) {
			log.log("ControlMode change is to MotionProfile mode, setting values on Talon.");
			ct.set(_setValue.value);
			log.log("Values set successfully.");

		}
	}

//	public void generateTrajPointList() {
//		log.log("in generateTrajPointList()");
//		conf1 = new MotionProfileConfiguration();
//		conf1.setMaxVel(SmartDashboard.getNumber("Max Velocity", 1));
//		conf1.setInterval((int) SmartDashboard.getNumber("Interval", 1));
//		conf1.setEndDistance(SmartDashboard.getNumber("Distance", 0) * gearRatio());
//		conf1.setMaxAcc(SmartDashboard.getNumber("Max Velocity", 1));
//		conf1.setVelocityOnly(false);
//		this.tpList = mpgSvc.generatePoints(conf1);
//		_notifer.startPeriodic(SmartDashboard.getNumber("Interval", 10) / 2000);
//	}

	/**
	 * Called to clear Motion profile buffer and reset state info during disabled
	 * and when Talon is not in MP control mode.
	 */
	public void stopMotionProfile() {
		log.log("in stopMotionProfile()");
		/*
		 * Let's clear the buffer just in case user decided to disable in the middle of
		 * an MP, and now we have the second half of a profile just sitting in memory.
		 */
		log.log("Clearing TrajectoryPoints from Talon.");
		for (CANTalon ct : ctList) {
			ct.clearMotionProfileTrajectories();
		}

		/* When we do re-enter motionProfile control mode, stay disabled. */
		_setValue = CANTalon.SetValueMotionProfile.Disable;

		/* When we do start running our state machine start at the beginning. */
		_state = MotionProfileState.INACTIVE;
		_loopTimeout = -1;

		/*
		 * If application wanted to start an MP before, ignore and wait for next button
		 * press
		 */
		_bStart = false;
	}

	public void changeControlMode(TalonControlMode mode) {
		log.log("in changeControlMode()");
		for (CANTalon ct : ctList) {
			log.log(String.format("Changing TalonControlMode from [%s] to [%s].", ct.getControlMode().name(),
					mode.name()), "Changing TalonControlMode");
			ct.changeControlMode(mode);
			if (mode == TalonControlMode.MotionProfile) {
				log.log("ControlMode change is to MotionProfile mode");
			}
		}
	}

	/**
	 * Called every loop.
	 */
	public void processPeriodic() {
		/* Get the motion profile status every loop */
		talon.getMotionProfileStatus(talonStatus);
		moveMotionProfile();
		/*
		 * track time, this is rudimentary but that's okay, we just want to make sure
		 * things never get stuck.
		 */
		if (_loopTimeout < 0) {
			/* do nothing, timeout is disabled */
		} else {
			/* our timeout is nonzero */
			if (_loopTimeout == 0) {
				/*
				 * something is wrong. Talon is not present, unplugged, breaker tripped
				 */
				MotionProfileLoggingService.OnNoProgress();
			} else {
				--_loopTimeout;
			}
		}

		/* first check if we are in MP mode */
		if (findControlMode(ctList) != TalonControlMode.MotionProfile) {
			/*
			 * we are not in MP mode. We are probably driving the robot around using
			 * gamepads or some other mode.
			 */
			log.log("Not in MotionProfile mode, do nothing.");
			_state = MotionProfileState.INACTIVE;
			_loopTimeout = -1;
		} else {
			/*
			 * we are in MP control mode. That means: starting Mps, checking Mp progress,
			 * and possibly interrupting MPs if thats what you want to do.
			 */
			log.log("In MotionProfile mode. Evaluating MotionProfileState of " + _state);
			switch (_state) {
			case INACTIVE: /* wait for application to tell us to start an MP */
				log.log("MotionProfileState is INACTIVE. Evaluating start flag of " + _bStart);
				if (_bStart) {
					log.log("Start flag is active.");

					_setValue = CANTalon.SetValueMotionProfile.Disable;
					log.log("Starting TrajectoryPoint filling.");
					log.log("TrajectoryPoint filling completed successfully.");
					/*
					 * MP is being sent to CAN bus, wait a small amount of time
					 */
					log.log("Setting MotionProfileState to: " + MotionProfileState.FILLING_BUFFER);
					_state = MotionProfileState.FILLING_BUFFER;
					_loopTimeout = kNumLoopsTimeout;
					_bStart = false;
				}
				break;
			case FILLING_BUFFER:
				log.log("MotionProfileState is FILLING_BUFFER. Checking if buffer has finished filling.");
				/*
				 * wait for MP to stream to Talon, really just the first few points
				 */
				/* do we have a minimum number of points in Talon */
				if (buffersFilled(mpsList, kMinPointsInTalon)) {
					log.log("Buffer has finished filling. Setting MotionProfileState to RUNNING_PROFILE.");
					/* start (once) the motion profile */
					/* MP will start once the control frame gets scheduled */
					_state = MotionProfileState.RUNNING_PROFILE;
					_loopTimeout = kNumLoopsTimeout;
				}
				break;
			case RUNNING_PROFILE: /* check the status of the MP */
				log.log("MotionProfileState is RUNNING_PROFILE.");
				_setValue = CANTalon.SetValueMotionProfile.Enable;
				/*
				 * if talon is reporting things are good, keep adding to our timeout. Really
				 * this is so that you can unplug your talon in the middle of an MP and react to
				 * it.
				 */
				if (mpsList.stream().allMatch(mps -> !mps.isUnderrun)) {
					_loopTimeout = kNumLoopsTimeout;
				}

				boolean allActivePointsValid = mpsList.stream().allMatch(mps -> mps.activePointValid);
				boolean endOfAllPoints = mpsList.stream().allMatch(mps -> mps.activePoint.isLastPoint);
				/*
				 * If we are executing an MP and the MP finished, start loading another. We will
				 * go into hold state so robot servo's position.
				 */
				if (allActivePointsValid && endOfAllPoints) {
					log.log("Last TrajectoryPoint reached. Setting MotionProfileState to INACTIVE.");
					/*
					 * because we set the last point's isLast to true, we will get here when the MP
					 * is done
					 */
					_setValue = CANTalon.SetValueMotionProfile.Hold;
					_state = MotionProfileState.INACTIVE;
					_loopTimeout = -1;
				}
				break;
			}
		}
		/* printfs and/or logging */
		if (SmartDashboard.getBoolean("Log MotionProfile Stats", true))
			MotionProfileLoggingService.process(mpsList);
	}

	public void startFilling(List<TrajectoryPoint> tpList) {
		System.out.println("started filling");
		_notifer.startPeriodic(SmartDashboard.getNumber("Interval", 10) / 2000);

		log.log("in startFilling()");
		if (tpList == null || tpList.size() == 0) {
			log.log("WARNING! Cannot start filling CANTalon buffer without first setting TrajectoryPointList!");
			return;
		}

		/* did we get an underrun condition since last time we checked ? */
		if (mpsList.stream().anyMatch(mps -> mps.hasUnderrun)) {
			log.log("WARNING! Underrun condition occurred.");
			/* better log it so we know about it */
			MotionProfileLoggingService.OnUnderrun();
			/*
			 * clear the error. This flag does not auto clear, this way we never miss
			 * logging it.
			 */

			for (CANTalon ct : ctList) {
				ct.clearMotionProfileHasUnderrun();
			}
		}
		/*
		 * just in case we are interrupting another MP and there is still buffer points
		 * in memory, clear it.
		 */
		log.log("Clearing previous TrajectoryPoints if they exist.", false);
		for (CANTalon ct : ctList) {
			ct.clearMotionProfileTrajectories();
		}

		/* This is fast since it's just into our TOP buffer */
		log.log("Pushing TrajectoryPoints to Talon.");
		for (TrajectoryPoint tp : tpList) {
			for (CANTalon ct : ctList) {
				ct.pushMotionProfileTrajectory(tp);
			}
		}
	}

	/**
	 *
	 * @return the output value to pass to Talon's set() routine. 0 for disable
	 *         motion-profile output, 1 for enable motion-profile, 2 for hold
	 *         current motion profile trajectory point.
	 */

	private TalonControlMode findControlMode(List<CANTalon> talonList) {
		TalonControlMode mode = null;
		for (CANTalon ct : talonList) {
			TalonControlMode nextMode = ct.getControlMode();
			if (mode == null)
				mode = nextMode;
			else if (mode != ct.getControlMode())
				log.log(String.format(
						"Warning: Expected all CANTalon control modes to be equal but found modes [%s] and [%s].", ct,
						nextMode));
		}

		return mode;
	}

	private boolean buffersFilled(List<MotionProfileStatus> mpStatusList, int minBufferSize) {
		log.log("in buffersFilled()");
		int totalFilled = 0;
		int talonCount = 0;
		log.log("number of motion profile statuses " + mpStatusList.size());
		for (MotionProfileStatus mps : mpStatusList) {
			log.log(String.format("Buffer Count [%s] Min buffer Size [%s]", mps.btmBufferCnt, minBufferSize),
					String.valueOf(Math.random()));
			talonCount++;
			if (mps.btmBufferCnt > minBufferSize)
				totalFilled++;
		}

		if (totalFilled == talonCount)
			return true;

		log.log(String.format("Only [%s] of [%s] buffers filled.", totalFilled, talonCount), "buffer-not-filled-msg");
		return false;
	}

	public void resetEnc() {
		talon.setEncPosition(0);
	}



}
