// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc2974.Ralph.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2974.Ralph.Robot;

import org.usfirst.frc2974.Ralph.autonomous.MoveStraightByTime;
import org.usfirst.frc2974.Ralph.autonomous.MoveElevator;
import org.usfirst.frc2974.Ralph.autonomous.StrafeByTime;
import org.usfirst.frc2974.Ralph.autonomous.TurnByTime;


/**
 *
 */
public class AutonomousCommand extends Command {

	private Command current = null;

	public AutonomousCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putNumber("Max Power", 1);
		SmartDashboard.putNumber("Turn Direction", 1);
		//can take value 1 or -1 to show direction of turning
		SmartDashboard.putNumber("Strafe Direction", -1);
		//takes a value of 1 or -1 to show direction of strafing
		
		SmartDashboard.putNumber("Time", 1);
		
		SmartDashboard.putNumber("clawRunTime", 1.0);// placeholder values
		SmartDashboard.putNumber("clawRunPower", 0.0);

		SmartDashboard.putNumber("elevatorHeight", .5);
		SmartDashboard.putNumber("elevatorRunTime", 1.0);
		SmartDashboard.putNumber("elevatorRunPower", 0.5);

		SmartDashboard.putString("Debug", "Initialized OK");
		SmartDashboard.putNumber("CommandTime", 0.0);
		Robot.forklift.setPowerMode();
		Robot.forklift.resetPot();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		SmartDashboard.putNumber("CommandTime", timeSinceInitialized());
		SmartDashboard.putNumber("elevatorTarget", Robot.forklift.currentTarget());
		SmartDashboard.putNumber("elevatorError", Robot.forklift.currentError());
		SmartDashboard.putNumber("elevatorPosition", Robot.forklift.currentPosition());
		if (current == null) {
			if (Robot.oi.right.getRawButton(6)) {
				SmartDashboard.putString("Debug", "In button 6 press");
				current = new MoveStraightByTime(
						SmartDashboard.getNumber("Time"),
						SmartDashboard.getNumber("Max Power")
						);
				Scheduler.getInstance().add(current);
			}
			if (Robot.oi.right.getRawButton(7)) {
				SmartDashboard.putString("Debug", "In button 7 press");
				Robot.forklift.setPositionMode();
				current = new MoveElevator(
						SmartDashboard.getNumber("elevatorHeight"));
				Scheduler.getInstance().add(current);
			}
//			if (Robot.oi.right.getRawButton(8)) {
//				Robot.forklift.setPowerMode();
//				current = new TestClawInPowerMode(
//						SmartDashboard.getNumber("clawRunTime"),
//						SmartDashboard.getNumber("clawRunPower"));
//				Scheduler.getInstance().add(current);
//			}
			if (Robot.oi.right.getRawButton(8)) {
				Robot.forklift.setPowerMode();
//				current = new TestElevatorInPowerMode(
//						SmartDashboard.getNumber("elevatorRunTime"),
//						SmartDashboard.getNumber("elevatorRunPower"));
				//TODO add this back in CORRECTLY; this part needs refurbishing it seems :)
				Scheduler.getInstance().add(current);
			}
			
			if (Robot.oi.right.getRawButton(9)) {
				SmartDashboard.putString("Debug", "In button 9 press");
				current = new TurnByTime(
						SmartDashboard.getNumber("Time"),
						SmartDashboard.getNumber("Turn Direction")
						);
				Scheduler.getInstance().add(current);
			}
		
			if (Robot.oi.right.getRawButton(10)) {
				SmartDashboard.putString("Debug", "In button 10 press");
				current = new StrafeByTime(
						SmartDashboard.getNumber("Time"),
						SmartDashboard.getNumber("Strafe Direction")
						);
				Scheduler.getInstance().add(current);
			}
		} else {
			if (!current.isRunning()) {
				SmartDashboard.putString("Debug", "Command done, waiting");
				current = null;
			}

		}

	}

	// Make this return true when this Command no longer needs to run
	// execute()
	protected boolean isFinished() {

		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.forklift.setPowerMode();
	}

	// Called when another command which requires one or more of the
	// same subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
