package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        rightClimber.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        rightClimber.setSensorPhase(false);
        rightClimber.setNeutralMode(NeutralMode.Brake);

        //leftClimber = RobotMap.leftClimber;
        //leftClimber.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        //leftClimber.setSensorPhase(true);
        //leftClimber.setNeutralMode(NeutralMode.Brake);
        
        //initMotor(leftClimber);
        initMotor(rightClimber);

        //rightClimber.follow(leftClimber);
        //rightClimber.setInverted(TalonFXInvertType.FollowMaster);

        extendPosition = -344500;
        retractPosition = -150000;
    }

    public void initMotor(WPI_TalonFX motor) {
        motor.configFactoryDefault();
        motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        motor.setSensorPhase(false);
        //motor.configPeakOutputForward(1, 0);
        //motor.configPeakOutputReverse(-1, 0);
        //motor.configNominalOutputForward(0, 0);
        //motor.configNominalOutputReverse(0, 0);
        motor.setNeutralMode(NeutralMode.Brake);
        motor.configAllowableClosedloopError(0, 10, 0);
        motor.configMaxIntegralAccumulator(0, 100);
        // NEED TO TUNE THESE
        motor.config_kP(0, 1.5, 0);
        motor.config_kI(0, 0.0, 0);
        motor.config_kD(0, 1.0, 0);
    }
    
    public void stopClimber() {

        //leftClimber.set(ControlMode.PercentOutput, 0);
        rightClimber.set(ControlMode.PercentOutput, 0);

    }
    
    public void extendClimber(double percentOutput) {

        //leftClimber.set(ControlMode.PercentOutput, 0.5);
        rightClimber.set(ControlMode.PercentOutput, percentOutput);

    }

    public void retractClimber(double percentOutput) {

        //leftClimber.set(ControlMode.PercentOutput, -0.25);
        rightClimber.set(ControlMode.PercentOutput, percentOutput);

    }
    
    /** Moves the climber to a set postion.
     * 
     * @param retract If false, the climber extends. If true, the climber retracts.
     */
    public void ClimberAutoStop(boolean retract) {
        //double input = SmartDashboard.getNumber("input", 0);
        //SmartDashboard.putNumber("output", input);

        //rightClimber.config_kP(2, SmartDashboard.getNumber("climb kP", 0), 0);
        //rightClimber.config_kI(2, SmartDashboard.getNumber("climb kI", 0), 0);
        //rightClimber.config_kD(2, SmartDashboard.getNumber("climb kD", 0), 0);

        double setPosition = retract ? retractPosition:extendPosition;
        rightClimber.set(ControlMode.Position, (int)setPosition);

        SmartDashboard.putNumber("Climber height", rightClimber.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Target height", setPosition);
    }

    public void resetClimber() {

        //leftClimber.set(ControlMode.Position, 0);
        rightClimber.set(ControlMode.Position, 0);

    }
}
