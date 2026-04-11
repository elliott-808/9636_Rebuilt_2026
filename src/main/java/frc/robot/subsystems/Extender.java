// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

// import javax.security.auth.callback.ConfirmationCallback;


import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.Agitator;


public class Extender extends SubsystemBase {
    private SparkFlex extendMotor = new SparkFlex(33, MotorType.kBrushless);
    // this is for the relative encoder that is built into the brushless motor. it gives the rotational position of it.
    //private RelativeEncoder encoder = leaderMotor.getEncoder();
// private AbsoluteEncoder encoder = Agitator.getEncoder();

public void extenderForward() {
    extendMotor.set(0.1);
}

public void extenderBackward() {
    extendMotor.set(-0.1);
}

public void extenderStop () {
    extendMotor.set(0);
}
// public Command extenderSpeedCommand(DoubleSupplier speed) {
//     return new FunctionalCommand(
//         () -> {},
//         () -> extenderSpeed(speed.getAsDouble()),
//         (interrupted)   -> extenderStop(),
//         () -> false, this);
// }

public Command extenderForwardCommand(){
return this.runOnce(
    () -> extenderForward());
}

public Command extenderBackwardCommand(){
return this.runOnce(
    () -> extenderBackward());
}

//     private PIDController pidController = new PIDController(
//         Constants.extenderkP,
//         Constants.extenderkI,
//         Constants.extenderkD
//     );

//     //Configures motors
//     boolean motorConfigured = false;

//     public Extender() {
//         if (!motorConfigured) {
//             configureMotor();
//             motorConfigured = true;
//         }
//     }
    

//     // private TrapezoidProfile.Constraints constraints;
//     private TrapezoidProfile.State goalState = new TrapezoidProfile.State();
//     private TrapezoidProfile.State currentState = new TrapezoidProfile.State();
//     private TrapezoidProfile profile = new TrapezoidProfile(new TrapezoidProfile.Constraints(50, 30));

//     private ExtenderPosition currentTarget = ExtenderPosition.IN;
//     private boolean isHomed = false;
//     public static double setpoint = 0.56;
//     SparkFlexConfig extendConfig = new SparkFlexConfig();
//     double currentPos;



    

//     // No clue what this is, but its there.
//     public enum ExtenderPosition {

//         IN(0),
//         OUT(10);

//         public final double positionInches;
        
//         ExtenderPosition(double positionInches) {
//             this.positionInches = positionInches;
//         }
//     }


//     /*  
//     Configures the primary and follower motors and sets a tolerance to
//     the PID for how close it can be/should be to the point. 
//     */
//     private void configureMotor() {

//         // Setting tolerance, why is this in configure motors???
//         pidController.setTolerance(0.5);

//         // Primary motor configuration
//         extendConfig.idleMode(IdleMode.kBrake);
//         extendConfig.smartCurrentLimit(40);
//         extendConfig.voltageCompensation(12.0);
//         extendMotor.configure(extendConfig, com.revrobotics.ResetMode.kResetSafeParameters, com.revrobotics.PersistMode.kPersistParameters);

        
//     }
   
//     // periodic() happens every 20ms, so anything that needs to be repeated constantly goes here.
//     @Override
//     public void periodic() {

//         // Gives a variable for the current positon of the elevator carrage
//         currentPos = encoder.getPosition() / Constants.countsPerInch;
  
//         // Handles the bottom of the elevator when position is below 0.5 and isHomed = false
//         if (!isHomed && currentPos < 2.0) {
//             handleReturnLimit();
//         }

        
//         // Calculate the next state and update current state
//         currentState = profile.calculate(Constants.extenderPIDLoopTime, currentState, goalState); // 20ms control loop

//         // handles bottom limit if position is close to zero
//         if (currentPos < 0.05) {
//             handleReturnLimit();
//         }

//         // Stops the motors if the height gets too far. may we all pray this works /\
//         if (getInches() > 13) {
//             stopMotor();
//         }

//         // Only runs the motors if homed and it knows where position zero is.
//         if (isHomed) {
//             // Read up on PID stuff, this is weird.
//             double pidOutput = pidController.calculate(getInches(), currentState.position);
//             double ff = calculateFeedForward(currentState);
            
//             // clamps the output power to be between -0.5 and 0.5 so no one dies
//             double outputPower = pidOutput + ff;
            
//             // takes the resulting output power and powers the motor
//             extendMotor.set(outputPower);

//         }

//         // Update SmartDashboard
//         updateTelemetry();
//     }

//     /* 
//     Stops the motor movement, sets isHomed to true, setpoint to 0,
//     defines the current and goal state, and resets the PID
//     */
//     private void handleReturnLimit() {
//         stopMotor();
//         isHomed = true;
//         setpoint = 0.56;  
//         currentState = new TrapezoidProfile.State(0, 0);
//         goalState = new TrapezoidProfile.State(0, 0);
//         pidController.reset();
//     }

//     // Take a WILD guess as to what this does.
//     public void stopMotor() {
//         extendMotor.set(0);
//         pidController.reset();
//     }

//     // Checks if the elevator is within a small tolerance of the target height
//     public boolean isExtendedTo(double targetExtensionInches) {
//         return pidController.atSetpoint() && 
//                Math.abs(getInches() - targetExtensionInches) < 1;
//     }
    
//     // 0.01 is kS, 0.1 is kG, and state.velocity is kV
//     // kS (static friction), kG (gravity), kV (velocity),
//     private double calculateFeedForward(TrapezoidProfile.State state) {
//         return 0 * Math.signum(state.velocity) +
//                Constants.extenderkS +
//                Constants.extenderkG * state.velocity;
//     }

//     // This takes in a value (inches) and changes the setpoint that is to be the goalState
//     public void setPositionInches(double inches) {
//         System.out.println("Setting Extender Setpoint: " + inches);
//         // Gives warning if elevator is not homed to console
//         if (!isHomed && inches > 0) {
//             System.out.println("Warning: Extender not homed! Home first before moving to positions.");
//             return;
//         }

//         // Limits the possible input of inches
//         setpoint = MathUtil.clamp(
//             inches,
//             0,
//             Constants.extenderMax // Max Height
//         );
        
//         // Update goal state for motion profile
//         goalState = new TrapezoidProfile.State(setpoint, 0.1);
//         System.out.println("Updated goal state: " + goalState);
//     }

//     // gets the current height of the carrage.
//     public double getInches() {
//         return encoder.getPosition() / Constants.countsPerInch;
//     }
// public Command extenderMoveOut() {
//     // return Commands.runEnd(
    
//     //     () -> setPositionInches(Constants.extendOutValue),
//     //     null,
//     //     this);
//        return new FunctionalCommand(
//         () -> {},
//         () -> setpoint = Constants.extendOutValue,
//         (interrupted)   -> setpoint = Constants.extendOutValue,
//         () -> false, this).andThen(
//             new FunctionalCommand(
//         () -> {},
//         () -> setPositionInches(Constants.extendOutValue),
//         (interrupted)   -> setPositionInches(Constants.extendOutValue),
//         () -> false, this));
// }

// public Command extenderMoveIn() {
// //     return Commands.runEnd(
    
// //         () -> setPositionInches(Constants.extendInValue),
// //         null,
// //         this);
//     return new FunctionalCommand(
//         () -> {},
//         () -> setpoint = Constants.extendInValue,
//         (interrupted)   -> setpoint = Constants.extendInValue,
//         () -> false, this).andThen(
//             new FunctionalCommand(
//         () -> {},
//         () -> setPositionInches(Constants.extendInValue),
//         (interrupted)   -> setPositionInches(Constants.extendInValue),
//         () -> false, this));
// }
//     // gives smartdashboard some numbers for "easy" debugging
//     private void updateTelemetry() {
//         SmartDashboard.putNumber("Extension", getInches());
//         SmartDashboard.putNumber("Extender Target", setpoint);
//         SmartDashboard.putBoolean("Extender Homed", isHomed);
//         SmartDashboard.putString("Extender State", currentTarget.toString());
//         SmartDashboard.putNumber("Extender Current", extendMotor.getOutputCurrent());
//         SmartDashboard.putNumber("Extender Velocity", currentState.velocity);
//  System.out.println("ENC="+encoder.getPosition()*1.0+"   setpoint= "+setpoint);
//     }


   
  }
