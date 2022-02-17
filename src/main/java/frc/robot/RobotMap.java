/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // Swerve Motor Controllers 
  public static WPI_TalonSRX FRTalonD = new WPI_TalonSRX(10);
  public static WPI_TalonSRX FRTalonS = new WPI_TalonSRX(2);
  public static WPI_TalonSRX FLTalonD = new WPI_TalonSRX(7);
  public static WPI_TalonSRX FLTalonS = new WPI_TalonSRX(3);
  public static WPI_TalonSRX RLTalonD = new WPI_TalonSRX(5);
  public static WPI_TalonSRX RLTalonS = new WPI_TalonSRX(6);
  public static WPI_TalonSRX RRTalonD = new WPI_TalonSRX(9);
  public static WPI_TalonSRX RRTalonS = new WPI_TalonSRX(1);

  // Joysticks
  public static Joystick leftJoy = new Joystick(2);
  public static Joystick rightJoy = new Joystick(1);
  public static Joystick tablet = new Joystick(0);

  // Ball Lift
  public static WPI_TalonFX BallLiftLeft = new WPI_TalonFX(6);
  public static WPI_TalonFX BallLiftRight = new WPI_TalonFX(4);

  // Others
  public static Encoder leftClimberEncoder = new Encoder(0, 1);
  public static Encoder driveEncoder = new Encoder(8 , 9);
  public static AHRS navx = new AHRS(SPI.Port.kMXP);

  // Climber
  public static WPI_TalonFX leftClimber = new WPI_TalonFX(11); //Falcon 500
  public static WPI_TalonFX rightClimber = new WPI_TalonFX(12); //Falcon 500

  // Intake
  public static WPI_TalonFX intakePosition = new WPI_TalonFX(13);
  public static WPI_TalonSRX intakeSpin = new WPI_TalonSRX(14);
}
