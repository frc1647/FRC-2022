// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Movement;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/** Add your docs here. */
public class DriveTime extends TimedCommand {
  
  double motorSpeed;
  double fwd;
  double str;
  double rcw;
  
  /** drives the robot forward for a specified length of time at a specified multiplier of its maximum velocity.
   * 
   * @param timeout the amount of time the robot should drive for in seconds.
   * @param multiplier how fast the robot should drive as a multiplier of its maximum velocity.
   */
  public DriveTime(double timeout, double fwd, double str, double rcw) {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    requires(Robot.SwerveDrive);
    this.fwd = fwd;
    this.str = str;
    this.rcw = rcw;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.SwerveDrive.SwerveDrive(str, fwd, rcw);
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.SwerveDrive.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //Robot.drivetrain.stop();
  }
}
