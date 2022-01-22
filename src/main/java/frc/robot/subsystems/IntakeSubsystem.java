package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class IntakeSubsystem extends Subsystem {

    private final BaseMotorController intakePosition = RobotMap.intakePosition;
    private final BaseMotorController intakeSpin = RobotMap.intakeSpin;

    //Stop Values
    final double extendPosition = 0;
    final double retractPosition = 0;

    @Override
    protected void initDefaultCommand() {
   
    }

    public IntakeSubsystem() {

    }

    public void extendIntake() {

        intakePosition.set(ControlMode.PercentOutput, 0.25);

    }

    public void retractIntake() {

        intakePosition.set(ControlMode.PercentOutput, -0.25);

    }

    public void extendIntakeAutoStop() {

        intakePosition.set(ControlMode.Position, extendPosition);

    }

    public void retractIntakeAutoStop() {

        intakePosition.set(ControlMode.Position, retractPosition);

    }

    public void spinIntake() {

        intakeSpin.set(ControlMode.PercentOutput, 0.5);

    }

    public void stopIntakePosition() {
    
        intakePosition.set(ControlMode.PercentOutput, 0);

    }

    public void stopIntakeSpin() {

        intakeSpin.set(ControlMode.PercentOutput, 0);

    }
    
}
