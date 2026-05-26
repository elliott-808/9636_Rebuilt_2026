// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AgitatorBackward;
import frc.robot.commands.AgitatorForward;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.FarShooterCammand;
import frc.robot.commands.CloseShooterCommand;
import frc.robot.subsystems.Agitator;
import frc.robot.subsystems.Extender;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import swervelib.SwerveInputStream;

import java.io.File;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Agitator agitator = new Agitator();
  // private final Climber climber = new Climber();
  private final Extender extender = new Extender();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();


  // Replace with CommandPS4Controller or CommandJoystick if needed
  final CommandPS4Controller driverPS4 = new CommandPS4Controller(0);
  final CommandPS4Controller operatorPS4 = new CommandPS4Controller(1);

  public static final SwerveSubsystem       drivebase  = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                                "swerve"));
    private final SendableChooser<Command> autoChooser = new SendableChooser<>();


  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
                                                                () -> driverPS4.getLeftY() * -1,
                                                                () -> driverPS4.getLeftX() * -1)
                                                            .withControllerRotationAxis(driverPS4::getRightX)
                                                            .deadband(OperatorConstants.DEADBAND)
                                                            .scaleTranslation(0.8)
                                                            .allianceRelativeControl(true);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
SmartDashboard.putBoolean("Extender UP", true);
SmartDashboard.putBoolean("Extender DOWN", false);

    // Configure the trigger bindings
    configureBindings();
    DriverStation.silenceJoystickConnectionWarning(true);
    //Put the autoChooser on the SmartDashboard
    SmartDashboard.putData("Auto Chooser", autoChooser);

    NamedCommands.registerCommand("Shoot From Hub", shooter.runCloseShooterCommand().withTimeout(5.0));
        NamedCommands.registerCommand("test", Commands.print("I EXIST"));



  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

        Command driveFieldOrientedAnglularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);
      drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

      operatorPS4.R1().whileTrue(new AgitatorForward(agitator));
      operatorPS4.R2().whileTrue(new AgitatorBackward(agitator));
      operatorPS4.L1().onTrue(extender.extenderForwardCommand().withTimeout(1.25));
      operatorPS4.L2().onTrue(extender.extenderBackwardCommand().withTimeout(1.25));
      // operatorPS4.L1().whileTrue(extender.extenderSpeedCommand(() -> -0.1).
      //   alongWith(Commands.waitSeconds(0.25).
      //   andThen(extender.extenderSpeedCommand(() -> 0))));
      // operatorPS4.L2().whileTrue(extender.extenderSpeedCommand(() -> 0.1)
      // .alongWith(Commands.waitSeconds(0.25).
      // andThen(extender.extenderSpeedCommand(() -> 0))));

      operatorPS4.triangle().whileTrue(new IntakeIn(intake));
      operatorPS4.circle().whileTrue(new IntakeOut(intake));
      operatorPS4.povDown().whileTrue(shooter.runFarShooterCommand());
      operatorPS4.povUp().whileTrue(shooter.runCloseShooterCommand());
      // operatorPS4.povUp().onTrue(new ClimberUp(climber));

  }

  
public Command getAutonomousCommand()
  {
    // Pass in the selected auto from the SmartDashboard as our desired autnomous commmand 
  //   // An example command will be run in autonomous
    return drivebase.getAutonomousCommand("Drive and Shooting Auto");
  // }
  }

    public void setMotorBrake(boolean brake){
    drivebase.setMotorBrake(brake);
  }

}