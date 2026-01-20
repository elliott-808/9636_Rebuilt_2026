// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.DigitalInput;


public class test_motor extends SubsystemBase {

    // this is how you instanciate a SparkMax. Device IDs are set in the rev hardware client.
    public SparkMax Motor1 = new SparkMax(12, MotorType.kBrushless);


    public void test_motorcommand() {

        // the "motor.set(speed:);" command sets the motors to a specific speed from -1 to 1. these are going in opposite direction because of the physical orientation of them on the robot.
        Motor1.set(-0.3);
       

        // if (coralSensor.get()) {

        //     coralTooketh = true;

        //     Motor1.set(0);
        //     coralMotorRight.set(0);
        //     return;

        // }

    }
}