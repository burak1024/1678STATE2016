package frc.robot.Subsystems.Intake;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.lib.Subsystem;

public class IntakeSubsystem extends Subsystem {
    private final TalonFX intakeMotorR = new TalonFX(IntakeConstants.INTAKE_MOTOR_ID1);
    private final TalonFX intakeMotorL = new TalonFX(IntakeConstants.INTAKE_MOTOR_ID2);
    private final VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private static IntakeSubsystem instance;

    public static IntakeSubsystem getInstance() {
        if (instance == null) {
            instance = new IntakeSubsystem();
        }
        return instance;
    }

    public static IntakeSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public IntakeSubsystem() {
        intakeMotorR.getConfigurator().apply(IntakeConfig.config());
        intakeMotorL.getConfigurator().apply(IntakeConfig.config());
    }

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
                    () -> idlePMethods(),
                    () -> emptyMethod()
            },
            {
                    () -> intakingIMethods(),
                    () -> intakingPMethods(),
                    () -> intakingEMethods()
            }
    };

    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }

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
    private void idlePMethods() {
        intakeMotorL.setControl(Voltage.withOutput(0.0));
        intakeMotorR.setControl(Voltage.withOutput(0.0));
    }
}