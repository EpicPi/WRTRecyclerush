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

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2974.Ralph.autonCommandGroups.DoNothing;
import org.usfirst.frc2974.Ralph.autonCommandGroups.Forward_NoStep;
import org.usfirst.frc2974.Ralph.autonCommandGroups.Forward_Step;
import org.usfirst.frc2974.Ralph.autonCommandGroups.OneTote_Dynamic;
import org.usfirst.frc2974.Ralph.autonCommandGroups.OneTote_NoStep;
import org.usfirst.frc2974.Ralph.autonCommandGroups.OneTote_Step;
import org.usfirst.frc2974.Ralph.autonCommandGroups.*;
import org.usfirst.frc2974.Ralph.commands.*;
import org.usfirst.frc2974.Ralph.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	public static Camera cam;
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Forklift forklift;
	public static Inputs inputs;
	public static Grabber grabber;
	private SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		SmartDashboard.putNumber("AutonMoveForwardTime", 6); // used in dynamic
																// movelinear//
																// TODO remove
																// eventually
		RobotMap.init();

		driveTrain = new DriveTrain();
		forklift = new Forklift();
		grabber = new Grabber();
		cam = new Camera();

		// After subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.

		oi = new OI();
		inputs = new Inputs();

		// TODO decrease wait time for auton
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Do Nothing", new DoNothing());
		autoChooser.addObject("No Step - one tote", new OneTote_NoStep());
		autoChooser.addObject("Step - one tote", new OneTote_Step());
		autoChooser.addObject("No step - forward", new Forward_NoStep());
		autoChooser.addObject("Step - forward", new Forward_Step());
		autoChooser.addObject("Dynamic - one tote", new OneTote_Dynamic());
		autoChooser.addObject("Dynamic - forward", new Forward_Dynamic());
		SmartDashboard.putData("PICK AN AUTONOMOUS", autoChooser);
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomousCommand = (Command) autoChooser.getSelected();
		autonomousCommand.start();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {

		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Scheduler.getInstance().add(new UpDownTeleop());
		Scheduler.getInstance().add(new Grab());
		Scheduler.getInstance().add(new Drive());
		Scheduler.getInstance().add(new Camera());
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		if ((Robot.oi.xbox.getButton(Gamepad.Button.B))) {
			Scheduler.getInstance().add(new Stop());
		}
		SmartDashboard.putData(Scheduler.getInstance());
		cam.updateSmartDashboard();
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
