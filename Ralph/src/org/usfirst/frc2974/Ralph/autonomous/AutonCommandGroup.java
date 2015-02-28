package org.usfirst.frc2974.Ralph.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonCommandGroup extends CommandGroup {
    
    public  AutonCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new GrabAndHold());
    	addParallel(new MoveElevator(10));
    	addSequential(new Wait(2));
    	addSequential(new MoveStraightByTime(5,10));
    	addParallel(new Release());
    	addSequential(new MoveElevator(0)); 	
    	
    }
}
