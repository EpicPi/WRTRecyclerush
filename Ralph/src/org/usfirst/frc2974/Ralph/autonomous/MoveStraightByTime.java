package org.usfirst.frc2974.Ralph.autonomous;

import org.usfirst.frc2974.Ralph.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveStraightByTime extends Command {
	
	/**
	 * The amount of time the robot must travel
	 */
	private double timeToTravel;
	/**
	 * The speed at which the robot must travel
	 */
	private double power;
	
	private final double C_ACCEL = 4;
	private final double C_DECEL = -4;
	
    /**
     * Creates a MoveStraightByTime command that goes for the desired time and with desired speed
     * @param timeToTravel the amount of time to travel
     * @param power the speed to travel with    1 for forward   2 to 
     */
    public MoveStraightByTime(double timeToTravel, double power) {
    	requires(Robot.driveTrain);
    	this.timeToTravel = timeToTravel;
    	this.power = -power;
    	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.setSpeeds(0,0,0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double time = timeSinceInitialized();
    	double powerSteady = Math.abs(power);
    	double powerAccel = C_ACCEL * time;
    	double powerDecel = C_DECEL * (time - timeToTravel);
    	double powerOut = Math.min(Math.min( powerAccel, powerDecel), powerSteady);
    	SmartDashboard.putNumber("Motor Power", powerOut);
    	Robot.driveTrain.setSpeeds(Math.signum(power)*powerOut, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timeSinceInitialized()>timeToTravel;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setSpeeds(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
