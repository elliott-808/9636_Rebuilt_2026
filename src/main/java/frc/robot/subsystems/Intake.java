// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

//IDS TO BE CHANGED LATER

private SparkFlex leaderMotor = new SparkFlex (15, MotorType.kBrushless);
//private SparkFlex followerMotor = new SparkFlex (2, MotorType.kBrushless);

boolean motorsConfigured = false;


  public Intake() {
if (!motorsConfigured){
  configureMotors();
  motorsConfigured = true;
}

  }
SparkFlexConfig leaderConfig = new SparkFlexConfig();


  private void configureMotors(){


leaderConfig.smartCurrentLimit(40);
leaderConfig.voltageCompensation(12.0);
leaderConfig.idleMode(IdleMode.kBrake);

leaderMotor.configure(leaderConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);

//SparkFlexConfig followerConfig = new SparkFlexConfig();


//followerConfig.follow(leaderMotor);
//followerMotor.configure(followerConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters); 

  }

  public void intakeIn() {
leaderMotor.set(0.3);
  }

  public void intakeOut() {
leaderMotor.set(-0.3);
  }

  public void intakeStop() {
leaderMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
