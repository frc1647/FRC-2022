package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ClimberSubsystem;

public class RetractClimber extends Command {

    public RetractClimber() {
        requires(Robot.ClimberSubsystem);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.ClimberSubsystem.retractClimberAutoStop();
    }

    @Override
    protected void end() {
        Robot.ClimberSubsystem.stopClimber();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
    
}