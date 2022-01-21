/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Movement;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Swerve.SwerveMath;
import frc.robot.subsystems.Swerve.CentricMode;
import frc.robot.OI;

/**
 * An example command.  You can replace me with your own command.
 */
public class SwapCentricMode extends Command {

  public SwapCentricMode() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //method 1
    //Robot.drivetrain.toggleMode();

    //4048 method
    CentricMode mode = Robot.drivetrain.getModeRobot();
    if (mode == CentricMode.Robot){
      Robot.drivetrain.setModeField();
    } else {
      Robot.drivetrain.setModeRobot();
    }
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
