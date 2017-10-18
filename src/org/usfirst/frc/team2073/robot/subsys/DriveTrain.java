package org.usfirst.frc.team2073.robot.subsys;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.usfirst.frc.team2073.robot.domain.MotionProfileConfiguration;
import org.usfirst.frc.team2073.robot.svc.MotionProfileGenerationService;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

//	making a new talon this is your motor controller that everything will run through
	private CANTalon talon;
	
//	Create a MotionProfileStatus, this is used to monitor the talon and if anything has gone wrong with your profiles
	private CANTalon.MotionProfileStatus talonStatus = new CANTalon.MotionProfileStatus();
//	this is how we are generating profiles, we pass a motion profile config into the generate and generate a list of trajectory points
	private MotionProfileGenerationService mpGen = new MotionProfileGenerationService();
	
//	these are all of our parameters for the profile
	private static final int INTERVAL_MS = 10;
//	private static final double MAX_VEL = 700;
//	private static final double END_DISTANCE = 1000;
//	private static final double MAX_ACCELERATION = 5;
	
	
	
//	we need our delay to be around twice as often as each point (the .001 is just to convert to seconds)
	private static final double DELAY = (INTERVAL_MS / 2) * .001;

	private static final String MAX_VEL_KEY = "max.vel";
	private static final String END_DISTANCE_KEY = "end.distnace";
	private static final String MAX_ACCELERATION_KEY = "max.acc";

	private static final double MAX_VEL_DEFAULT = 700;
	private static final double END_DISTANCE_DEFAULT = 1000;
	private static final double MAX_ACCELERATION_DEFAULT = 5;
	
	
//	this is our generated list of points
	private List<TrajectoryPoint> tpList;
	private Queue<TrajectoryPoint> tpQueue;
//	our configuration where we store the parameters for the profile
	private MotionProfileConfiguration conf1;

	private int numberPoints;
	private int topBuf = -1;
	private int btmBuf = -1;
	private boolean bufferFilled = false;
	private boolean filled = false;
	
	// TODO: Extract all these booleans to a state machine
	private boolean generating = false;
	private boolean profileComplete = false;

	boolean printed = false;
	
	public DriveTrain() {
//		System.out.println("-> DriveTrain constructor");
//		System.out.println("<- DriveTrain constructor");
	}
	
	public void init(CANTalon talon) {
		SmartDashboard.putNumber(MAX_VEL_KEY, MAX_VEL_DEFAULT);
		SmartDashboard.putNumber(END_DISTANCE_KEY, END_DISTANCE_DEFAULT);
		SmartDashboard.putNumber(MAX_ACCELERATION_KEY, MAX_ACCELERATION_DEFAULT);
		
		this.talon = talon;

//		how often in ms the talon will be looking for updates
		talon.changeMotionControlFramePeriod(INTERVAL_MS / 2);

//		F gain! this is the only part you need to tune to have profiles run correctly, start with theoretical value
		talon.setF(.7871); // TODO: Extract to constant
		
//		This is what the talon will use to check position and correct itself
		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder); // TODO: Extract? Will this change?

	}
	
    @Override
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public boolean isProfileComplete() {
		return profileComplete;
	}
    
    public void generateMotionProfile() {
		
		if(!generating) {
			generating = true;
			generatePoints();
		}
		generating = false;
    }
    
    public void doMotionProfile() {
		
//    	if(profileComplete) {
//    		System.out.println("Profile complete, skipping.");
//    		return;
//    	}
    	
		if (!filled) {
			profileComplete = false;
			System.out.println("Resetting Talon");

//			Change to motion profile mode
			talon.changeControlMode(TalonControlMode.MotionProfile);
//			first disable the talon in case the motor is moving
			talon.set(CANTalon.SetValueMotionProfile.Disable.value);
//	 		create a queue (it's like a list but the data is deleted after it is used) arrayLists can also be used depending on your needs
//			clear any points remaining in the motion profile buffer
			talon.clearMotionProfileTrajectories();

			System.out.println("Resetting Talon complete!");
			printed = false;

			System.out.println("Pushing points to Talon");
			int i = 0;
			for (TrajectoryPoint tp : tpQueue) {
				i++;
				talon.getMotionProfileStatus(talonStatus);

				// Extremely resource heavy, uncomment for debugging only
//				System.out.printf("SEZSHWANNNN: Cnt: %s \tRem: %s \tQ-Rem: %s\n"
//						, talonStatus.topBufferCnt, talonStatus.topBufferRem, tpQueue.size() - i);
				
				talon.pushMotionProfileTrajectory(tp);
			}

			System.out.println("Pushing points to Talon complete!");
			
			
			filled = true;
		} else {
			if (btmBuf > 5) {
				bufferFilled = true;
			}
			talon.getMotionProfileStatus(talonStatus);
			
			// Extremely resource heavy, uncomment for debugging only
//			System.out.printf("topCnt: %s \t topRem: %s \t btmCnt: %s \n"
//					, talonStatus.topBufferCnt, talonStatus.topBufferRem, talonStatus.btmBufferCnt);
			
			talon.processMotionProfileBuffer();
			if (bufferFilled) {
//				System.out.println("Buffer filled. Setting talon to enabled");
				talon.set(CANTalon.SetValueMotionProfile.Enable.value);
			}
			if (bufferFilled && talonStatus.topBufferCnt == 0 && talonStatus.btmBufferCnt == 0) {
				System.out.println("Profile complete");
				profileComplete = true;
			}
		}
    }

    public void move() {
		
		talon.changeControlMode(TalonControlMode.PercentVbus);
		talon.set(.5);
    }
    
    public void stop() {
		talon.set(CANTalon.SetValueMotionProfile.Disable.value);
		talon.clearMotionProfileTrajectories();
		filled = false;
    }

	private void generatePoints() {
		
//		Make a motion profile config and set all of our values
		conf1 = new MotionProfileConfiguration();
		conf1.setForwards(false);
		conf1.setInterval(INTERVAL_MS);
		
		conf1.setMaxVel(SmartDashboard.getNumber(MAX_VEL_KEY, MAX_VEL_DEFAULT));
		conf1.setEndDistance(SmartDashboard.getNumber(END_DISTANCE_KEY, END_DISTANCE_DEFAULT));
		conf1.setMaxAcc(SmartDashboard.getNumber(MAX_ACCELERATION_KEY, MAX_ACCELERATION_DEFAULT));
		
//		generate list from the config
		tpList = mpGen.generatePoints(conf1);
		tpQueue = new LinkedList<>(tpList);
		
		numberPoints = tpList.size();
			
	}
	
	/**
	 * This should get called every iteration
	 */
	public void periodic() {
//		this lets the talon and the status work together 
		talon.getMotionProfileStatus(talonStatus);
//		we are just using this to see how many points are in each buffer
		topBuf = talonStatus.topBufferCnt;
		btmBuf = talonStatus.btmBufferCnt;
		
		if(!printed) {
			System.out.println("In operatorControl()");
			printed = true;
		}
	}
}

