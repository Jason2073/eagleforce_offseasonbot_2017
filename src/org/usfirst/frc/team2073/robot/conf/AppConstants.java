package org.usfirst.frc.team2073.robot.conf;

public abstract class AppConstants {
	public abstract class Controllers {
		public abstract class PowerStick{
			public static final int PORT = 1;
			public abstract class ButtonPorts {
				public static final int CENTER = 4;
				
			}	
		}
		public abstract class DriveWheel{
			public static final int PORT = 2;
			public abstract class ButtonPorts {
				public static final int LPADDLE = 1;
				
			}	
		}
		
		public abstract class Xbox {
			public static final int PORT = 0;
			public abstract class ButtonPorts {
				public static final int X = 1;
				public static final int A = 2;
				public static final int B = 3;
				public static final int Y = 4;
				public static final int L1 = 5;
				public static final int R1 = 6;
				public static final int L2 = 7;
				public static final int R2 = 8;
				public static final int EAST = 90;
				public static final int NORTHEAST = 45;
				public static final int NONE = 0;
				
			}	
		}	
	}
}
