/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.*;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.StopMechanisims;
import frc.robot.commands.BallLift.*;
import frc.robot.commands.Climber.ExtendClimber;
import frc.robot.commands.Climber.ExtendClimberConstant;
import frc.robot.commands.Climber.RetractClimber;
import frc.robot.commands.Climber.RetractClimberConstant;
//import frc.robot.commands.Intake.ExtendIntake;
//import frc.robot.commands.Intake.RetractIntake;
import frc.robot.commands.Intake.SpinIntake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  Joystick leftJoy = RobotMap.leftJoy;
  Joystick rightJoy = RobotMap.rightJoy;
  XboxController controller = RobotMap.controller;

  //Joystick tablet = RobotMap.tablet;

  public OI(){
    //Right Joystick Right Joystick Right Joystick Right Joystick Right Joystick
    JoystickButton intakeSpin = new JoystickButton(rightJoy, 1);
    JoystickButton ballsUp = new JoystickButton(rightJoy, 3);
    JoystickButton ballsDown = new JoystickButton(rightJoy, 2);
    JoystickButton climbUpConstant = new JoystickButton(rightJoy, 11);
    JoystickButton climbDownConstant = new JoystickButton(rightJoy, 10);

    ballsUp.whenPressed(new BallLiftUp());
    //ballsUp.whenPressed(new BallLiftUpSwitch(0.2));

    ballsDown.whenPressed(new BallLiftDown());
    climbUpConstant.whileHeld(new ExtendClimberConstant());
    climbDownConstant.whileHeld(new RetractClimberConstant());
    intakeSpin.whileHeld(new SpinIntake(false));

    //Left Joystick Left Joystick Left Joystick Left Joystick Left Joystick

    JoystickButton climbUp = new JoystickButton(leftJoy, 3);
    JoystickButton climbDown = new JoystickButton(leftJoy, 2);
    JoystickButton leftballUpConstant = new JoystickButton(leftJoy, 6);
    JoystickButton leftballDownConstant = new JoystickButton(leftJoy, 7);
    JoystickButton rightballUpConstant = new JoystickButton(leftJoy, 11);
    JoystickButton rightballDownConstant = new JoystickButton(leftJoy, 10);
    JoystickButton stopMechanisims = new JoystickButton(leftJoy, 5);
    JoystickButton intakeReverse = new JoystickButton(leftJoy, 1);

    climbUp.whenPressed(new ExtendClimberConstant());
    climbDown.whenPressed(new RetractClimberConstant());
    leftballUpConstant.whileHeld(new LeftConstant(false));
    leftballDownConstant.whileHeld(new LeftConstant(true));
    rightballUpConstant.whileHeld(new RightConstant(false));
    rightballDownConstant.whileHeld(new RightConstant(true));
    stopMechanisims.whenPressed(new StopMechanisims());
    intakeReverse.whileHeld(new SpinIntake(true));

    //Controller pt2
    JoystickButton cClimbUp = new JoystickButton(controller, 2);
    JoystickButton cClimbDown = new JoystickButton(controller, 1);
    JoystickButton cBallUp = new JoystickButton(controller, 4);
    JoystickButton cBallDown = new JoystickButton(controller, 3);
    JoystickButton cStopMechanisims = new JoystickButton(controller, 6);

    JoystickButton cLeftballUpConstant = new JoystickButton(controller, 7);
    JoystickButton cLeftballDownConstant = new JoystickButton(controller, 9);
    JoystickButton cRightballUpConstant = new JoystickButton(controller, 8);
    JoystickButton cRightballDownConstant = new JoystickButton(controller, 10);

    //cClimbUp.whileHeld(new ExtendClimberConstant());
    //cClimbDown.whileHeld(new RetractClimberConstant());
    cClimbUp.whenPressed(new ExtendClimber());
    cClimbDown.whenPressed(new RetractClimber());
    cBallUp.whenPressed(new BallLiftUp());
    //cBallUp.whenPressed(new BallLiftUpSwitch(0.2));
    cBallDown.whenPressed(new BallLiftDown());
    cStopMechanisims.whenPressed(new StopMechanisims());

    cLeftballUpConstant.whileHeld(new LeftConstant(false));
    cLeftballDownConstant.whileHeld(new LeftConstant(true));
    cRightballUpConstant.whileHeld(new RightConstant(false));
    cRightballDownConstant.whileHeld(new RightConstant(true));
  }

  public Joystick getLeftJoy(){
    return leftJoy;
  }

  public Joystick getRightJoy() {
    return rightJoy;
  }

  public XboxController getController() {
    return controller;
  }  
}