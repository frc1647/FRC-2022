// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.commands.BallLift.*;
import frc.robot.commands.Climber.*;
import frc.robot.subsystems.ClimberSubsystem;

/** Add your docs here. */
public class StopMechanisims extends InstantCommand {
  /** Add your docs here. */
  public StopMechanisims() {
    super();
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ClimberSubsystem);
    requires(Robot.ballLift);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.ClimberSubsystem.stopClimber();
    Robot.ballLift.stopLeftLift();
    Robot.ballLift.stopRightLift();
  }
}
