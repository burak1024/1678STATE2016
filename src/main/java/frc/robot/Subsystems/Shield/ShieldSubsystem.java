package frc.robot.Subsystems.Shield;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class ShieldSubsystem extends Subsystem {
    private final TalonFX shieldMotor = new TalonFX(16);
    private final MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private static ShieldSubsystem instance;

    public static ShieldSubsystem getInstance() {
        if (instance == null) {
            instance = new ShieldSubsystem();
        }
        return instance;
    }

    public static ShieldSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        shielding(1);

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
                    () -> shieldingIMethods(),
                    () -> shieldingPMethods(),
                    () -> shieldingEMethods()
            }
    };

    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }

    public void shieldingIMethods() {
        shieldMotor.setControl(Motion.withPosition(5));
    }

    public void shieldingPMethods() {
        shieldMotor.setControl(Motion.withPosition(5));
    }

    public void shieldingEMethods() {
        shieldMotor.setControl(Motion.withPosition(0));
    }
}
