// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.BallLift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.BallLift;

public class RightConstant extends Command {

  int invert = 1;
  final double liftSpeed = 0.25; //motor speed as percent of max output

  public RightConstant(boolean down) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ballLift);
    invert = down ? -1:1;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.ballLift.rightConst(liftSpeed * invert);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.ballLift.stopRightLift();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  /*
  @Override
  protected void interrupted() {
    //end();
  }*/
}
