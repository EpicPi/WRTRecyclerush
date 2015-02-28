// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2974.Ralph;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static VictorSP driveTrainFrontRight;
    public static VictorSP driveTrainBackRight;
    public static VictorSP driveTrainFrontLeft;
    public static VictorSP driveTrainBackLeft;
    
	public static CANTalon elevatorTalon; 
	public static CANTalon grabberTalon;
	
    public static DigitalInput digital0;
    public static DigitalInput digital1;
    public static DigitalInput digital2;
    public static DigitalInput digital3;
    public static DigitalInput digital4;
    public static DigitalInput digital5;
    public static DigitalInput digital6;
    public static DigitalInput digital7;
    public static DigitalInput digital8;
    public static DigitalInput digital9;
    
    public static AnalogInput analog0;
    public static AnalogInput analog1;
    public static AnalogInput analog2;
    public static AnalogInput analog3;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
       
    	driveTrainFrontRight = new VictorSP(2);
        LiveWindow.addActuator("DriveTrain", "FrontRight", (VictorSP) driveTrainFrontRight);
        
        driveTrainBackRight = new VictorSP(0);
        LiveWindow.addActuator("DriveTrain", "BackRight", (VictorSP) driveTrainBackRight);
        
        driveTrainFrontLeft = new VictorSP(3);
        LiveWindow.addActuator("DriveTrain", "FrontLeft", (VictorSP) driveTrainFrontLeft);
        
        driveTrainBackLeft = new VictorSP(1);
        LiveWindow.addActuator("DriveTrain", "BackLeft", (VictorSP) driveTrainBackLeft);
        
    	elevatorTalon = new CANTalon(1); 
    	grabberTalon = new CANTalon(2);
    	
    	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        digital0 = new DigitalInput(0);
        digital1 = new DigitalInput(1);
        digital2 = new DigitalInput(2);
        digital3 = new DigitalInput(3);
        digital4 = new DigitalInput(4);
        digital5 = new DigitalInput(5);
        digital6 = new DigitalInput(6);
        digital7 = new DigitalInput(7);
        digital8 = new DigitalInput(8);
        digital9 = new DigitalInput(9);
        
        analog0 = new AnalogInput(0);
        analog1 = new AnalogInput(1);
        analog2 = new AnalogInput(2);
        analog3 = new AnalogInput(3);
 
    }
}
