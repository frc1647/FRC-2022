// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.NewSwerve;


/** Add your docs here. */
public class CentricToggle {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private boolean centricToggle = false;

  public void toggle() {
    if (centricToggle) {
      centricToggle = false;
    } else {
      centricToggle = true;
    }
  }

  public boolean get() {
    return centricToggle;
  }
}
