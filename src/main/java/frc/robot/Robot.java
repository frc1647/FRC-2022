// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SensorUtil;
import edu.wpi.first.wpilibj2.command.Subsystem;
//import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
//import frc.robot.subsystems.Swerve.Swerve;
//import frc.robot.subsystems.Swerve.SwerveDrivetrain;
//import frc.robot.subsystems.Swerve.SwerveMath;
//import frc.robot.subsystems.Swerve.*;
import frc.robot.subsystems.BallLift;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NewSwerve.Swerve;
import frc.robot.OI;
import frc.robot.RobotMap;

import frc.robot.commands.*;
import frc.robot.commands.Movement.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static String mode;
  public static Swerve SwerveDrive = new Swerve();
  //public static Timer m_timer = new Timer();
  public static ClimberSubsystem ClimberSubsystem = new ClimberSubsystem();
  public static BallLift ballLift = new BallLift();
  public static IntakeSubsystem IntakeSubsystem = new IntakeSubsystem();
  public static UsbCamera usbCam;

  //public static frc.robot.subsystems.NewSwerve.Swerve SwerveDrive = new frc.robot.subsystems.NewSwerve.Swerve();
  
  public static OI oi;
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  /*
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  */

  public static double zeroHeading;
  public static double zeroAngle;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    //ONLY RESET ENCODERS WHEN ALIGNING SWERVE
    //drivetrain.resetDriveEnc();
    zeroHeading = RobotMap.navx.getFusedHeading();
    zeroAngle = RobotMap.navx.getAngle();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //update smartdashboard values from NewSwerve here
    SmartDashboard.putNumber("front left pos", SwerveDrive.getFrontLeftModule().getCurrentPos());
    SmartDashboard.putNumber("front right pos", SwerveDrive.getFrontRightModule().getCurrentPos());
    SmartDashboard.putNumber("rear left pos", SwerveDrive.getRearLeftModule().getCurrentPos());
    SmartDashboard.putNumber("rear right pos", SwerveDrive.getRearRightModule().getCurrentPos());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_chooser.getSelected();
    m_autonomousCommand = new Auto2();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    //m_timer.start();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    usbCam = CameraServer.startAutomaticCapture();
    //SwerveDrive.getFrontRightModule().move3(0, 0);
    //SwerveDrive.getFrontLeftModule().move3(0, 0);
    //SwerveDrive.getRearLeftModule().move3(0, 0);
    //SwerveDrive.getRearRightModule().move3(0, 0);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    //Robot.drivetrain.stop();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {

  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
