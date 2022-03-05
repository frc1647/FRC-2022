/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.BallLiftDown;
import frc.robot.commands.BallLiftUp;
import frc.robot.commands.ExtendClimber;
import frc.robot.commands.RetractClimber;
import frc.robot.commands.Intake.ExtendIntake;
import frc.robot.commands.Intake.RetractIntake;
import frc.robot.commands.Intake.SpinIntake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  Joystick leftJoy = RobotMap.leftJoy;
  Joystick rightJoy = RobotMap.rightJoy;
  Joystick tablet = RobotMap.tablet;

  public OI(){
    //Right Joystick Right Joystick Right Joystick Right Joystick Right Joystick
    JoystickButton intakeSpin = new JoystickButton(rightJoy, 1);
    JoystickButton ballsUp = new JoystickButton(rightJoy, 3);
    JoystickButton ballsDown = new JoystickButton(rightJoy, 2);
    //JoystickButton intakeUp = new JoystickButton(rightJoy, 4);
    //JoystickButton intakeDown = new JoystickButton(rightJoy, 5);

    ballsUp.whenPressed(new BallLiftUp());
    ballsDown.whenPressed(new BallLiftDown());
    intakeSpin.whileHeld(new SpinIntake(1));
    //intakeUp.whenPressed(new RetractIntake());
    //intakeDown.whenPressed(new ExtendIntake());

    JoystickButton climbUp = new JoystickButton(leftJoy, 3);
    JoystickButton climbDown = new JoystickButton(leftJoy, 2);
    JoystickButton intakeReverse = new JoystickButton(leftJoy, 5);

    //climbUp.whenPressed(new ExtendClimber());
    //climbDown.whenPressed(new RetractClimber());
    intakeReverse.whileHeld(new SpinIntake(-1));
  
  }

  public Joystick getLeftJoy(){
    return leftJoy;
  }
  public Joystick getRightJoy() {
    return rightJoy;
  }
  public Joystick getTablet(){
    return tablet;
  
  }
  
}
