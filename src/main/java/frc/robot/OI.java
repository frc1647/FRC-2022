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
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.StopMechanisims;
import frc.robot.commands.BallLift.*;
import frc.robot.commands.Climber.ExtendClimber;
import frc.robot.commands.Climber.ExtendClimberConstant;
import frc.robot.commands.Climber.RetractClimber;
import frc.robot.commands.Climber.RetractClimberConstant;
import frc.robot.commands.Intake.SpinIntake;
import frc.robot.commands.Movement.toggleCentricMode;
import frc.robot.subsystems.NewSwerve.CentricToggle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  Joystick leftJoy = RobotMap.leftJoy;
  Joystick rightJoy = RobotMap.rightJoy;
  XboxController controller = RobotMap.controller;

  public OI(){
    //Right Joystick Right Joystick Right Joystick Right Joystick Right Joystick
    JoystickButton intakeSpin = new JoystickButton(rightJoy, 1);
    JoystickButton ballsUp = new JoystickButton(rightJoy, 3);
    JoystickButton ballsDown = new JoystickButton(rightJoy, 2);
    JoystickButton climbUpConstant = new JoystickButton(rightJoy, 11);
    JoystickButton climbDownConstant = new JoystickButton(rightJoy, 10);

    ballsUp.whenPressed(new BallLiftUpSwitch());

    ballsDown.whenPressed(new BallLiftDown());
    climbUpConstant.whileHeld(new ExtendClimberConstant());
    climbDownConstant.whileHeld(new RetractClimberConstant());
    intakeSpin.whileHeld(new SpinIntake(false));

    //Left Joystick Left Joystick Left Joystick Left Joystick Left Joystick

    JoystickButton leftballUpConstant = new JoystickButton(leftJoy, 6);
    JoystickButton leftballDownConstant = new JoystickButton(leftJoy, 7);
    JoystickButton rightballUpConstant = new JoystickButton(leftJoy, 11);
    JoystickButton rightballDownConstant = new JoystickButton(leftJoy, 10);
    JoystickButton stopMechanisims = new JoystickButton(leftJoy, 5);
    JoystickButton intakeReverse = new JoystickButton(leftJoy, 1);
    JoystickButton toggleMode = new JoystickButton(leftJoy, 3);
    //do not assign button 4, it is used to set the robot's forward orientation in NewDrive.java

    leftballUpConstant.whileHeld(new LeftConstant(false));
    leftballDownConstant.whileHeld(new LeftConstant(true));
    rightballUpConstant.whileHeld(new RightConstant(false));
    rightballDownConstant.whileHeld(new RightConstant(true));
    stopMechanisims.whenPressed(new StopMechanisims());
    intakeReverse.whileHeld(new SpinIntake(true));

    toggleMode.whenPressed(new toggleCentricMode());

    //Controller Controller Controller Controller Controller Controller Controller 
    JoystickButton cClimbUp = new JoystickButton(controller, 2);
    JoystickButton cClimbDown = new JoystickButton(controller, 1);
    JoystickButton cBallUp = new JoystickButton(controller, 4);
    JoystickButton cBallDown = new JoystickButton(controller, 3);
    JoystickButton cSpinIntake = new JoystickButton(controller, 6);
    JoystickButton cReverseIntake = new JoystickButton(controller, 5);
    //do not assign button 8, it is used to set the robot's forward orientation in NewDrive.java

    /* Only the up functions are used here becuase the purpose of these is for fine-tuning the 
     * either side of the elevator. cBallUp and cBallDown handle most of the actual control.
     */
    JoystickButton cLeftballUpConstant = new JoystickButton(controller, 9); //7
    //JoystickButton cLeftballDownConstant = new JoystickButton(controller, 9); //9
    JoystickButton cRightballUpConstant = new JoystickButton(controller, 10); //8
    //JoystickButton cRightballDownConstant = new JoystickButton(controller, 10); //10

    cClimbUp.whileHeld(new ExtendClimberConstant());
    cClimbDown.whileHeld(new RetractClimberConstant());
    //cClimbUp.whenPressed(new ExtendClimber());
    //cClimbDown.whenPressed(new RetractClimber());
    cBallUp.whenPressed(new BallLiftUpSwitch());
    //cBallUp.whenPressed(new BallLiftUp());
    cBallDown.whenPressed(new BallLiftDown());
    cSpinIntake.whileHeld(new SpinIntake(false));
    cReverseIntake.whileHeld(new SpinIntake(true));

    cLeftballUpConstant.whileHeld(new LeftConstant(false));
    //cLeftballDownConstant.whileHeld(new LeftConstant(true));
    cRightballUpConstant.whileHeld(new RightConstant(false));
    //cRightballDownConstant.whileHeld(new RightConstant(true));
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