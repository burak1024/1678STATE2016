package frc.robot.Subsystems.Shield;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ShieldConfig {
    public static TalonFXConfiguration config(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit=ShieldConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicCruiseVelocity = ShieldConstants.CRUISE_VELOCITY;
        config.MotionMagic.MotionMagicAcceleration = ShieldConstants.ACCELERATION;
        config.MotionMagic.MotionMagicJerk = ShieldConstants.JERK;

        config.Slot0.kS=ShieldConstants.kS;
        config.Slot0.kV=ShieldConstants.kV;
        config.Slot0.kA=ShieldConstants.kA;

        config.Slot0.kP = ShieldConstants.kP;
        config.Slot0.kI =ShieldConstants.kI;
        config.Slot0.kD = ShieldConstants.kD;

        config.MotorOutput.NeutralMode =NeutralModeValue.Coast;
        return config;
    }
}