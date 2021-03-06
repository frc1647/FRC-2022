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

    private WPI_TalonFX leftClimber;
    private WPI_TalonFX rightClimber;

    private double extendPosition;
    private double retractPosition;
    
    @Override
    protected void initDefaultCommand() {

    }

    public ClimberSubsystem() {
        
        rightClimber = RobotMap.rightClimber;
        leftClimber = RobotMap.leftClimber;
        initMotor(leftClimber);
        initMotor(rightClimber);

        //rightClimber.follow(leftClimber);
        //rightClimber.setInverted(TalonFXInvertType.FollowMaster);

        extendPosition = -170000;
        retractPosition = -62000;
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
        motor.configAllowableClosedloopError(0, 2, 0);
        // NEED TO TUNE THESE
        motor.config_kP(0, 0.9, 0);
        motor.config_kI(0, 0.0, 0);
        motor.config_kD(0, 1.5, 0);
    }
    
    public void stopClimber() {

        leftClimber.set(ControlMode.PercentOutput, 0);
        rightClimber.set(ControlMode.PercentOutput, 0);

    }
    
    public void moveClimber(double percentOutput) {

        leftClimber.set(ControlMode.PercentOutput, percentOutput * -1);
        rightClimber.set(ControlMode.PercentOutput, percentOutput * -1);

    }

    /*public void retractClimber(double percentOutput) {

        leftClimber.set(ControlMode.PercentOutput, percentOutput * -1);
        rightClimber.set(ControlMode.PercentOutput, percentOutput);

    }*/
    
    /** Moves the climber to a set postion.
     * 
     * @param retract If false, the climber extends. If true, the climber retracts.
     */
    public void ClimberAutoStop(boolean retract) {
        //double input = SmartDashboard.getNumber("input", 0);
        //SmartDashboard.putNumber("output", input);

        //leftClimber.set(ControlMode.Position, extendPosition);
        double setPosition = retract ? retractPosition:extendPosition;
        rightClimber.set(ControlMode.Position, (int)setPosition);
        leftClimber.set(ControlMode.Position, (int)setPosition);

        //SmartDashboard.putNumber("right Climber height", rightClimber.getSelectedSensorPosition());
        //SmartDashboard.putNumber("left Climber height", leftClimber.getSelectedSensorPosition());
        //SmartDashboard.putNumber("Target height", setPosition);
    }

    public void resetClimber() {

        leftClimber.set(ControlMode.Position, 0);
        rightClimber.set(ControlMode.Position, 0);

    }

    public double getLeftHeight() {
        return leftClimber.getSelectedSensorPosition();
    }

    public double getRightHeight() {
        return rightClimber.getSelectedSensorPosition();
    }
}
