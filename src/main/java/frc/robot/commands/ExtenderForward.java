// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.Extender;
// import frc.robot.Constants;

// // fuck elevators, all my homies hate elevators.
// public class ExtenderForward extends Command {
//     public final Extender extenderGoForward;

//     public ExtenderForward(Extender extenderCommand) {

//         extenderGoForward = extenderCommand;
//         addRequirements(extenderCommand);
//     }

//     @Override
//     public void initialize() {
//         // At the beginning we set te height to whatever height we have in the Constants.java file
//         extenderGoForward.setPositionInches(Constants.extendOutValue);

//     }

//     @Override
//     public void execute() {

//         System.out.println("Extender moving forward...");

//     }

//     @Override
//     public boolean isFinished() {
//         return extenderGoForward.isExtendedTo(Constants.extendOutValue);
//         //here, we dont have "return false;" but instead wether or not the elevator is at the height.
//     }
  
//     @Override
//     public void end(boolean interrupted) {
      
//     }
// }