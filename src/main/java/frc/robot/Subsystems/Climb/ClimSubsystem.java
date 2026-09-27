package frc.robot.Subsystems.Climb;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.lib.Subsystem;

public class ClimSubsystem extends Subsystem{
    Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    private ClimSubsystem instance;

    private state currentState=state.idle;
    public enum state {
        idle(0),
        climbing(1);

        public final int stateNum;

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
                    () -> climbingIMethods(),
                    () -> climbingPMethods(),
                    () -> climbingEMethods()
            }
    };
    private void climbingIMethods(){
        solenoid.set(true);
    }
    private void climbingPMethods(){
        solenoid.set(true);
    }
    private void climbingEMethods(){
        solenoid.set(false);
    }
    

}
