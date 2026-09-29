
package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.Subsystems.CommandSwerveDrivetrain;
import frc.robot.Subsystems.SuperStructure;
import frc.robot.Subsystems.generated.TunerConstants;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond);
  private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond);
  public final CommandPS4Controller driver = new CommandPS4Controller(0);
  public final CommandPS4Controller driver2 = new CommandPS4Controller(1);
  public final CommandSwerveDrivetrain drivetrain = CommandSwerveDrivetrain.getSwerve();
  private final Field2d field = new Field2d();

  public Robot() {
    drivetrain.setDefaultCommand(
        drivetrain.applyRequest(() -> new SwerveRequest.FieldCentric()
            .withVelocityX(-driver.getLeftY() * MaxSpeed)
            .withVelocityY(-driver.getLeftX() * MaxSpeed)
            .withRotationalRate(MathUtil.applyDeadband(-driver.getRightX(), 0.5) * MaxAngularRate)));

  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SuperStructure.getInstance().periodic();
    field.setRobotPose(drivetrain.getState().Pose);
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}