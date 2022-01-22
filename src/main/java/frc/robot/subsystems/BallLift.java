/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class BallLift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonFX motor1;
  public WPI_TalonFX motor2;

  private double tolerance;
  private double goalHeight;
  private double motorSpeed;

  public BallLift() {
    tolerance = 250;
    goalHeight = 0;
    motorSpeed = 0.75; //ranges from -1 to 1
    
    motor1 = RobotMap.BallLift1;
    motor1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    motor2 = RobotMap.BallLift2;
    motor2.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setPosition(double desiredHeight) {
    goalHeight = desiredHeight;
    if (desiredHeight - getPostion1() < 0){
      motor1.set(-motorSpeed);
      //motor2.set(-1);
    } else {
      motor1.set(motorSpeed);
      //motor2.set(1);
    }
    motor2.follow(motor1);
    delta();
  }

  public void setPosition2(double desiredHeight){ //not used in the commands, but might would be a better implementation if it works
    goalHeight = desiredHeight;
    motor1.set(ControlMode.Position, goalHeight);
    motor2.follow(motor1);
  }

  public boolean delta(){
    if (getPostion1() - goalHeight < tolerance){
      return true;
    } else {
      return false;
    }
  }

  public void stopBallLift(){
    motor1.set(ControlMode.PercentOutput, 0);
    motor2.set(ControlMode.PercentOutput, 0);
  }
  
  public double getPostion1(){
    return motor1.getSelectedSensorPosition(0);
  }

  public double getPostion2(){
    return motor2.getSelectedSensorPosition(0);
  }

}
