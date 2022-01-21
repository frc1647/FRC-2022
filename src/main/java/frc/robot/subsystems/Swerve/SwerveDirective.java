/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.Swerve;

/**
 * Add your docs here.
 */
public class SwerveDirective {

    private double speed;
    private double angle;

    public SwerveDirective(){ //does anything? //yep, its the code that gets run when a SwerveDirective variable is declared without specifing the speed and angle.
        
    }

    public SwerveDirective(double speed, double angle){
        this.speed = speed;
        this.angle = angle;
    }

    public double getSpeed(){
        return speed;
    }

    public double getAngle(){
        return angle;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }

}
