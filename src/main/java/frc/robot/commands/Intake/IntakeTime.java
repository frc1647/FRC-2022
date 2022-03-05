// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/** Add your docs here. */
public class IntakeTime extends TimedCommand {
  
  /** Spins the intake for a specified amount of time.
   * 
   * @param timeout The length of time the intake should spin for in seconds.
   */
  public IntakeTime(double timeout) {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    requires(Robot.IntakeSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.IntakeSubsystem.spinIntake(1);
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.IntakeSubsystem.stopIntakeSpin();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.IntakeSubsystem.stopIntakeSpin();
  }
}
