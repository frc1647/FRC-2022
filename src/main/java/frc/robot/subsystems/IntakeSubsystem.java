package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class IntakeSubsystem extends Subsystem {

    //private WPI_TalonFX intakePosition;
    private BaseMotorController intakeSpin;

    //private double extendPosition;
    //private double retractPosition;
    
    @Override
    protected void initDefaultCommand() {}

    public IntakeSubsystem() {
        //intakePosition = RobotMap.intakePosition;
        //intakePosition.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
        intakeSpin = RobotMap.intakeSpin;

        //extendPosition = 0;
        //retractPosition = 0;
    }

    public void spinIntake(int invert) {

        intakeSpin.set(ControlMode.PercentOutput, 0.80 * invert);

    }

    public void stopIntakeSpin() {

        intakeSpin.set(ControlMode.PercentOutput, 0);

    }

    /*public void extendIntake() {

        intakePosition.set(ControlMode.PercentOutput, 0.25);

    }

    public void retractIntake() {

        intakePosition.set(ControlMode.PercentOutput, -0.25);

    }

    public void stopIntakePosition() {
    
        intakePosition.set(ControlMode.PercentOutput, 0);

    }

    public void extendIntakeAutoStop() {

        intakePosition.set(ControlMode.Position, extendPosition);

    }

    public void retractIntakeAutoStop() {

        intakePosition.set(ControlMode.Position, retractPosition);

    }*/

}
