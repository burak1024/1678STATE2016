package frc.robot.Subsystems.Intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class IntakeConfig {
    public static TalonFXConfiguration config(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit=IntakeConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicCruiseVelocity = IntakeConstants.CRUISE_VELOCITY;
        config.MotionMagic.MotionMagicAcceleration = IntakeConstants.ACCELERATION;
        config.MotionMagic.MotionMagicJerk = IntakeConstants.JERK;

        config.Slot0.kS=IntakeConstants.kS;
        config.Slot0.kV=IntakeConstants.kV;
        config.Slot0.kA=IntakeConstants.kA;

        config.Slot0.kP = IntakeConstants.kP;
        config.Slot0.kI = IntakeConstants.kI;
        config.Slot0.kD = IntakeConstants.kD;

        config.MotorOutput.NeutralMode =NeutralModeValue.Coast;
        return config;
    }
}
