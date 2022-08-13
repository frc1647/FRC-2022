// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.BallLift.*;
import frc.robot.commands.Movement.DriveDistance;
import frc.robot.commands.Movement.DriveTime;

public class Auto3 extends CommandGroup {
  /** This is a more complex auto where the robot starts right in front of the low goal, deposits a ball, and drives out of the start area. */
  public Auto3() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    addSequential(new BallLiftUpSwitchTimeout(), 3.0);
    //addParallel(new DriveTime(2.0, 0.6, 0, 0));
    addSequential(new DriveDistance(88, -42));
    //addSequential(new BallLiftDown()); // was disabled to avoid problems with lowering the mailbox in the absence of operator intervention during auto

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
