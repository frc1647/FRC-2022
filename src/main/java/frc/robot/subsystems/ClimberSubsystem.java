package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberSubsystem extends Subsystem{

    //private WPI_TalonFX leftClimber;
    private WPI_TalonFX rightClimber;

    private double extendPosition;
    private double retractPosition;
    
    @Override
    protected void initDefaultCommand() {

    }

    public ClimberSubsystem() {
        
        rightClimber = RobotMap.rightClimber;
        rightClimber.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 2, 0);
        rightClimber.setSensorPhase(false);
        rightClimber.setNeutralMode(NeutralMode.Brake);

        //leftClimber = RobotMap.leftClimber;
        //leftClimber.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        //leftClimber.setSensorPhase(true);
        //leftClimber.setNeutralMode(NeutralMode.Brake);
        
        //initMotor(leftClimber);
        initMotor(rightClimber);

        //rightClimber.follow(leftClimber);
        rightClimber.setInverted(TalonFXInvertType.FollowMaster);

        extendPosition = 69;
        retractPosition = 0;
    }

    public void initMotor(WPI_TalonFX motor) {
        //motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        //motor.setSensorPhase(false);
        //motor.configPeakOutputForward(1, 0);
        //motor.configPeakOutputReverse(-1, 0);
        //motor.configNominalOutputForward(0, 0);
        //motor.configNominalOutputReverse(0, 0);
        //motor.setNeutralMode(NeutralMode.Brake);
        motor.configAllowableClosedloopError(2, 4, 10);
        // NEED TO TUNE THESE
        motor.config_kP(2, 6.9, 0);
        motor.config_kI(2, 0, 0);
        motor.config_kD(2, 0.03, 0);
    }
    
    public void stopClimber() {

        //leftClimber.set(ControlMode.PercentOutput, 0);
        rightClimber.set(ControlMode.PercentOutput, 0);

    }

    public void extendClimber() {

        //leftClimber.set(ControlMode.PercentOutput, 0.5);
        rightClimber.set(ControlMode.PercentOutput, 0.5);

    }

    public void retractClimber() {

        //leftClimber.set(ControlMode.PercentOutput, -0.25);
        rightClimber.set(ControlMode.PercentOutput, -0.25);

    }

    public void extendClimberAutoStop() {

        //leftClimber.set(ControlMode.Position, extendPosition);
        rightClimber.set(ControlMode.Position, extendPosition);

    }

    public void retractClimberAutoStop() {

        //leftClimber.set(ControlMode.Position, retractPosition);
        rightClimber.set(ControlMode.Position, retractPosition);

    }

    public void resetClimber() {

        //leftClimber.set(ControlMode.Position, 0);
        rightClimber.set(ControlMode.Position, 0);

    }
}
