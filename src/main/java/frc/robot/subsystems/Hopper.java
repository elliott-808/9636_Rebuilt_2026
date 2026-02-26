// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.DigitalInput;

public class Hopper extends SubsystemBase {
  /** Creates a new Hopper. */

//IDS TO BE CHANGED LATER

private SparkMax primaryMotor = new SparkMax (3, MotorType.kBrushless);
private Sit parkMax followerMotor = new SparkMax (4, MotorType.kBrushless);

boolean motorsConfigured = false;


  public Hopper() {
if (!motorsConfigured){
  configureMotors();
  motorsConfigured = true;
}

  }
SparkMaxConfig primaryConfig = new SparkMaxConfig();


  @SuppressWarnings("removal")
  private void configureMotors(){


primaryConfig.smartCurrentLimit(40);
primaryConfig.voltageCompensation(12.0);
primaryConfig.idleMode(IdleMode.kBrake);

primaryMotor.configure(primaryConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

SparkMaxConfig followerConfig = new SparkMaxConfig();


followerMotor.follow(primaryMotor);
followerMotor.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters); 

  }

  public void agitatorForward() {
primaryMotor.set(0.3);
  }

  public void agitatorBackward() {
primaryMotor.set(-0.3);
  }

  public void agitatorStop() {
primaryMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
