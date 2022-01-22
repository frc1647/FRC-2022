package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;

public class SpinIntake extends Command {

    IntakeSubsystem intakeSubsystem = Robot.IntakeSubsystem;

    public SpinIntake() {
        requires(Robot.IntakeSubsystem);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        intakeSubsystem.spinIntake();
    }

    @Override
    protected void end() {
        intakeSubsystem.stopIntakeSpin();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
    
}