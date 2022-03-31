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
  private final double maxRobotSpeed = 85; //inches per second
  private double motorSpeed = 0.5;

  private double xDist;
  private double yDist;
  private double velocity; //inches per second
  private double time; //seconds
  private Command driveTime;
  
  
  /** Makes the robot drive a specified distance.
   * 
   * @param xInches The number of inches forward the robot should drive. Negative values will make the robot drive backward.
   * @param yInches The number of inches right the robot should drive. Negative values will make the robot drive left.
   * 
   */
  public DriveDistance(double xInches, double yInches) {
    super();
    // Use requires() here to declare subsystem dependencies
    requires(Robot.SwerveDrive);
    this.xDist = xInches;
    this.yDist = yInches;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    velocity = maxRobotSpeed * motorSpeed;
    time = Math.abs(Math.hypot(xDist, yDist)) / velocity;

    double ratio;
    if (Math.abs(yDist) < Math.abs(xDist)) {

      ratio = (yDist / Math.abs(xDist));
      driveTime = new DriveTime(time, xDist * motorSpeed, ratio * motorSpeed, 0);

    } else if (Math.abs(yDist) > Math.abs(xDist)) {
      
      ratio = xDist / Math.abs(yDist);
      driveTime = new DriveTime(time, ratio * motorSpeed, yDist * motorSpeed, 0);

    }
    driveTime.start();
  }
}
