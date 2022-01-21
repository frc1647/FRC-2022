/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import frc.robot.commands.Climber.*;
//import frc.robot.commands.Intake.*;
import frc.robot.commands.Movement.*;
//import frc.robot.commands.Shooter.*;
/*import frc.robot.commands.HighGoalShoot;
import frc.robot.commands.LowGoalShoot;
import frc.robot.commands.Align;
import frc.robot.commands.LoadCells;
import frc.robot.commands.UnloadCells;
import frc.robot.commands.SnapToAngle;
import frc.robot.commands.ClimbArmDown;
import frc.robot.commands.ClimbArmUp;
import frc.robot.commands.WinchUp;
import frc.robot.commands.FeedShooter;
import frc.robot.commands.shootHighFromAnywhere;*/

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
    JoystickButton snapToAngle = new JoystickButton(rightJoy, 1);
    JoystickButton alignWithGoalJoystickButton = new JoystickButton(rightJoy, 3);
    JoystickButton climbArmUpJoystickButton = new JoystickButton(rightJoy, 5);
    JoystickButton climbArmDownJoystickButton = new JoystickButton(rightJoy, 4);
    JoystickButton winchUpJoystickButton = new JoystickButton(rightJoy, 2);

    //alignWithGoalJoystickButton.whenPressed(new Align());
        
    //snapToAngle.whileHeld(new SnapToAngle());
    
    //climbArmUpJoystickButton.whileHeld(new ClimbArmUp());
    //climbArmDownJoystickButton.whileHeld(new ClimbArmDown());
    //winchUpJoystickButton.whileHeld(new WinchUp());


    //Left Joystick Left Joystick Left Joystick Left Joystick Left Joystick
    JoystickButton shootJoystickButton = new JoystickButton(leftJoy, 1);
    JoystickButton highGoalShootJoystickButton = new JoystickButton(leftJoy, 3);
    JoystickButton lowGoalShootJoystickButton = new JoystickButton(leftJoy, 2);
    JoystickButton loadCellsJoystickButton = new JoystickButton(leftJoy, 5);
    JoystickButton unloadCellsJoystickButton = new JoystickButton(leftJoy, 4);

    //shootJoystickButton.whenPressed(new FeedShooter());

    //highGoalShootJoystickButton.whileHeld(new HighGoalShoot());
    //lowGoalShootJoystickButton.whileHeld(new LowGoalShoot());

    //loadCellsJoystickButton.whileHeld(new LoadCells());
    //unloadCellsJoystickButton.whileHeld(new UnloadCells());


    //Tablet Tablet Tablet Tablet Tablet
    JoystickButton highGoalShoot = new JoystickButton(tablet, 1);
    JoystickButton lowGoalShoot = new JoystickButton(tablet, 2);
    JoystickButton alignWithGoal = new JoystickButton(tablet, 3);
    JoystickButton loadCells = new JoystickButton(tablet, 6);
    JoystickButton unloadCells = new JoystickButton(tablet, 7);
    JoystickButton climbArmUp = new JoystickButton(tablet, 8);
    JoystickButton climbArmDown = new JoystickButton(tablet, 9);
    JoystickButton winchUp = new JoystickButton(tablet, 10);
    JoystickButton snapToAngleTableButton = new JoystickButton(rightJoy, 1);
    JoystickButton shoot = new JoystickButton(tablet, 11);
    
    //highGoalShoot.whileHeld(new HighGoalShoot());
    //lowGoalShoot.whileHeld(new LowGoalShoot());
    //shoot.whenPressed(new FeedShooter());
    
    //alignWithGoal.whenPressed(new Align());
    
    //loadCells.whileHeld(new LoadCells());
    //unloadCells.whileHeld(new UnloadCells());
    
    //climbArmUp.whileHeld(new ClimbArmUp());
    //climbArmDown.whileHeld(new ClimbArmDown());
    //winchUp.whileHeld(new WinchUp());
  
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
