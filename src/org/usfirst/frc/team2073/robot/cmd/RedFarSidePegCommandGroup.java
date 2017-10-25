package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedFarSidePegCommandGroup extends CommandGroup{
	private DrivetrainSubsystem drivetrain;
	public RedFarSidePegCommandGroup() {
		drivetrain = RobotMap.getDrivetrain();
		drivetrain.setMotionProfileDriveDistance(114);
		addSequential( new MoveForwardMpCommand());
		drivetrain.setMotionProfilePointTurnAngle(30);
		addSequential(new PointTurnCommand());
		drivetrain.setMotionProfileDriveDistance(40);
		addSequential( new MoveForwardMpCommand());
		addParallel(new GearIntakeToPlaceCommand());
		addParallel(new GearOuttakeCommand());
		
		
		

	
	}
}
