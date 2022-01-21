/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.Swerve;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Movement.Drive;
import frc.robot.Robot;
/**
 * Add your docs here.
 */
public class Swerve {

    private SwerveModule module1;
    private SwerveModule module2;
    private SwerveModule module3;
    private SwerveModule module4;

    private final SwerveMath math; //this might be redundant

    private SwerveDirective[] swerveDirectives = {new SwerveDirective(), new SwerveDirective(), new SwerveDirective(), new SwerveDirective()};

    public Swerve(SwerveModule module1, SwerveModule module2, SwerveModule module3, SwerveModule module4, double width, double length){ //width and length as parameters might be redundant
        this.module1 = module1;
        this.module2 = module2;
        this.module3 = module3;
        this.module4 = module4;

        math = new SwerveMath(width, length); //this might be redundant
    }

    public void move(double fwd, double str, double rcw, double gyroVal){
        math.move(fwd, str, rcw, gyroVal, swerveDirectives); //this might be redundant
        //Robot.swerveMath.move(fwd, str, rcw, gyroVal, swerveDirectives); //this might be a replacement for everything noted as potentally redundnant

        module1.move(swerveDirectives[0].getSpeed(), swerveDirectives[0].getAngle());
        module2.move(swerveDirectives[1].getSpeed(), swerveDirectives[1].getAngle());
        module3.move(swerveDirectives[2].getSpeed(), swerveDirectives[2].getAngle());
        module4.move(swerveDirectives[3].getSpeed(), swerveDirectives[3].getAngle());
        SmartDashboard.putNumber("FL angle", swerveDirectives[0].getAngle() * 360);
        SmartDashboard.putNumber("FR angle", swerveDirectives[1].getAngle() * 360);
        SmartDashboard.putNumber("RL angle", swerveDirectives[2].getAngle() * 360);
        SmartDashboard.putNumber("RR angle", swerveDirectives[3].getAngle() * 360);
        /*SmartDashboard.putNumber("Fl Speed", swerveDirectives[0].getSpeed());
        SmartDashboard.putNumber("Fr Speed", swerveDirectives[1].getSpeed());
        SmartDashboard.putNumber("Bl Speed", swerveDirectives[2].getSpeed());
        SmartDashboard.putNumber("Br Speed", swerveDirectives[3].getSpeed());*/
    }

    public void stop(){
        module1.stop();
        module2.stop();
        module3.stop();
        module4.stop();
    }

}
