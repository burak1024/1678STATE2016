package frc.robot.Subsystems.Climb;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.lib.Subsystem;

public class ClimbSubsystem extends Subsystem {
    Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    private static ClimbSubsystem instance;

    public static ClimbSubsystem getInstance() {
        if (instance == null) {
            instance = new ClimbSubsystem();
        }
        return instance;
    }

    public static ClimbSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        climbing(1);

        public final int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    public Runnable[][] methods = {
            {
                    () -> emptyMethod(),
                    () -> emptyMethod(),
                    () -> emptyMethod()
            },
            {
                    () -> climbingIMethods(),
                    () -> emptyMethod(),
                    () -> climbingEMethods()
            }
    };

    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }

    private void climbingIMethods() {
        solenoid.set(true);
    }
    private void climbingEMethods() {
        solenoid.set(false);
    }

}