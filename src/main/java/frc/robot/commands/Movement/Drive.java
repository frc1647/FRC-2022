/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.Movement;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Swerve.*;
import frc.robot.OI;

// Motor control imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Drive extends Command {

  private double fwd;
  private double str;
  private double rcw;

  public Drive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    fwd = -Robot.oi.getLeftJoy().getY(); // - or + ?
    str = Robot.oi.getLeftJoy().getX(); // was left joy
    rcw = Robot.oi.getRightJoy().getX(); // was right joy

    //joystick deadzone

    if (fwd >= -0.1 && fwd <= 0.1){
      fwd = 0;
    }

    if (str >= -0.1 && str <= 0.1){
      str = 0;
    }

    if (rcw >= -0.1 && rcw <= 0.1){
      rcw = 0;
    }

    //makes joysticcs values a parabola, while maintaining negative values
    fwd *= fwd * Math.signum(fwd);
    str *= str * Math.signum(str);
    rcw *= rcw * Math.signum(rcw) * 0.5;

    //TRY WITH CUBE TOMORROW //update: we didn't  

    //Robot.drivetrain.move(0, 0, 0);
    Robot.drivetrain.move(fwd, str, rcw);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
