package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.OI;
import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearIntakeCommandGroup extends CommandGroup {
	private GearIntakeSubsystem gearSub = RobotMap.getGearIntake();
	private Joystick controller = OI.getController();

    public GearIntakeCommandGroup() {
    	if(gearSub.getApproxAngle() < 1 && gearSub.getApproxAngle() > -1 && controller.getPOV() == 90) {
    		addSequential(new GearIntakeToDownCommand());
    	}
    	if(gearSub.getApproxAngle() < 50 && gearSub.getApproxAngle() > 40 && controller.getPOV() == 90) {
    		addSequential(new GearIntakeToDownCommand());
    	}else if(gearSub.getApproxAngle() < 1 && gearSub.getApproxAngle() > -1 && controller.getPOV() == 45) {
    		addSequential(new GearIntakeToPlaceCommand());
    	}else if(gearSub.getApproxAngle() < 95 && gearSub.getApproxAngle() > 85 && controller.getPOV() == 45) {
    		addSequential(new GearIntakeToPlaceCommand());
    	}else {
    		addSequential(new GearIntakeResetCommand());
    	}
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
