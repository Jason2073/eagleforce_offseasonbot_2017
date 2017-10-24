package org.usfirst.frc.team2073.robot.cmd;

import org.usfirst.frc.team2073.robot.OI;
import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.GearIntakeSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearIntakeCommandGroup extends CommandGroup {
	private final GearIntakeSubsystem gearIntake;
	private final Joystick controller;

	public GearIntakeCommandGroup() {
		gearIntake = RobotMap.getGearIntake();
		controller = OI.getController();

	/*	if(gearSub.getApproxAngle() < 1 && gearSub.getApproxAngle() > -1 && controller.getPOV() == 90) {
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
		}*/
		addSequential(new GearIntakeToDownCommand());
		addSequential(new GearIntakeToPlaceCommand());
		addSequential(new GearIntakeResetCommand());
		if(controller.getPOV() == 180 && gearSub.isZero()) {
		}else if(controller.getPOV() == 90 && gearSub.isZero()) {
		}else {
		}
	}
}
