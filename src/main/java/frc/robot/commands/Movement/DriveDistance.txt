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
  private int invert;

  private double distance; //inches
  private double velocity; //inches per second
  private double time; //seconds
  private Command driveTime;
  
  
  /** Makes the robot drive a specified distance.
   * 
   * @param inches The distance the robot should drive in inches.
   * @param invert Setting to true makes the robot drive backward. Setting to false makes the robot drive forward.
   */
  public DriveDistance(double inches, boolean invert) {
    super();
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    this.distance = Math.abs(inches);
    
    this.invert = invert ? -1:1;
    
    /*if (invert) {
      this.invert = -1;
    } else {
      this.invert = 1;
    }*/
  }

  /** Makes the robot drive a specified distance.
   * 
   * param inches The distance the robot should drive forward in inches.
   *
  public DriveDistance(double inches) {
    super();
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    this.distance = Math.abs(inches);
    this.invert = 1;
  }*/

  // Called once when the command executes
  @Override
  protected void initialize() {
    velocity = maxRobotSpeed * motorSpeed;
    time = distance/velocity;
    
    driveTime = new DriveTime(time, motorSpeed * invert);
    driveTime.start();
  }
}
