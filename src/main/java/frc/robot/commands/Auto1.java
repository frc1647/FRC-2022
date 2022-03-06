// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Intake.SpinIntake;
import frc.robot.commands.Movement.DriveDistance;

public class Auto1 extends CommandGroup {
  
  private double forwardDistance = 40;
  private double reverseDistance = 30;
  
  /** Autonomus sequence 1. */
  public Auto1() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.
    

    //addSequential(new DriveDistance(forwardDistance, false));
    addSequential(new SpinIntake(1), 4); //works?
    //addSequential(new BallLiftUp()); //BallLiftUp command currently uses non PID method
    addSequential(new DriveDistance(reverseDistance, true));
    addSequential(new DriveDistance(reverseDistance, false));
    //addSequential(new IntakeTime(20));
    

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
