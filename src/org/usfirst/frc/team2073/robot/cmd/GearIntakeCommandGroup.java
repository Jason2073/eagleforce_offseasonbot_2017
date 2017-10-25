package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.OI;
import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.GearPositionSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearIntakeCommandGroup extends CommandGroup {
	private final GearPositionSubsystem gearIntake;
	private final Joystick controller;

	public GearIntakeCommandGroup() {
		gearIntake = RobotMap.getGearPosition();
		controller = OI.getController();

	/*	if(gearIntake.getApproxAngle() < 1 && gearIntake.getApproxAngle() > -1 && controller.getPOV() == 90) {
			addSequential(new GearIntakeToDownCommand());
		}
		if(gearIntake.getApproxAngle() < 50 && gearIntake.getApproxAngle() > 40 && controller.getPOV() == 90) {
			addSequential(new GearIntakeToDownCommand());
		}else if(gearIntake.getApproxAngle() < 1 && gearIntake.getApproxAngle() > -1 && controller.getPOV() == 45) {
			addSequential(new GearIntakeToPlaceCommand());
		}else if(gearIntake.getApproxAngle() < 95 && gearIntake.getApproxAngle() > 85 && controller.getPOV() == 45) {
			addSequential(new GearIntakeToPlaceCommand());
		}else {
			addSequential(new GearIntakeResetCommand());
		}*/
		addSequential(new GearIntakeToDownCommand());
		addSequential(new GearIntakeToPlaceCommand());
		addSequential(new GearIntakeResetCommand());
		if(controller.getPOV() == 180 && gearIntake.isZero()) {
		}else if(controller.getPOV() == 90 && gearIntake.isZero()) {
		}else {
		}
	}
}
