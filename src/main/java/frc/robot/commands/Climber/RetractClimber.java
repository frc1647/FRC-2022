package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ClimberSubsystem;

public class RetractClimber extends Command {

    /** Retracts the climber all the way down. */
    public RetractClimber() {
        requires(Robot.ClimberSubsystem);
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        Robot.ClimberSubsystem.ClimberAutoStop(true);
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