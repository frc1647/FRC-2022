// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Movement;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/** Add your docs here. */
public class DriveDistance extends InstantCommand {
  
  private final double maxRobotSpeed = 85; //inches per second
  private double motorSpeed = 0.5;

  private double xDist;
  private double yDist;
  private double velocity; //inches per second
  private double time; //seconds
  private final double spinCorr = 0.235;//constant to account for the time spent rotating swerve modules
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
    double xDistAbs = Math.abs(xDist);
    double yDistAbs = Math.abs(yDist);
    velocity = maxRobotSpeed * motorSpeed;
    time = Math.hypot(xDistAbs, yDistAbs) / velocity;

    double ratio;
    if (xDistAbs > yDistAbs) {

      ratio = (yDist / xDistAbs);
      time += (ratio / 2) * spinCorr;
      driveTime = new DriveTime(time, motorSpeed * Math.signum(xDist), ratio * motorSpeed, 0);

    } else if (yDistAbs > xDistAbs) {

      ratio = xDist / yDistAbs;
      time += (1 - (ratio / 2)) * spinCorr;
      driveTime = new DriveTime(time, ratio * motorSpeed, motorSpeed * Math.signum(yDist), 0);

    } else {

      time += 0.5 * spinCorr;
      driveTime = new DriveTime(time, motorSpeed * Math.signum(xDist), motorSpeed * Math.signum(yDist), 0);
      
    }
    driveTime.start();
  }
}
