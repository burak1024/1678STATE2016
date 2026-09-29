package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Elevator.ElevatorSubsystem;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.Subsystems.Shield.ShieldSubsystem;
import frc.robot.Subsystems.Climb.ClimbSubsystem;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;

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
            () -> shieldingIMethods(),
            () -> intakingIMethods(),
            () -> shootingIMethods(),
            () -> climbingIMethods(),
    };

    private void idleIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.idle, ShooterSubsystem.state.idle,
                ClimbSubsystem.state.idle,ShieldSubsystem.state.idle);
    }
    private void shieldingIMethods(){
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.idle, ShooterSubsystem.state.idle, ClimbSubsystem.state.idle, ShieldSubsystem.state.shielding);;
    }

    private void intakingIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.intaking, ElevatorSubsystem.state.idle, ShooterSubsystem.state.idle,
                ClimbSubsystem.state.idle,ShieldSubsystem.state.shielding);
    }

    private void shootingIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.shooting,
                ShooterSubsystem.state.shooting, ClimbSubsystem.state.idle,ShieldSubsystem.state.idle);
    }

    private void climbingIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, ElevatorSubsystem.state.climb, ShooterSubsystem.state.idle,
                ClimbSubsystem.state.climbing,ShieldSubsystem.state.idle);
    }

    private void changeSubsystemStates(IntakeSubsystem.state intakeState, ElevatorSubsystem.state elevatorState,
            ShooterSubsystem.state shooterState, ClimbSubsystem.state climbState,ShieldSubsystem.state shieldState) {
        IntakeSubsystem.subsystem().changeState(intakeState);
        ElevatorSubsystem.subsystem().changeState(elevatorState);
        ShooterSubsystem.getInstance().changeState(shooterState);
        ClimbSubsystem.subsystem().changeState(climbState);
        ShieldSubsystem.subsystem().changeState(shieldState);
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        shielding(1),
        intaking(2),
        shooting(3),
        climbing(4);

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
        ShooterSubsystem.subsystem();
        ClimbSubsystem.subsystem();
        ShieldSubsystem.subsystem();
    }

    public void changeState(state newState) {
        if (this.currentState == newState)
            return;
        currentState = newState;
        methods[currentState.stateNum].run();
        SmartDashboard.putNumber("ActiveState", currentState.stateNum);
    }

}