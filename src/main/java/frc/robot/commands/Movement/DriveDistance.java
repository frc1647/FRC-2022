// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Movement;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/** Add your docs here. */
public class DriveDistance extends InstantCommand {
  
  //following two values are temporary
  private final double maxRobotSpeed = 120; //inches per second
  private double motorSpeed = 0.5;

  private double distance; //inches
  private double velocity; //inches per second
  private double time; //seconds
  private Command driveTime;
  
  /** Add your docs here. */
  public DriveDistance(double inches) {
    super();
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    this.distance = inches;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    velocity = maxRobotSpeed * motorSpeed;
    time = distance/velocity;
    
    driveTime = new DriveTime(time, motorSpeed);
    driveTime.start();
  }
}
