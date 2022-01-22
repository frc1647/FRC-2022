package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ClimberSubsystem;

public class RetractClimber extends Command {

    ClimberSubsystem climberSubsystem = Robot.ClimberSubsytem;

    public RetractClimber() {
        requires(Robot.ClimberSubsytem);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        climberSubsystem.retractClimberAutoStop();
    }

    @Override
    protected void end() {
        climberSubsystem.stopClimber();
    }


    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return true;
    }
    
}