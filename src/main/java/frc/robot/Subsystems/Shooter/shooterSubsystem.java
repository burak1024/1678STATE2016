package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.LimelightHelpers;
import frc.robot.Subsystems.SuperStructure;
import frc.robot.lib.Subsystem;

public class ShooterSubsystem extends Subsystem {
    private final TalonFX shooterMotor = new TalonFX(shooterConstants.SHOOTER_MOTOR_ID);
    private final VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private Pose2d robotPos = LimelightHelpers.getBotPose2d("limelight");
    private static ShooterSubsystem instance;

    public static ShooterSubsystem getInstance() {
        if (instance == null) {
            instance = new ShooterSubsystem();
        }
        return instance;
    }

    public static ShooterSubsystem subsystem() {
        return getInstance();
    }

    public ShooterSubsystem() {
        SuperStructure.setTeamTranslations();
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

    private void setMotor() {
        Translation2d robotpos = robotPos.getTranslation();
        double z =shooterConstants.shooterVOLTMap().get(SuperStructure.targetPos.getDistance(robotpos));
        shooterMotor.setControl(Voltage.withOutput(z));
    }


    private void shooterIMethods() {
        setMotor();
    }

    private void shooterPMethods() {
        setMotor();
    }

    private void shooterEMethods() {
        setMotor();
    }

}
