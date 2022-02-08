package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;

public class ExtendIntake extends Command {

    public ExtendIntake() {
        requires(Robot.IntakeSubsystem);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.IntakeSubsystem.extendIntakeAutoStop();
    }

    @Override
    protected void end() {
        Robot.IntakeSubsystem.stopIntakePosition();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void interrupted() {
        Robot.IntakeSubsystem.stopIntakePosition();
    }
    
}