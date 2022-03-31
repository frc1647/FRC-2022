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
    //JoystickButton intakeUp = new JoystickButton(rightJoy, 4);
    //JoystickButton intakeDown = new JoystickButton(rightJoy, 5);

    ballsUp.whenPressed(new BallLiftUp());
    //ballsUp.whenPressed(new BallLiftUpSwitch());

    ballsDown.whenPressed(new BallLiftDown());
    climbUpConstant.whileHeld(new ExtendClimberConstant());
    climbDownConstant.whileHeld(new RetractClimberConstant());
    //intakeSpin.whileHeld(new SpinIntake(1));
    //intakeUp.whenPressed(new RetractIntake());
    //intakeDown.whenPressed(new ExtendIntake());

    //Left Joystick Left Joystick Left Joystick Left Joystick Left Joystick

    JoystickButton climbUp = new JoystickButton(leftJoy, 3);
    JoystickButton climbDown = new JoystickButton(leftJoy, 2);
    JoystickButton leftballUpConstant = new JoystickButton(leftJoy, 6);
    JoystickButton leftballDownConstant = new JoystickButton(leftJoy, 7);
    JoystickButton rightballUpConstant = new JoystickButton(leftJoy, 11);
    JoystickButton rightballDownConstant = new JoystickButton(leftJoy, 10);
    JoystickButton stopMechanisims = new JoystickButton(leftJoy, 5);
    //JoystickButton intakeReverse = new JoystickButton(leftJoy, 1);

    climbUp.whenPressed(new ExtendClimber());
    climbDown.whenPressed(new RetractClimber());
    leftballUpConstant.whileHeld(new LeftConstant(false));
    leftballDownConstant.whileHeld(new LeftConstant(true));
    rightballUpConstant.whileHeld(new RightConstant(false));
    rightballDownConstant.whileHeld(new RightConstant(true));
    stopMechanisims.whenPressed(new StopMechanisims());
    //intakeReverse.whileHeld(new SpinIntake(-1));

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

    cClimbUp.whileHeld(new ExtendClimberConstant());
    cClimbDown.whileHeld(new RetractClimberConstant());
    cBallUp.whenPressed(new BallLiftUp());
    cBallDown.whenPressed(new BallLiftDown());
    cStopMechanisims.whenPressed(new StopMechanisims());

    cLeftballUpConstant.whileHeld(new LeftConstant(false));
    cLeftballDownConstant.whileHeld(new LeftConstant(true));
    cRightballUpConstant.whileHeld(new RightConstant(false));
    cRightballDownConstant.whileHeld(new RightConstant(true));
    
    /* Old Controller
    //Controller Controller Controller Controller Controller Controller
    JoystickButton cIntakeSpin = new JoystickButton(controller, 6);
    JoystickButton cIntakeReverse = new JoystickButton(controller, 5);
    JoystickButton cLiftBall = new JoystickButton(controller, 4);
    JoystickButton cLowerBall = new JoystickButton(controller, 1);
    //JoystickButton cClimbUp = new JoystickButton(controller, 3);
    //JoystickButton cClimbDown = new JoystickButton(controller, 2);
    JoystickButton climberConstantUp = new JoystickButton(controller, 3);
    JoystickButton climberConstantDown = new JoystickButton(controller, 2);
    
    //cIntakeSpin.whileHeld(new SpinIntake(1));
    //cIntakeReverse.whileHeld(new SpinIntake(-1));
    cLiftBall.whenPressed(new BallLiftUp());
    climberConstantUp.whenPressed(new ExtendClimberConstant());
    cLowerBall.whenPressed(new BallLiftDown());
    climberConstantDown.whenPressed(new RetractClimberConstant());
    //cClimbUp.whenPressed(new ExtendClimber());
    //cClimbDown.whenPressed(new RetractClimber());
    //climberConstantUp.whenPressed(new ExtendClimberConstant());
    //climberConstantDown.whenPressed(new RetractClimberConstant());
    */
  }

  public Joystick getLeftJoy(){
    return leftJoy;
  }

  public Joystick getRightJoy() {
    return rightJoy;
  }
  /*public Joystick getTablet(){
    return tablet;
  
  }*/

  public XboxController getController() {
    return controller;
  }  
}
