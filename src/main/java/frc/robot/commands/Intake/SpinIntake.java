package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;

public class SpinIntake extends Command {

    private int invert;

    /** Spins the intake.
     * 
     * @param reverse If true, the intake spins in reverse. If false, the intake spins normally.
     */
    public SpinIntake(boolean reverse) {
        requires(Robot.IntakeSubsystem);
        invert = reverse ? -1:1;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.IntakeSubsystem.spinIntake(invert);
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