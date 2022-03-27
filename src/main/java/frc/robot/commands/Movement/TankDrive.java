// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Movement;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TankDrive extends Command {

  public static final double deadzone = 0.1;

  public TankDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.SwerveDrive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftSpeed =0; //remove the =0
    double rightSpeed =0; //remove the =0

    double forward = -Robot.oi.getRightJoy().getY();
    double turn = Robot.oi.getRightJoy().getX();

    //put one-joystick tank controls from test code here

    Robot.SwerveDrive.tankSwerve(leftSpeed, rightSpeed);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
