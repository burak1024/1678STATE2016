package frc.robot.Subsystems.Elevator;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.LimelightHelpers;
import frc.robot.Subsystems.SuperStructure;
import frc.robot.lib.Subsystem;

public class ElevatorSubsystem extends Subsystem {
    private final TalonFX elevatorMotor1 = new TalonFX(elevatorConstants.ELEVATOR_MOTOR1_ID);
    private final TalonFX elevatorMotor2 = new TalonFX(elevatorConstants.ELEVATOR_MOTOR2_ID);
    private final TalonFX elevatorMotor3 = new TalonFX(elevatorConstants.ELEVATOR_MOTOR3_ID);
    private final MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private Pose2d robotPos = LimelightHelpers.getBotPose2d("limelight");
    private static ElevatorSubsystem instance;

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
        SuperStructure.setTeamTranslations();
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
                    () -> idlePMethods(),
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

    private void setElevPosition() {
        Translation2d robotpos = robotPos.getTranslation();
        double Pos= elevatorConstants.positionMap().get(SuperStructure.targetPos.getDistance(robotpos));
        double Height = elevatorConstants.heightMap().get(SuperStructure.targetPos.getDistance(robotpos));
        elevatorMotor1.setControl(Motion.withPosition(Pos));
        elevatorMotor2.setControl(Motion.withPosition(Pos));
        elevatorMotor3.setControl(Motion.withPosition(Height));

    }
    private void climbIMethods() {
        setElevPosition();
    }

    private void climbPMethods() {
        setElevPosition();
    }

    private void climbEMethods() {
        setElevPosition();
    }

    private void shootIMethods() {
        setElevPosition();
    }

    private void shootpMethods() {
        setElevPosition();
    }

    private void shootEMethods() {
        setElevPosition();
    }
    private void idlePMethods(){
        set0();
    }
    private void set0(){
        elevatorMotor1.setControl(Motion.withPosition(0));
        elevatorMotor2.setControl(Motion.withPosition(0));
        elevatorMotor3.setControl(Motion.withPosition(0));
    }


}
