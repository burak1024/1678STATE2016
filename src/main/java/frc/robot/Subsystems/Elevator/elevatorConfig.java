package frc.robot.Subsystems.Elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;


public class elevatorConfig {
    public static TalonFXConfiguration config(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit=elevatorConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicCruiseVelocity = elevatorConstants.CRUISE_VELOCITY;
        config.MotionMagic.MotionMagicAcceleration = elevatorConstants.ACCELERATION;
        config.MotionMagic.MotionMagicJerk = elevatorConstants.JERK;

        config.Slot0.kS=elevatorConstants.kS;
        config.Slot0.kV=elevatorConstants.kV;
        config.Slot0.kA=elevatorConstants.kA;

        config.Slot0.kP = elevatorConstants.kP;
        config.Slot0.kI = elevatorConstants.kI;
        config.Slot0.kD = elevatorConstants.kD;

        config.MotorOutput.NeutralMode =NeutralModeValue.Brake;
        return config;
    }
}
