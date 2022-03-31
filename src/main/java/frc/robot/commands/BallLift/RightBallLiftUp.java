// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.BallLift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class RightBallLiftUp extends Command {
  
  private double speed;
  
  public RightBallLiftUp(double speed) {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.ballLift);
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.ballLift.rightConst(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return RobotMap.rightBoxSwitch.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.ballLift.stopRightLift();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
