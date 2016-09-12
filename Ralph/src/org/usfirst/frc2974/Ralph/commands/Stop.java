package org.usfirst.frc2974.Ralph.commands;

import org.usfirst.frc2974.Ralph.Robot;
import org.usfirst.frc2974.Ralph.subsystems.Forklift.Mode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Stop extends Command {

    public Stop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.grabber);
    	requires(Robot.forklift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.setSpeeds(0,0,0);
    	Robot.forklift.setMode(Mode.power);
    	Robot.forklift.setElevatorPower(0);
    	Robot.grabber.setGrabberPower(0, true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
