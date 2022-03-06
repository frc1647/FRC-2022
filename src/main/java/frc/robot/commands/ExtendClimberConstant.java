package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ClimberSubsystem;

public class ExtendClimberConstant extends Command {

    /**Extends the climber to a height specified in {@link ClimberSubsystem} */
    public ExtendClimberConstant() {
        requires(Robot.ClimberSubsystem);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.ClimberSubsystem.extendClimber(0.25);
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