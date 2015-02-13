package org.usfirst.frc2974.Ralph.subsystems;
import org.usfirst.frc2974.Ralph.RobotMap;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
//Forklift subsystem
//Robot will have:
//Potentiometer to see lift's position on robot
//switch to see bottom
//2 CAN Talons - 1 for elevator, 1 for claw
//-possibly- a brake on elevator(PID controlled), need to turn on/off;

/**DESIGNERS' NOTE: DO NOT USE A VALUE OF 1 FOR A PARAMETER FOR ANYTHING UNLESS YOU 
*WANT THE ROBOT TO LAUNCH ITS PARTS AT HIGH SPEEDS AT SOMETHING
*.....seriously
*/

public class Forklift extends Subsystem
{
	public void initDefaultCommand(){
		setDefaultCommand(null);
	}

	public final double NEUTRAL_POS = 6;
	//after elevator is zeroed, raises to this for safe driving

	private double zeroPosition = 0;
	
	//TODO get input from limit switch
	private double rampRate;
	private int izone;
	private double p = 10;
	private double i = 0;
	private double d = 0;
	private double f = 0;
	private double deadband = 0.25;
	public final int profile1 = 1;
	
	//TODO set values for p, i, d 
	public final double LEVEL_MULTIPLIER = 20;
	//TODO determine by how much the level # (1, 2, 3) must be multiplied to get postiion to raise the arm
	//pos is in rotations: LEVEL_MULTIPLIERS = # rotations to raise the tote 1 level
	public final double HEIGHT_CONSTANT = -12.1635; // Calibrated against 
	
	public final double MAX_POSITION = 55;//to be calibrated
	
	CANTalon elevatorTalon; 
	
	public Forklift()
	{

		
		
		elevatorTalon = RobotMap.elevatorTalon;

		elevatorTalon.ConfigRevLimitSwitchNormallyOpen(true);//assuming up = down, rev = up
		elevatorTalon.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);//potentiometer gives feedback
		
		setPowerMode();
	}
	//These are methods used in ForkliftZero command
	public void setPowerMode()
	{
		elevatorTalon.changeControlMode(CANTalon.ControlMode.PercentVbus);
		//will there be brake functionality?
		elevatorTalon.enableControl();
	}
	
	public void setPositionMode()
	{
		Preferences prefs = Preferences.getInstance();
		p = prefs.getDouble("E_P", 3);
		i = prefs.getDouble("E_I", 0.02);
		d = prefs.getDouble("E_D", 0.0);
		izone = prefs.getInt("E_Izone", 5);
		deadband = Math.abs(prefs.getDouble("E_Deadband", 0.25) * HEIGHT_CONSTANT);
		rampRate = Math.abs(prefs.getDouble("E_RampRate", 10));
		elevatorTalon.setPID(p, i, d, f, izone, rampRate, profile1);
		elevatorTalon.changeControlMode(CANTalon.ControlMode.Position);
		elevatorTalon.set(elevatorTalon.getPosition());
		elevatorTalon.enableControl();
	}
	
	//returns if the forklift is at the bottom and has closed the limit switch
	public boolean isZero()
	{
		return elevatorTalon.isFwdLimitSwitchClosed();
	}
	

	public void resetPot()
	{
		zeroPosition = elevatorTalon.getPosition();		
	}	
	
	// <\methods used in ForkliftZero>
	

	public void setElevatorPosition(double height)
	{		
		double pos = HEIGHT_CONSTANT*height+zeroPosition;
		elevatorTalon.set(pos);
	}
	
	//raises/lowers to a variable height(not dependent on levels)
	public void setElevatorPower(double power)
	{
		power= Math.min(Math.max(power,-1), 1);		
		elevatorTalon.set(-power);		
	}
	
	public boolean isAtPosition()
	{
		return Math.abs(elevatorTalon.getClosedLoopError()) < deadband;		
	}
	
	public void incrementElevatorPos(double dheight)
	{
		double height = currentTarget() + dheight;
		height= Math.min(Math.max(height,zeroPosition), MAX_POSITION);	
		setElevatorPosition(height);
	}
	
	public double currentError()
	{
		return elevatorTalon.getClosedLoopError()/HEIGHT_CONSTANT;
	}
	
	public double currentTarget()
	{
		return (elevatorTalon.getSetpoint()-zeroPosition)/HEIGHT_CONSTANT;
	}
	
	public double currentPosition()
	{
		return (elevatorTalon.getPosition()-zeroPosition)/HEIGHT_CONSTANT;
	}
	
	
}
