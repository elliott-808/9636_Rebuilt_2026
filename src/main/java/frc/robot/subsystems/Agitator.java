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

private SparkMax leaderMotor = new SparkMax (3, MotorType.kBrushless);
private SparkMax followerMotor = new SparkMax (4, MotorType.kBrushless);

boolean motorsConfigured = false;


  public Hopper() {
if (!motorsConfigured){
  configureMotors();
  motorsConfigured = true;
}

  }
SparkMaxConfig leaderConfig = new SparkMaxConfig();


  private void configureMotors(){


leaderConfig.smartCurrentLimit(40);
leaderConfig.voltageCompensation(12.0);
leaderConfig.idleMode(IdleMode.kBrake);

leaderMotor.configure(leaderConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);

SparkMaxConfig followerConfig = new SparkMaxConfig();


followerConfig.follow(leaderMotor);
followerMotor.configure(followerConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters); 

  }

  public void agitatorForward() {
leaderMotor.set(0.3);
  }

  public void agitatorBackward() {
leaderMotor.set(-0.3);
  }

  public void agitatorStop() {
leaderMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
