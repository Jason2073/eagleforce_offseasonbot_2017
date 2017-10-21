package org.usfirst.frc.team2073.robot;

import org.usfirst.frc.team2073.robot.ctx.AppContext;
import org.usfirst.frc.team2073.robot.ctx.OI;
import org.usfirst.frc.team2073.robot.ctx.RobotMap;
import org.usfirst.frc.team2073.robot.subsys.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	private static AppContext ctx;
	private OI oi;
	private RobotMap rm;
	private Drivetrain dt;

	@Override
	public void robotInit() {
//    	System.out.println("-> robotInit()");
		ctx = AppContext.getInstance();
		ctx.init();
		oi = ctx.getOi();
		rm = ctx.getRobotMap();
		dt = rm.getDriveTrain();
//    	System.out.println("<- robotInit()");
	}

	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
		dt.move(oi.joystick.getY(), oi.wheel.getX());
		
	}
	
	public static AppContext getCtx() {
//    	System.out.println("-> getCtx()");
//    	System.out.println("<- getCtx()");
		return ctx;
	}
}
