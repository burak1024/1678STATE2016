package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class shooterConfig {
    public static TalonFXConfiguration config(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit=shooterConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicCruiseVelocity = shooterConstants.CRUISE_VELOCITY;
        config.MotionMagic.MotionMagicAcceleration = shooterConstants.ACCELERATION;
        config.MotionMagic.MotionMagicJerk = shooterConstants.JERK;

        config.Slot0.kS=shooterConstants.kS;
        config.Slot0.kV=shooterConstants.kV;
        config.Slot0.kA=shooterConstants.kA;

        config.Slot0.kP = shooterConstants.kP;
        config.Slot0.kI = shooterConstants.kI;
        config.Slot0.kD = shooterConstants.kD;

        config.MotorOutput.NeutralMode =NeutralModeValue.Coast;
        return config;
    }
}
