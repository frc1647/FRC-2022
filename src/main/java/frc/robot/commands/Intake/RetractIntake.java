package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;

public class RetractIntake extends Command {

    public RetractIntake() {
        requires(Robot.IntakeSubsystem);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.IntakeSubsystem.retractIntakeAutoStop();
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