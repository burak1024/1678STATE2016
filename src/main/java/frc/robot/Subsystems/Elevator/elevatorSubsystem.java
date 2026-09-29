package frc.robot.Subsystems.Elevator;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.LimelightHelpers;
import frc.robot.lib.Subsystem;

public class ElevatorSubsystem extends Subsystem {
    private final TalonFX elevatorMotor1 = new TalonFX(elevatorConstants.ELEVATOR_MOTOR1_ID);
    private final TalonFX elevatorMotor2 = new TalonFX(elevatorConstants.ELEVATOR_MOTOR2_ID);
    private final TalonFX elevatorMotor3 = new TalonFX(elevatorConstants.ELEVATOR_MOTOR3_ID);
    private final MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private Pose2d robotPos = LimelightHelpers.getBotPose2d("limelight");
    private static ElevatorSubsystem instance;
    private Translation2d targetPos;

    public static ElevatorSubsystem getInstance() {
        if (instance == null) {
            instance = new ElevatorSubsystem();
        }
        return instance;
    }

    public static ElevatorSubsystem subsystem() {
        return getInstance();
    }

    public ElevatorSubsystem() {
        elevatorMotor1.getConfigurator().apply(elevatorConfig.config());
        elevatorMotor2.getConfigurator().apply(elevatorConfig.config());
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        climb(1),
        shooting(2);

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
                    () -> climbIMethods(),
                    () -> climbPMethods(),
                    () -> climbEMethods()
            },
            {
                    () -> shootIMethods(),
                    () -> shootpMethods(),
                    () -> shootEMethods()
            }
    };

    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }

    private double setElevPosition() {
        double x = robotPos.getX() - targetPos.getX();
        double y = robotPos.getY() - targetPos.getY();
        double hypot = Math.hypot(x, y);
        return elevatorConstants.positionMap().get(hypot);
    }

    private double setElevHeightPosition() {
        double x = robotPos.getX() - targetPos.getX();
        double y = robotPos.getY() - targetPos.getY();
        double hypot = Math.hypot(x, y);
        return elevatorConstants.heightMap().get(hypot);
    }

    private void setElev(double Pos, double Height) {
        elevatorMotor1.setControl(Motion.withPosition(Pos));
        elevatorMotor2.setControl(Motion.withPosition(Pos));
        elevatorMotor3.setControl(Motion.withPosition(Height));
    }

    private void climbIMethods() {
        setElev(5.0, 5.0);
    }

    private void climbPMethods() {
        setElev(5.0, 5.0);
    }

    private void climbEMethods() {
        setElev(0.0, 5.0);
    }

    private void shootIMethods() {
        setTeamTranslations();
        setElev(setElevPosition(), setElevHeightPosition());
    }

    private void shootpMethods() {
        setElev(setElevPosition(), setElevHeightPosition());
    }

    private void shootEMethods() {
        setElev(0, 0);
    }

    private void setTeamTranslations() {
        Alliance alliance = DriverStation.getAlliance().orElse(Alliance.Blue);

        if (alliance == Alliance.Red) {
            targetPos = elevatorConstants.Blue_target;
        } else {
            targetPos = elevatorConstants.Red_target;
        }
    }

}
