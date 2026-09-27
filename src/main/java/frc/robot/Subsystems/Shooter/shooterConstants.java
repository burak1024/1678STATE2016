package frc.robot.Subsystems.Shooter;

import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;

public class shooterConstants {
    public static InterpolatingDoubleTreeMap shooterVOLTMap(){
        InterpolatingDoubleTreeMap treeMap  = new InterpolatingDoubleTreeMap();
        treeMap.put(0.0, 0.0);
        treeMap.put(1.0, 1.5);
        treeMap.put(4.0, 12.0);
        return treeMap;
    }
}
