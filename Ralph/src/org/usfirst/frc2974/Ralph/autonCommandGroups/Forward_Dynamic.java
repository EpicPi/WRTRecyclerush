package org.usfirst.frc2974.Ralph.autonCommandGroups;

import org.usfirst.frc2974.Ralph.autonomous.InitElevator;
import org.usfirst.frc2974.Ralph.autonomous.MoveElevator;
import org.usfirst.frc2974.Ralph.autonomousDynamic.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Forward_Dynamic extends CommandGroup {
    
    public  Forward_Dynamic() {
    	double autonMoveForwardTime = 6;
    	double autonMoveForwardSpeed = .4;
    	double autonTurnTime = .75;
    	
    	SmartDashboard.putNumber("AutonMoveForwardTime", autonMoveForwardTime);
    	SmartDashboard.putNumber("AutonMoveForwardSpeed", autonMoveForwardSpeed);
    	SmartDashboard.putNumber("AutonTurnTime", autonTurnTime);
    	addSequential(new InitElevator());
    	addSequential(new MoveLinear_Dynamic(autonMoveForwardTime,autonMoveForwardSpeed));
    	addSequential(new Turn_Dynamic(autonTurnTime,Math.signum(autonTurnTime)));
    	addSequential(new MoveElevator(6)); 
    	addParallel(new MoveElevator(1));
    }
}
