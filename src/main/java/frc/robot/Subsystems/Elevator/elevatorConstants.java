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
}
