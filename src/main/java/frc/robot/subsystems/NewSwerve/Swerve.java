// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.NewSwerve;

import java.util.Arrays;
import java.util.Collections;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Movement.NewDrive;

/** Add your docs here. */
public class Swerve extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private BaseMotorController frDrive = RobotMap.FRTalonD;
    private BaseMotorController flDrive = RobotMap.FLTalonD;
    private BaseMotorController rrDrive = RobotMap.RRTalonD;
    private BaseMotorController rlDrive = RobotMap.RLTalonD;
    // BaseMotorController will be replaced with spark max because of integrated encoder on Neos

    private WPI_TalonSRX frSteer = RobotMap.FRTalonS;
    private WPI_TalonSRX flSteer = RobotMap.FLTalonS;
    private WPI_TalonSRX rrSteer = RobotMap.RRTalonS;
    private WPI_TalonSRX rlSteer = RobotMap.RLTalonS;

    private final double width = 22;
    private final double length = 21;
    private final double maxSpeed = 120; // inches per second
    private final double encoderTicsPerRotation = 1024;

    // ADJUST PIDS HERE
    private final double P = 0.0;
    private final double I = 0.0;
    private final double D = 0.0;

    private SwerveModule frontRight;
    private SwerveModule frontLeft;
    private SwerveModule rearRight;
    private SwerveModule rearLeft;

    public Swerve() {
        init();
        frontRight = new SwerveModule(frDrive, frSteer);
        frontLeft = new SwerveModule(flDrive, flSteer);
        rearRight = new SwerveModule(rrDrive, rrSteer);
        rearLeft = new SwerveModule(rlDrive, rlSteer);
    }

    // ADJUST SWERVE MODULE SETTINGS HERE
    private void init() {
        initDriveMotor(frDrive, false);
        initDriveMotor(flDrive, false);
        initDriveMotor(rrDrive, false);
        initDriveMotor(rlDrive, false);

        initSteerMotor(frSteer, true, true);
        initSteerMotor(flSteer, true, true);
        initSteerMotor(rrSteer, true, true);
        initSteerMotor(rlSteer, true, true);
    }

    private void initDriveMotor(BaseMotorController motor, boolean invert) {
        motor.configFactoryDefault();
        motor.setInverted(invert);
        motor.setNeutralMode(NeutralMode.Brake);
    }

    private void initSteerMotor(WPI_TalonSRX motor, boolean invertDrive, boolean invertEncoder) {
        motor.configFactoryDefault();
        motor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
        motor.setSensorPhase(invertEncoder);
        motor.setInverted(invertDrive);
        motor.setNeutralMode(NeutralMode.Brake);

        motor.config_kP(0, P, 0);
        motor.config_kI(0, I, 0);
        motor.config_kD(0, D, 0);

        motor.config_IntegralZone(0, 100, 0);
        motor.configAllowableClosedloopError(0, 5, 0);
        motor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 0);
    }

    public void SwerveDrive(double strafe, double forward, double omega) {
        double diagonal = Math.hypot(length, width); //value should be identical to the one produced by our old code
        //double diagonal = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2)); //value in our old code
        //double diagonal = 2.0; //value in cybersonics' code
        double omegaLD = omega * (length / diagonal);
        double omegaWD = omega * (width / diagonal);

        // Compute the constants used later for calculating speeds and angles
        double A = strafe - omegaLD;
        double B = strafe + omegaLD;
        double C = forward - omegaWD;
        double D = forward + omegaWD;

        // Compute the drive motor speeds
        double frSpeed = Math.hypot(B, C);
        double flSpeed = Math.hypot(B, D);
        double rrSpeed = Math.hypot(A, C);
        double rlSpeed = Math.hypot(A, D);

        // Compute angles for the steering motors
        // When drives are calibrated for zero position on encoders they can be at 90 degrees (or maybe some other angle) to the front of the robot.
        // Subtract and add 90 degrees to steering calculation to offset for initial position/calibration of drives if the drive zero position faces the side of the robot.
        double frAngle = Math.toDegrees(Math.atan2(B, C)) + 90;
        double flAngle = Math.toDegrees(Math.atan2(B, D)) + 90;
        double rrAngle = Math.toDegrees(Math.atan2(A, C)) - 90;
        double rlAngle = Math.toDegrees(Math.atan2(A, D)) - 90;
        // ABOVE ANGLE OFFSETS ARE TEMPORARY

        double maxSpeed = Collections.max(Arrays.asList(frSpeed, flSpeed, rrSpeed, rlSpeed, 1.0));
        frSpeed /= maxSpeed;
        flSpeed /= maxSpeed;
        rrSpeed /= maxSpeed;
        rlSpeed /= maxSpeed;

        frontRight.move(frSpeed, frAngle);
        frontLeft.move(flSpeed, flAngle);
        rearRight.move(rrSpeed, rrAngle);
        rearLeft.move(rlSpeed, rlAngle);
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getEncoderTicsPerRotation() {
        return encoderTicsPerRotation;
    }

    public SwerveModule getFrontRightModule() {
        return frontRight;
    }

    public SwerveModule getFrontLeftModule() {
        return frontLeft;
    }

    public SwerveModule getRearRightModule() {
        return rearRight;
    }

    public SwerveModule getRearLeftModule() {
        return rearLeft;
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new NewDrive());
    }

}
