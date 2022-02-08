/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

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
    goalHeight = 0; //temporary value
    motorSpeed = 0.75; //ranges from -1 to 1
    
    motor1 = RobotMap.BallLift1;
    motor2 = RobotMap.BallLift2;

    initMotor(motor1);
    initMotor(motor2);

    //motor2.follow(motor1);
    motor2.setInverted(TalonFXInvertType.OpposeMaster);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void initMotor(WPI_TalonFX motor) {
    motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    motor.setSensorPhase(false);
    //motor.configPeakOutputForward(1, 0);
    //motor.configPeakOutputReverse(-1, 0);
    //motor.configNominalOutputForward(0, 0);
    //motor.configNominalOutputReverse(0, 0);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.configAllowableClosedloopError(1, 4, 10);
    // NEED TO TUNE THESE (if we use setPosition2)
    motor.config_kP(1, 6.9, 0);
    motor.config_kI(1, 0, 0);
    motor.config_kD(1, 0.03, 0);
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

  public void setPosition2(double desiredHeight) { //not used in the commands, but would be a better implementation if it works
    goalHeight = desiredHeight;
    motor1.set(ControlMode.Position, goalHeight);
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
