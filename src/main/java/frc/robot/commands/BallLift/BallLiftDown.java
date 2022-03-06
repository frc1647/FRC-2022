/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.BallLift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallLiftDown extends Command {
  
  /** Lifts the cargo elevator up to unload cargo. */
  public BallLiftDown() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ballLift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.ballLift.LiftMove(0, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Robot.ballLift.stopBallLift();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //Robot.ballLift.stopBallLift();
  }
}
