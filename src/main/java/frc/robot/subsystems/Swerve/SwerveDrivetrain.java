/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.Swerve;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Movement.Drive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Swerve.SwerveMath;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class SwerveDrivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static BaseMotorController frDrive = RobotMap.FRTalonD; //Front right drive
  public static BaseMotorController flDrive = RobotMap.FLTalonD; //front left drive
  public static BaseMotorController rrDrive = RobotMap.RRTalonD; //rear right Drive
  public static BaseMotorController rlDrive = RobotMap.RLTalonD; //rear left drive
  public static WPI_TalonSRX frSteer = RobotMap.FRTalonS; //front right steer
  public static WPI_TalonSRX flSteer = RobotMap.FLTalonS; //front left steer
  public static WPI_TalonSRX rrSteer = RobotMap.RRTalonS; //rear right steer
  public static WPI_TalonSRX rlSteer = RobotMap.RLTalonS; //rear left steer

  private final double width = 22; //was 9.5 //NEW: was 10.5
  private final double length = 21; //was 8.5 //NEW: was 9.25
  private final double gearRatio = 59.16666666;

  public Swerve swerveDrivetrain;

  private SwerveModule frontRight;
  private SwerveModule frontLeft;
  private SwerveModule rearRight;
  private SwerveModule rearLeft;
  
  /*
  private Encoder analogFrontRight = RobotMap.frontRight; // changed encoder from DigitalInput
  private Encoder analogFrontLeft = RobotMap.frontLeft;
  private Encoder analogRearRight = RobotMap.rearRight;
  private Encoder analogRearLeft = RobotMap.rearLeft;
  */
  private static Encoder enc = RobotMap.driveEncoder;
  private static AHRS gyro = RobotMap.navx;

  private final double P = 6.9;
  private final double I = 0; //dont touch
  private final double D = 0.03;

  public SwerveDrivetrain(){
    frontLeft = new SwerveModule("Front Left Wheel", flDrive, flSteer, gearRatio);
    frontRight = new SwerveModule("Front Right Wheel", frDrive, frSteer, gearRatio);
    rearLeft = new SwerveModule("Rear Left Wheel", rlDrive, rlSteer, gearRatio);
    rearRight = new SwerveModule("Rear Right Wheel", rrDrive, rrSteer, gearRatio);
    init();

    swerveDrivetrain = new Swerve(frontRight, frontLeft, rearRight, rearLeft, width, length);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }

  public void init(){
    //set gyro to 0;
    
    initSteerMotor(frSteer);
    initSteerMotor(flSteer);
    initSteerMotor(rrSteer);
    initSteerMotor(rlSteer);
    resetPosition();
    //resetDriveEnc();
  }

  public void initSteerMotor(WPI_TalonSRX steerMotor){
    steerMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 10);
    steerMotor.setSensorPhase(false);

    steerMotor.configPeakOutputForward(1, 10);
    steerMotor.configPeakOutputReverse(-1, 10);

    steerMotor.configNominalOutputForward(0, 10);
    steerMotor.configNominalOutputReverse(0, 10);

    frontLeft.setReverseEncoder(true);
    frontRight.setReverseEncoder(false);
    rearLeft.setReverseEncoder(true);
    rearRight.setReverseEncoder(false);
    
    frontRight.setReverseSteerMotor(true);
    frontLeft.setReverseSteerMotor(true);
    rearLeft.setReverseSteerMotor(true);
    rearRight.setReverseSteerMotor(true);

    steerMotor.setNeutralMode(NeutralMode.Brake);

    steerMotor.configAllowableClosedloopError(0, 4, 10);

    steerMotor.config_kP(0, P, 10);
    steerMotor.config_kI(0, I, 10);
    steerMotor.config_kD(0, D, 10);
  }

  public void resetDriveEnc(){
    enc.reset(); //maybe change
  }

  public void resetPosition(){
    frSteer.set(ControlMode.Position, 0);
    flSteer.set(ControlMode.Position, 0);
    rrSteer.set(ControlMode.Position, 0);
    rlSteer.set(ControlMode.Position, 0);
  }

  public double getDistance(){
    return enc.getDistance(); // maybe change
  }

  public double getGyro(){
    return gyro.getAngle();
  }

  public void move(double fwd, double str, double rcw){
    swerveDrivetrain.move(fwd, str, rcw, getGyro());
  }

  public void stop(){
    swerveDrivetrain.stop();
  }
  
  public void toggleMode(){
    Robot.swerveMath.toggleCentricMode();
  }
	
  public void setModeRobot() {
    Robot.swerveMath.setModeRobot();
  }
	
  public CentricMode getModeRobot() {
    return Robot.swerveMath.getCentricMode();
  }
	
  public void setModeField() {
    Robot.swerveMath.setModeField();
  }
  
  public double getWidth() {
    return width;
  }

  public double getLength() {
    return length;
  }

}
