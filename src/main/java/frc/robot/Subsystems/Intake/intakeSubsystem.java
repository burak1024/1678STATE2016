package frc.robot.Subsystems.Intake;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.lib.Subsystem;

public class intakeSubsystem extends Subsystem {
    private final TalonFX intakeMotorR = new TalonFX(0);
    private final TalonFX intakeMotorL = new TalonFX(0);
    private final VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private static intakeSubsystem instance;

    public intakeSubsystem getInstance() {
        if (instance == null) {
            instance = new intakeSubsystem();
        }
        return instance;
    }

    public intakeSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        intaking(1);

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
                    () -> intakingIMethods(),
                    () -> intakingPMethods(),
                    () -> intakingEMethods()
            }
    };

    private void intakingIMethods() {
        intakeMotorL.setControl(Voltage.withOutput(3.0));
        intakeMotorR.setControl(Voltage.withOutput(3.0));
    }

    private void intakingPMethods() {
        intakeMotorL.setControl(Voltage.withOutput(3.0));
        intakeMotorR.setControl(Voltage.withOutput(3.0));
    }

    private void intakingEMethods() {
        intakeMotorL.setControl(Voltage.withOutput(3.0));
        intakeMotorR.setControl(Voltage.withOutput(3.0));
    }
}