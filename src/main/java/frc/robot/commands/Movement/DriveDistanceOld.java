// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Movement;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


// THIS COMMAND HAS BEEN REPLACED. DO NOT USE.


public class DriveDistanceOld extends Command {

  private boolean isDone = false;
  private double motorSpeed = 0.5;

  private final double maxRobotSpeed = 24; //inches per second
  private double distance; //inches
  private double velocity; //inches per second
  private double time; //seconds

  public DriveDistanceOld(double inches) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    this.distance = inches;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    velocity = maxRobotSpeed * motorSpeed;
    time = distance/velocity;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.drivetrain.move(motorSpeed, 0, 0);

    try {
      wait(20, 0);
      time -= 0.02;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (time <= 0) {
      isDone = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isDone;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //Robot.drivetrain.stop();
  }
}
