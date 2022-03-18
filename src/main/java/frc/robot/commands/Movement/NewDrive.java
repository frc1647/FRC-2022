// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Movement;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class NewDrive extends Command {

  public static final double deadzone = 0.1;
  public static final double rcwScale = 0.5;

  private double originHeading = 0.0;
  private double originCorr = 0;
  private double strfwdPow = 2.0;
  private double rcwPow = 2.0;

  private double fwd;
  private double str;
  private double rcw;

  public NewDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.SwerveDrive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    originHeading = Robot.zeroHeading;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*if (Robot.oi.getLeftJoyButton(7)) {
		originHeading = RobotMap.navX.getFusedHeading();
	}*/

	double originOffset = 360 - originHeading;
	originCorr = RobotMap.navx.getFusedHeading() + originOffset;

    fwd = -Robot.oi.getRightJoy().getY(); // - or + ?
    str = Robot.oi.getRightJoy().getX(); // was left joy
    rcw = Robot.oi.getLeftJoy().getX(); // was right joy
    
    if (Math.abs(str) < deadzone) {
      str = 0.0;
    }
	if (Math.abs(fwd) < deadzone) {
      fwd = 0.0;
    }
	if (Math.abs(rcw) < deadzone) {
      rcw = 0.0;
    }

    //makes joysticcs values a parabola, while maintaining negative values
    fwd = Math.abs(Math.pow(fwd, strfwdPow)) * Math.signum(fwd);
    str = Math.abs(Math.pow(str, strfwdPow)) * Math.signum(str);
    rcw = Math.abs(Math.pow(rcw, rcwPow)) * Math.signum(rcw) * rcwScale;

    /* //commented out until we get a working navx
    if (!Robot.oi.getLeftJoy().getTrigger()) {
      // When the Left Joystick trigger is not pressed, The robot is in Field Centric Mode.
      // The calculations correct the forward and strafe values for field centric attitude. 
    
      // Rotate the velocity vector from the joystick by the difference between our
      // current orientation and the current origin heading
      double originCorrection = Math.toRadians(originHeading - RobotMap.navx.getFusedHeading());
      double temp = fwd * Math.cos(originCorrection) - str * Math.sin(originCorrection);
      str = str * Math.cos(originCorrection) + fwd * Math.sin(originCorrection);
      fwd = temp;
    }*/

    Robot.SwerveDrive.SwerveDrive(str, fwd, rcw);

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
