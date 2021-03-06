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
				public static final int A = 1;
				public static final int B = 2;
				public static final int X = 3;
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
		public static final int DRIVE_SOLENOID_2 = 7;
		
		// Ball Intake
		public static final int BALL_INTAKE_SOLENOID_1 = 2;
		public static final int BALL_INTAKE_SOLENOID_2 = 3;
		public static final int BALL_INTAKE_MOTOR_1 = 4;
		public static final int BALL_INTAKE_MOTOR_2 = 5;
		
		// Climber
		public static final int CLIMBER_MOTOR = 1;
		
		// Intermediate
		public static final int BELLY_ROLLERS = 11;
		public static final int INTERMEDIATE = 2;
		
		// Turret Shooter
		public static final int TURRET_POSITION = 33;
		public static final int TURRET_SHOOTER_1 = 4;
		public static final int TURRET_SHOOTER_2 = 5;
		
		// Gear Intake
		public static final int GEAR_INTAKE_MOTOR = 0;
		public static final int GEAR_INTAKE_TALON = 0;
		public static final int LIGHT_SENSOR = 2;
		public static final int MAGNET_ZEROER = 0;
	}
	public abstract class DashboardKeys {
		public static final String INVERSE = "Inverse";
		public static final String SENSE = "Sense";
		public static final String RPM = "RPM";
		public static final String SET_F = "Set F";
		public static final String SET_P = "Set P";
		public static final String SET_I = "Set I";
		public static final String SET_D = "Set D";
		public static final String FGAIN = "Fgain";
	}
	public abstract class Defaults {
		public static final double FGAIN = 8.7871;
	}
	public abstract class Subsystems {
		public abstract class Drivetrain {
			public static final String NAME = "Drivetrain";
			public static final double WHEEL_DIAMETER = 4 * Math.PI;
			public static final double AUTONOMOUS_MAX_VELOCITY = 300;
			public static final double AUTONOMOUS_MAX_ACCELERATION = 50;
			public static final double ROBOT_WIDTH = 29;
			public static final double HIGH_GEAR_RATIO = 4.89;
			public static final double LOW_GEAR_RATIO = 15.41;
			public abstract class ComponentNames {
				public static final String LEFT_MOTOR = "Left Motor";
				public static final String LEFT_MOTOR_SLAVE = "Left Motor Slave";
				public static final String RIGHT_MOTOR = "Right Motor";
				public static final String RIGHT_MOTOR_SLAVE = "Right Motor Slave";
				public static final String SOLENOID_1 = "Solenoid 1";
				public static final String SOLENOID_2 = "Solenoid 2";
			}
		}
		public abstract class Turret {
			public static final String NAME = "Turret";
			public static final int TURRET_POSITION_CODES_PER_REV = 10;
			public abstract class ComponentNames {
				public static final String POS = "Pos";
				public static final String SHOOTER_1 = "Shooter 1";
				public static final String SHOOTER_2 = "Shooter 2";
			}
		}
		public abstract class GearPosition {
			public static final String NAME = "Gear Position";
			public abstract class ComponentNames {
				public static final String TALON = "Talon";
				public static final String MAGNET_ZEROER = "Magnet Zeroer";
			}
		}
		public abstract class GearIntake {
			public static final String NAME = "Gear Intake";
			public abstract class ComponentNames {
				public static final String INTAKE_MOTOR = "Intake Motor";
				public static final String LIGHT_SENSOR = "Light Sensor";
			}
		}
		public abstract class BallIntake {
			public static final String NAME = "Ball Intake";
			public abstract class ComponentNames {
				public static final String MOTOR_1 = "Motor 1";
				public static final String MOTOR_2 = "Motor 2";
				public static final String SOLENOID_1 = "Solenoid 1";
				public static final String SOLENOID_2 = "Solenoid 2";
			}
		}
		public abstract class Intermediate {
			public static final String NAME = "Intermediate";
			public abstract class ComponentNames {
				public static final String MAIN = "Up to Turret";
				public static final String BELL_ROLLER = "Belly Rollers";
			}
		}
		public abstract class Climber {
			public static final String NAME = "Climber";
			public abstract class ComponentNames {
				public static final String MOTOR = "Motor";
			}
		}
	}
}
