package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Elevator.elevatorSubsystem;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.Subsystems.Climb.ClimbSubsystem;
import frc.robot.Subsystems.Shooter.shooterSubsystem;

public class SuperStructure extends SubsystemBase {
    private static SuperStructure instance;

    public static SuperStructure getInstance() {
        if (instance == null) {
            instance = new SuperStructure();
        }
        return instance;
    }

    private final Runnable[] methods = {
            () -> idleIMethods(),
            () -> intakingIMethods(),
            () -> shootingIMethods(),
            () -> climbingIMethods(),
    };

    private void idleIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.idle, ArmSubsystem.state.idle);
    }

    private void intakingIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.intaking, ElevatorSubsystem.state.idle, ArmSubsystem.state.idle);
    }

    private void changeSubsystemStates(IntakeSubsystem.state intakeState, elevatorSubsystem.state elevatorState,
            shooterSubsystem.state shooterState, ClimbSubsystem.state climbState) {
        IntakeSubsystem. ; 
        elevatorSubsystem.subsystem().changeState(elevatorState);

    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        intaking(1),
        shooting(2),
        climbing(3);

        public final int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    @Override
    public void periodic() {

        SmartDashboard.putString("SuperStructure/State", currentState.toString());

    }

    public SuperStructure() {
        IntakeSubsystem.subsystem();
        ElevatorSubsystem.subsystem();
        shooterSubsystem.
    }

    public void changeState(state newState) {
        if (this.currentState == newState)
            return;
        currentState = newState;
        methods[currentState.stateNum].run();
        SmartDashboard.putNumber("ActiveState", currentState.stateNum);
    }

    public static Command pull() {
        return new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.pulling));
    }

}