package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;

public class ExtendIntake extends Command {

    IntakeSubsystem intakeSubsystem = Robot.IntakeSubsystem;

    public ExtendIntake() {
        requires(Robot.IntakeSubsystem);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        intakeSubsystem.extendIntakeAutoStop();
    }

    @Override
    protected void end() {
        intakeSubsystem.stopIntakePosition();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
    
}