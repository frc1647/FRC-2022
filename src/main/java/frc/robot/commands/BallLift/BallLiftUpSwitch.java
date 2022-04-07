// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.BallLift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class BallLiftUpSwitch extends CommandGroup {
  
  private Command left;
  private Command right;
  private final double boxSpeed = 0.6;
  
  /** Add your docs here. */
  public BallLiftUpSwitch() {
    requires(Robot.ballLift);
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

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
    left = new LeftBallLiftUp(boxSpeed);
    right = new RightBallLiftUp(boxSpeed);

    addParallel(left);
    addSequential(right);
  }

  public BallLiftUpSwitch(double speed) {
    requires(Robot.ballLift);

    left = new LeftBallLiftUp(speed);
    right = new RightBallLiftUp(speed);

    addParallel(left);
    addSequential(right);
  }

  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected void end() {
    cancel();
  }
}
