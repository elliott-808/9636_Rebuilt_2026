// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkRelativeEncoder;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Configs {
    private static final double nominalVoltage = 12.0;

  public static final class Shooter {
    public static final SparkFlexConfig flywheelConfig = new SparkFlexConfig();
    public static final SparkFlexConfig flywheelFollowerConfig = new SparkFlexConfig();
    public static final SparkMaxConfig feederConfig = new SparkMaxConfig();

    static {
      // Configure basic setting of the flywheel motors
      flywheelConfig
        .inverted(true)
        .idleMode(IdleMode.kCoast)
        .closedLoopRampRate(1.0)
        .openLoopRampRate(1.0)
        .smartCurrentLimit(80);

      /*
       * Configure the closed loop controller. We want to make sure we set the
       * feedback sensor as the primary encoder.
       */
      flywheelConfig
        .closedLoop

          // Set PID values for position control
          .p(0.0002)
          .i(0.0)
          .d(0.0)
          .outputRange(-1, 1);

      flywheelConfig.closedLoop
        .maxMotion
          // Set MAXMotion parameters for MAXMotion Velocity control
          .cruiseVelocity(5000)
          .maxAcceleration(10000)
          .allowedProfileError(1.0);

      // Constants.NeoMotorConstants.kVortexKv is in rpm/V. feedforward.kV is in V/rpm sort we take
      // the reciprocol.
      flywheelConfig.closedLoop
        .feedForward.kV(nominalVoltage / Constants.kVortexKv);

      // Configure the follower flywheel motor to follow the main flywheel motor
      flywheelFollowerConfig.apply(flywheelConfig)
        .follow(Constants.kFlywheelMotorCanId, true);

      // Configure basic setting of the feeder motor
      feederConfig
        .inverted(true)
        .idleMode(IdleMode.kCoast)
        .openLoopRampRate(1.0)
        .smartCurrentLimit(60);
    }
  }

}