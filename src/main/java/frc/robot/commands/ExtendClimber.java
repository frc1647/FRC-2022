package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ClimberSubsystem;

public class ExtendClimber extends Command {

    /**Extends the climber to a height specified in {@link ClimberSubsystem} */
    public ExtendClimber() {
        requires(Robot.ClimberSubsystem);
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        Robot.ClimberSubsystem.ClimberAutoStop(false);
    }

    @Override
    protected void end() {
        Robot.ClimberSubsystem.stopClimber();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
}