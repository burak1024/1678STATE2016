package frc.robot.Subsystems.Elevator;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.LimelightHelpers;
import frc.robot.lib.Subsystem;

public class elevatorSubsystem extends Subsystem {
    private final TalonFX elevatorMotor1 = new TalonFX(0);
    private final TalonFX elevatorMotor2 = new TalonFX(0);
    private final MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private Pose2d robotPos = LimelightHelpers.getBotPose2d("limelight");
    private static elevatorSubsystem instance;


    public static elevatorSubsystem getInstance() {
        if (instance == null) {
            instance = new elevatorSubsystem();
        }
        return instance;
    }

    public static elevatorSubsystem subsystem() {
        return getInstance();
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
    public Runnable [][]methods = {
        {
            ()->emptyMethod(),
            ()->emptyMethod(),
            ()->emptyMethod()
        },
        {
            ()->climbIMethods(),
            ()->climbPMethods(),
            ()->climbEMethods()
        },
        {
            ()->shootIMethods(),
            ()->shootpMethods(),
            ()->shootEMethods()
        }
    };
    private double setElevPosition(){
        double x = robotPos.getX();
        double y = robotPos.getY();
        double hypot = Math.hypot(x, y);
        return elevatorConstants.positionMap().get(hypot);
    }
    private void setElev(double Pos){
        elevatorMotor1.setControl(Motion.withPosition(Pos));
        elevatorMotor2.setControl(Motion.withPosition(Pos));
    }
    private void climbIMethods(){
        setElev(5.0);
    }
    private void climbPMethods(){
        setElev(5.0);
    }
    private void climbEMethods(){
        setElev(0.0);
    }
    private void shootIMethods(){
        setElev(setElevPosition());
    }
    private void shootpMethods(){
        setElev(setElevPosition());
    }
    private void shootEMethods(){
        setElev(0);
    }

}
