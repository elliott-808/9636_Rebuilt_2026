// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLimitSwitch;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.LimitSwitchConfig.Behavior;
import com.revrobotics.spark.config.LimitSwitchConfig.Type;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */

  private SparkFlex climberMotor;
  private SparkFlexConfig climberConfig;
  private SparkLimitSwitch forwardLimitSwitch;
  //private SparkLimitSwitch reverseLimitSwitch;
  private RelativeEncoder encoder;

  public Climber() {
climberMotor = new SparkFlex(9, MotorType.kBrushless);
    forwardLimitSwitch = climberMotor.getForwardLimitSwitch();
   // reverseLimitSwitch = climberMotor.getReverseLimitSwitch();
    encoder = climberMotor.getEncoder();

    /*
     * Create a new SPARK MAX configuration object. This will store the
     * configuration parameters for the SPARK MAX that we will set below.
     */
    climberConfig = new SparkFlexConfig();

    // Set the idle mode to brake to stop immediately when reaching a limit
    climberConfig.idleMode(IdleMode.kBrake);

    // Enable limit switches to stop the motor when they are closed
    climberConfig.limitSwitch
        .forwardLimitSwitchType(Type.kNormallyOpen)
        .forwardLimitSwitchTriggerBehavior(Behavior.kStopMovingMotor);
     //   .reverseLimitSwitchType(Type.kNormallyOpen)
      //  .reverseLimitSwitchTriggerBehavior(Behavior.kStopMovingMotor);

    // Set the soft limits to stop the motor at -50 and 50 rotations
    climberConfig.softLimit
        .forwardSoftLimit(50)
        .forwardSoftLimitEnabled(true)
        .reverseSoftLimit(-50)
        .reverseSoftLimitEnabled(true);

    /*
     * Apply the configuration to the SPARK MAX.
     *
     * kResetSafeParameters is used to get the SPARK MAX to a known state. This
     * is useful in case the SPARK MAX is replaced.
     *
     * kPersistParameters is used to ensure the configuration is not lost when
     * the SPARK MAX loses power. This is useful for power cycles that may occur
     * mid-operation.
     */
    climberMotor.configure(climberConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

    // Reset the position to 0 to start within the range of the soft limits
    encoder.setPosition(0);

    // Initialize dashboard values
    SmartDashboard.setDefaultBoolean("Direction", true);

  
  }

  public void climberDown() {
climberMotor.set(-0.3);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
        SmartDashboard.putBoolean("Forward Limit Reached", forwardLimitSwitch.isPressed());
  //  SmartDashboard.putBoolean("Reverse Limit Reached", reverseLimitSwitch.isPressed());
    SmartDashboard.putNumber("Applied Output", climberMotor.getAppliedOutput());
    SmartDashboard.putNumber("Position", encoder.getPosition());
  }
}
