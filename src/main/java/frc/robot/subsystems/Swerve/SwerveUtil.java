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
public class SwerveUtil {
    public static double convertEncoderValue(double encoderValue){
		double encPos = encoderValue;
		double gearRatio = 1024;
		
		encPos /= gearRatio; //inputted gearratio is encoder tics per rotate 
		encPos = encPos % 1;
		
		return encPos;
	}

	public double modPos(double in, double m){
        double mod = in % m;
        if(in < 0) {
            mod += m;
        } else if(in == m){
			mod = 0;
		}
        return mod;
    }
	
	public static double convertAngle(double angle, double gearRatio){
		double encVal = angle;
		
		encVal += gearRatio;
		
		return encVal;
	}
}
