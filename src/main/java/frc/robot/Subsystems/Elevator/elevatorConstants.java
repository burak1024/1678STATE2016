package frc.robot.Subsystems.Elevator;

import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;

public class elevatorConstants {
    public static InterpolatingDoubleTreeMap positionMap(){
        InterpolatingDoubleTreeMap treeMap = new InterpolatingDoubleTreeMap();
        treeMap.put(0.0, 0.0);
        treeMap.put(1.0, 1.0);
        treeMap.put(4.0, 4.0);
        return treeMap;
    }
public static int ELEVATOR_MOTOR1_ID=8;
public static int ELEVATOR_MOTOR2_ID=9;
public static final double STATOR_CURRENT_LIMIT = 80.0;
    public static final double CRUISE_VELOCITY = Double.POSITIVE_INFINITY;
    public static final double ACCELERATION = Double.POSITIVE_INFINITY;
    public static final double JERK= 0;
    public static final double kS =0.25;
    public static final double kV = 0.119;
    public static final double kA = 0.01;
    public static final double kP=0.01;
    public static final double kI=0.01;
    public static final double kD = 0.01;
}
