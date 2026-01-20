// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.test_motor;

/* 
this is the beginning of the COMMANDS wow so cool
if you want to write a command, just replace the variables that make it up.
for example, replace "CoralSubsystem" with the name of the subsystem that is being moved.
if you hover over anything or press Command+Left Click, you can see where any term is called anywhere in the project.
*/
public class test_motorcommand extends Command {
    public final test_motor intakeIn;

    // This defines what subsystem is being changed or moved.
    public test_motorcommand(test_motor intakeCommand) {
        
        intakeIn = intakeCommand;
        addRequirements(intakeCommand);

    }

    // Hover our cursor over "initialize" to see what it does. do the same for "execute" "isFinished" and "end"
    // This says that whatever is in "initialize" will happen ONCE at the BEGINNING of the command when its called.
    @Override
    public void initialize() {

        // This version of "test_motorcommand" is the actual method within the "CoralSubsystem.java" file.
        intakeIn.test_motorcommand();
        
    }

    @Override
    public void execute() {

        // this will just print stuff over and over when the command is active.
        System.out.println("CoralIntakeIn activated");


    }

  
}