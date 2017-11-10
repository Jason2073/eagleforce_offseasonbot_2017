package org.usfirst.frc.team2073.robot.cmd.gearposition;

import org.usfirst.frc.team2073.robot.RobotMap;
import org.usfirst.frc.team2073.robot.cmd.gearintake.GearIntakeHoldCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class HardResetAndHoldCommandGroup extends CommandGroup {
	private DigitalInput sensor = RobotMap.getLightSensor();

	public HardResetAndHoldCommandGroup() {
		addParallel(new GearIntakeHardResetCommand());
		addParallel(new GearIntakeHoldCommand());
	}
	
	@Override
	protected boolean isFinished() {
		return !sensor.get();
	}
}
