// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.NewSwerve;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/** Add your docs here. */
public class SwerveModule {

    private BaseMotorController driveMotor;
    private WPI_TalonSRX steerMotor;
    private double encoderTics;
    private double targetPos;

    public SwerveModule(BaseMotorController drive, WPI_TalonSRX steer, double encTics) {
        this.driveMotor = drive;
        this.steerMotor = steer;
        this.encoderTics = encTics;
        //this.move(0, 0); // way to move the modules to face their zero position at code start
        this.setAngle(0);
    }

    private void setSpeed(double speed) {
        driveMotor.set(ControlMode.PercentOutput, speed);
    }

    private void setAngle(double angle) {
        steerMotor.set(ControlMode.Position, angle);
    }

    public void move(double speed, double angle) {
        double currentPos = steerMotor.getSelectedSensorPosition(0);
        double currentAngle = (currentPos / encoderTics * 360.0) % 360;
        // converts -360 360 angles to -180 180 angles
        if (currentAngle > 180.0) {
            currentAngle -= 360.0;
        } else if (currentAngle <= -180.0) { //might just be <, not <=
            currentAngle += 360.0;
        }
        double deltaDegrees = (currentAngle - angle) * -1; //i.e. a -180 degree delta means the target angle is 180 degrees behind the current angle
        // If we need to turn more than 180 degrees, it's faster to turn in the opposite direction
        if (Math.abs(deltaDegrees) > 90.0) {
            deltaDegrees -= 180.0 * Math.signum(deltaDegrees);
            speed = -speed;
        }
        targetPos = currentPos + deltaDegrees * encoderTics / 360.0; //in encoder tics
        setAngle(targetPos);
        setSpeed(speed);
    }

    public void move2(double speed, double angle) {
        double convAngle = angle * encoderTics / 360.0;
        double currentPos = steerMotor.getSelectedSensorPosition(0);

        double deltaTics = (currentPos - convAngle) * -1;
        if (Math.abs(deltaTics) > 256.0) {
            deltaTics -= 512.0 * Math.signum(deltaTics);
            speed = -speed;
        }
        targetPos = currentPos + deltaTics;
        setAngle(targetPos);
        setSpeed(speed);
    }

    public void move3(double speed, double angle) {
        double convAngle = angle * encoderTics / 360.0;
        double currentPos = steerMotor.getSelectedSensorPosition(0);
        
        double convPos = currentPos % encoderTics;
        if (Math.abs(convPos) > 512) { //will produce both 512 and -512 as a value, might need to fix
            convPos -= 1024 * Math.signum(convPos);
        }

        double deltaTics = (convPos - convAngle) * -1;
        if (Math.abs(deltaTics) > 256.0) {
            deltaTics -= 512.0 * Math.signum(deltaTics);
            speed = -speed;
        }
        targetPos = deltaTics + currentPos;

        setAngle(targetPos);
        setSpeed(speed);
    }

    public double getTargetPos() {
        return targetPos;
    }

    public double getCurrentPos() {
        return steerMotor.getSelectedSensorPosition(0);
    }

}
