/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.Swerve;

import java.lang.Math;
import java.sql.Time;
import java.time.Clock;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class SwerveMath {
    private final double length;
    private final double width;
    private final double diagonal;

    private CentricMode centricMode = CentricMode.Field; //this is where the inital drive mode is specified

    public void setModeRobot() {
        centricMode = CentricMode.Robot;
    }

    public void setModeField() {
        centricMode = CentricMode.Field;
    }
    
    public CentricMode getCentricMode() {
		return centricMode;
    }

    public void toggleCentricMode() {
        switch(centricMode) {
            case Robot: centricMode=CentricMode.Field;
                break;
            case Field: centricMode=CentricMode.Robot;
                break;
        }
    }

    public SwerveMath(double width, double length){
        // frDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        // flDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        // rrDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        // rlDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        // frSteer.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        // flSteer.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        // rrSteer.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        // rlSteer.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    
        this.width = width;
        this.length = length;
        diagonal = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
    }

    public void move(double fwd, double str, double rcw, double gyroVal, SwerveDirective[] swerveDirectives){
        /* WORKING SWERVE CODE (now outdated)
        //Module sun gears face the OUTER RIM of the robot.
        double a = str - rcw*(length/diagonal);
        double b = str + rcw*(length/diagonal);
        double c = fwd - rcw*(width/diagonal);
        double d = fwd + rcw*(width/diagonal);

        double frs = Math.sqrt(Math.pow(b,2)+Math.pow(c,2)); 
        double fls = -Math.sqrt(Math.pow(b,2)+Math.pow(d,2));
        double rrs = Math.sqrt(Math.pow(a,2)+Math.pow(d,2));
        double rls = -Math.sqrt(Math.pow(a,2)+Math.pow(c,2));
        
        double fra = Math.atan2(b,c)*180/Math.PI;
        double fla = Math.atan2(b,d)*180/Math.PI;
        double rra = Math.atan2(a,d)*180/Math.PI;
        double rla = Math.atan2(a,c)*180/Math.PI; 

        if(rcw != 0 && fwd == 0 && str == 0){
            fra = Math.atan2(b,c)*180/Math.PI - 90;
            fla = Math.atan2(b,d)*180/Math.PI + 90;
        }*/
    
        //Beyblade Code
        //4048's (and everyone else's) field centric math code
        if (isFieldCentric()){
            //double gyro = rcw * Robot.m_timer.get() * Math.PI * 2; //hardware substitute
            double gyro = (gyroVal * Math.PI) / 180;
            SmartDashboard.putNumber("gyro", gyro);
            double temp = fwd * Math.cos(gyro) + str * Math.sin(gyro);
            str = -fwd * Math.sin(gyro) + str * Math.cos(gyro);
            fwd = temp;
        }

        double a = str - rcw*(length/diagonal);
        double b = str + rcw*(length/diagonal);
        double c = fwd - rcw*(width/diagonal);
        double d = fwd + rcw*(width/diagonal);
        
        double frs = Math.sqrt(Math.pow(b,2)+Math.pow(c,2)); 
        double fls = Math.sqrt(Math.pow(b,2)+Math.pow(d,2));
        double rrs = Math.sqrt(Math.pow(a,2)+Math.pow(d,2));
        double rls = Math.sqrt(Math.pow(a,2)+Math.pow(c,2));

        double fra = -Math.atan2(b,c)*180/Math.PI;
        double fla = Math.atan2(b,d)*180/Math.PI;
        double rra = -Math.atan2(a,d)*180/Math.PI;
        double rla = Math.atan2(a,c)*180/Math.PI;
        
        //double temp = (fwd*Math.cos(gyro.getAngle())) + str*Math.sin(gyro.getAngle());
        double max = frs; 
        if(fls>max){
            max = fls;
        }
        if(rls>max){
            max = rls;
        }
        if(rrs>max){
            max = rrs;
        }
        if(max>1){
            frs/=max;
            fls/=max;
            rrs/=max;
            rls/=max;
        }

        //changes -180 to 180 angles to -0.5 to 0.5 angles 
        fra /= 360;
        fla /= 360;
        rra /= 360;
        rla /= 360;

        /*
        SmartDashboard.putNumber("FR ANGLE", fra);
        SmartDashboard.putNumber("FL ANGLE", fla);
        SmartDashboard.putNumber("RR ANGLE", rra);
        SmartDashboard.putNumber("RL ANGLE", rla);
        */

        setDirective(swerveDirectives[0], fra, frs);
        setDirective(swerveDirectives[1], fla, fls);
        setDirective(swerveDirectives[2], rra, rrs);
        setDirective(swerveDirectives[3], rla, rls);

    }

    private void setDirective(SwerveDirective swerveDirective, double angle, double speed){
        swerveDirective.setAngle(angle);
        swerveDirective.setSpeed(speed);
    }

    private boolean isFieldCentric(){
        return centricMode.equals(CentricMode.Field);
    }

}