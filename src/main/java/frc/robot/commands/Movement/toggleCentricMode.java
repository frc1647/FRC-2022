// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Movement;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/** Add your docs here. */
public class toggleCentricMode extends InstantCommand {
  /** Add your docs here. */
  public toggleCentricMode() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.centricToggle.toggle();
  }
}
