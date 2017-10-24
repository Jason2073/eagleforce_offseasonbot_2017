package org.usfirst.frc.team2073.robot.conf;

public abstract class AppConstants {
	public abstract class Controllers {
		public abstract class PowerStick {
			public static final int PORT = 1;
			public abstract class ButtonPorts {
				public static final int LEFT = 4;
			}	
		}
		public abstract class DriveWheel {
			public static final int PORT = 2;
			public abstract class ButtonPorts {
				public static final int LPADDLE = 1;
			}	
		}
		public abstract class Xbox {
			public static final int PORT = 0;
			public abstract class ButtonPorts {
				public static final int X = 3;
				public static final int A = 1;
				public static final int B = 2;
				public static final int Y = 4;
				public static final int L1 = 5;
				public static final int R1 = 6;
				public static final int L2 = 7;
				public static final int R2 = 8;
			}
		}
	}
	public abstract class RobotPorts {
		// Drivetrain
		public static final int LEFT_MOTOR = 7;
		public static final int LEFT_MOTOR_SLAVE = 8;
		public static final int RIGHT_MOTOR = 3;
		public static final int RIGHT_MOTOR_SLAVE = 1;
		public static final int DRIVE_SOLENOID_1 = 0;
		public static final int DRIVE_SOLENOID_2 = 1;
		
		// Ball Intake
		public static final int BALL_INTAKE_SOLENOID_1 = 2;
		public static final int BALL_INTAKE_SOLENOID_2 = 3;
		public static final int BALL_INTAKE_MOTOR_1 = 4;
		public static final int BALL_INTAKE_MOTOR_2 = 5;
		
		// Climber
		public static final int CLIMBER_MOTOR = 6;
		
		// Intermediate
		public static final int BELLY_ROLLERS = 1;
		public static final int INTERMEDIATE = 2;
		
		// Turret Shooter
		public static final int TURRET_POSITION = 3;
		public static final int TURRET_SHOOTER_1 = 4;
		public static final int TURRET_SHOOTER_2 = 5;
		
		// Gear Intake
		public static final int GEAR_INTAKE_MOTOR = 9;
		public static final int GEAR_INTAKE_TALON = 0;
		public static final int LIGHT_SENSOR = 2;
		public static final int MAGNET_ZEROER = 0;
	}
}
