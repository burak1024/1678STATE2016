package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.LimelightHelpers;
import frc.robot.lib.Subsystem;

public class shooterSubsystem extends Subsystem {
    private final TalonFX shooterMotor = new TalonFX(shooterConstants.SHOOTER_MOTOR_ID);
    private final VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private Pose2d robotPos = LimelightHelpers.getBotPose2d("limelight");
    private static shooterSubsystem instance;

    public static shooterSubsystem getInstance() {
        if (instance == null) {
            instance = new shooterSubsystem();
        }
        return instance;
    }

    public shooterSubsystem subsystem() {
        return getInstance();
    }

    public shooterSubsystem() {
        shooterMotor.getConfigurator().apply(shooterConfig.config());
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        shooting(1);

        public int stateNum;

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
                    () -> shooterIMethods(),
                    () -> shooterPMethods(),
                    () -> shooterEMethods()
            }
    };

    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }

    private double setVoltage() {
        double x = robotPos.getX();
        double y = robotPos.getY();
        double hypot = Math.hypot(x, y);
        return shooterConstants.shooterVOLTMap().get(hypot);
    }

    private void setMotor(double voltage) {
        shooterMotor.setControl(Voltage.withOutput(voltage));
    }

    private void shooterIMethods() {
        setMotor(setVoltage());
    }

    private void shooterPMethods() {
        setMotor(setVoltage());
    }

    private void shooterEMethods() {
        setMotor(0);
    }

}
