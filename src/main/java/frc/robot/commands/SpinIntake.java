package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;

public class SpinIntake extends Command {

    public SpinIntake() {
        requires(Robot.IntakeSubsystem);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.IntakeSubsystem.spinIntake();
    }

    @Override
    protected void end() {
        Robot.IntakeSubsystem.stopIntakeSpin();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        Robot.IntakeSubsystem.stopIntakeSpin();
    }
    
}