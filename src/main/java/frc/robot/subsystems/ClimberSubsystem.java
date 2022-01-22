package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberSubsystem extends Subsystem{

    private final BaseMotorController leftClimber = RobotMap.leftClimber;
    private final BaseMotorController rightClimber = RobotMap.rightClimber;

    //Stop Values
    final double extendPosition = 0;
    final double retractPosition = 0;
    
    @Override
    protected void initDefaultCommand() {

    }

    public ClimberSubsystem() {

    }
    
    public void stopClimber() {

        leftClimber.set(ControlMode.PercentOutput, 0);
        rightClimber.set(ControlMode.PercentOutput, 0);

    }

    public void extendClimber() {

        leftClimber.set(ControlMode.PercentOutput, 0.5);
        rightClimber.set(ControlMode.PercentOutput, 0.5);

    }

    public void retractClimber() {

        leftClimber.set(ControlMode.PercentOutput, -0.25);
        rightClimber.set(ControlMode.PercentOutput, -0.25);

    }

    public void extendClimberAutoStop() {

        leftClimber.set(ControlMode.Position, extendPosition);
        rightClimber.set(ControlMode.Position, extendPosition);

    }

    public void retractClimberAutoStop() {

        leftClimber.set(ControlMode.Position, retractPosition);
        rightClimber.set(ControlMode.Position, retractPosition);

    }

    public void resetClimber() {

        leftClimber.set(ControlMode.Position, 0);
        rightClimber.set(ControlMode.Position, 0);

    }
}
