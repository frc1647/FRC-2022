/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ResourceBundle.Control;

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
  public WPI_TalonFX BallmotorLeft;
  public WPI_TalonFX BallmotorRight;

  private double tolerance;
  private double goalHeight;
  private double motorSpeed;

  public BallLift() {
    tolerance = 500;
    goalHeight = 0; //temporary value
    motorSpeed = 0.37; //ranges from -1 to 1
    
    BallmotorLeft = RobotMap.BallLiftLeft;
    BallmotorRight = RobotMap.BallLiftRight;

    initMotor(BallmotorLeft);
    initMotor(BallmotorRight);

    //motor2.follow(motor1);
    //motor2.setInverted(TalonFXInvertType.OpposeMaster);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void initMotor(WPI_TalonFX motor) {
    motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 1, 0);
    motor.setSensorPhase(false);
    //motor.configPeakOutputForward(1, 0);
    //motor.configPeakOutputReverse(-1, 0);
    //motor.configNominalOutputForward(0, 0);
    //motor.configNominalOutputReverse(0, 0);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.configAllowableClosedloopError(1, 4, 10);
    // NEED TO TUNE THESE (if we use setPosition2)
    motor.config_kP(1, 5, 0);
    motor.config_kI(1, 0, 0);
    motor.config_kD(1, 0.0, 0);
  }

  public void LiftUp(int leftHeight, int rightHeight) {

    getLeftMotor().set(ControlMode.Position, leftHeight);
    getRightMotor().set(ControlMode.Position, rightHeight);

  }

  public void liftDown(double desiredHeight) {

    getLeftMotor().set(ControlMode.Position, desiredHeight);
    getRightMotor().set(ControlMode.Position, desiredHeight);
  }

  public void stopMotors(WPI_TalonFX motor){
    getLeftMotor().set(ControlMode.PercentOutput, 0);
    getRightMotor().set(ControlMode.PercentOutput, 0);
  }

  
  public double getPostionLeft(){
    return BallmotorLeft.getSelectedSensorPosition();
  }

  public double getPostionRight(){
    return BallmotorRight.getSelectedSensorPosition();
  }

  public WPI_TalonFX getLeftMotor() {
    return BallmotorLeft;
  }

  public WPI_TalonFX getRightMotor() {
    return BallmotorRight;
  }

}
