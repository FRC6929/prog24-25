// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autonome.AutoIntake;
import frc.robot.commands.Autonome.AutoShoot;
import frc.robot.commands.Autonome.AutoStrafe;
import frc.robot.commands.Autonome.AutoTurn;
import frc.robot.commands.Autonome.Automove;
import frc.robot.commands.Autonome.Automoveslow;
import frc.robot.commands.Autonome.Delay;
import frc.robot.commands.Align;
import frc.robot.commands.ChurrosCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.GobackabitCommand;
import frc.robot.commands.GrappinCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.Switchlacam;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Churros;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Grappin;
import frc.robot.subsystems.Hooter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;

import java.util.OptionalInt;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Joystick m_joystick = new Joystick(0);
  private final Joystick m_pilote= new Joystick(2);
  private final XboxController m_copilote = new XboxController(1);
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Grappin m_grappin = new Grappin();
  private final Hooter m_shooter = new Hooter();
  private final Intake m_intake = new Intake();
  private final Churros m_churros = new Churros();
  private final Camera m_camera = new Camera();
  private final Limelight m_limelight = new Limelight(NetworkTableInstance.getDefault().getTable("limelight"));


  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /**They say that the container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drivetrain.setDefaultCommand(new DriveCommand(m_drivetrain, m_pilote));
    m_churros.setDefaultCommand(new ChurrosCommand(m_churros, m_copilote));
    // Configure the trigger bindings
    configureBindings();
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
    JoystickButton goback = new JoystickButton(m_pilote, 2);
    JoystickButton align = new JoystickButton(m_pilote, 5);
    JoystickButton swithlacam = new JoystickButton(m_pilote, 6);
    JoystickButton grappin = new JoystickButton(m_copilote, 2);
    JoystickButton grappout = new JoystickButton(m_copilote, 3);
    JoystickButton churros = new JoystickButton(m_copilote, 7);
    JoystickButton intakein = new JoystickButton(m_copilote, 6);
    JoystickButton intakeout = new JoystickButton(m_copilote, 5);
    JoystickButton Shooterlow = new JoystickButton(m_copilote, 1);
    JoystickButton Shooterhigh = new JoystickButton(m_copilote, 4);
    
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

   // align.onTrue(new Align(m_drivetrain, m_limelight, m_pilote)).onFalse(new DriveCommand(m_drivetrain, m_pilote));
   //goback.onFalse(new GobackabitCommand(m_drivetrain, 1));
    churros.whileTrue(new ChurrosCommand(m_churros, m_copilote));
    grappin.whileTrue(new GrappinCommand(m_grappin,0.2));
    grappout.whileTrue(new GrappinCommand(m_grappin, -0.1));
    Shooterlow.whileTrue(( new ShooterCommand(m_shooter,-0.25)));// à changer
    Shooterhigh.whileTrue(new ShooterCommand(m_shooter, -0.3));// à changer
    align.whileTrue(new Align(m_drivetrain, m_limelight, m_pilote));
    intakein.whileTrue(new IntakeCommand(m_intake, true,m_copilote));
    swithlacam.whileTrue(new Switchlacam(m_camera, m_joystick));
    //true in / false out
    intakeout.whileTrue(new IntakeCommand(m_intake, false,m_copilote));  
    SmartDashboard.putBoolean("shooter", m_copilote.getRawButton(4));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  }
  
private static OptionalInt x = DriverStation.getLocation();
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    m_drivetrain.reset_encoders();
    String y = Robot.m_autoSelected;
    Command m_auto = new Delay(100);
    if(y == "Default"){//default
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(750));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
    }else if(y == "Bleu Centre-Gauche"){
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new Delay(200));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 19.5));
      m_auto = m_auto.andThen(new Delay(250));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
      m_auto = m_auto.andThen(new Automoveslow(m_drivetrain, -24));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -0.1));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 2));
      m_auto = m_auto.andThen(new AutoTurn(m_drivetrain, 57));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 25));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(200));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
      m_auto = m_auto.andThen(new Delay(300));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, -24));
      m_auto = m_auto.andThen(new AutoTurn(m_drivetrain, -55));
      m_auto = m_auto.andThen(new Automoveslow(m_drivetrain, -2));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
    }else if(y == "Rouge Centre-Droite"){
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new Delay(200));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 20));
      m_auto = m_auto.andThen(new Delay(200));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
      m_auto = m_auto.andThen(new AutoTurn(m_drivetrain, 10));
      m_auto = m_auto.andThen(new Automoveslow(m_drivetrain, -25));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 1));
      m_auto = m_auto.andThen(new AutoTurn(m_drivetrain, -56.5));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 27));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(300));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
      m_auto = m_auto.andThen(new Delay(300));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, -19.5));
      m_auto = m_auto.andThen(new AutoTurn(m_drivetrain, 61));
      m_auto = m_auto.andThen(new Automoveslow(m_drivetrain, -9));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
    }else if(y== "Rouge Droite" || y== "Bleu Droite"){
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1500));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(750));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 2));
      m_auto = m_auto.andThen(new AutoTurn(m_drivetrain, 65.5));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 26.5));
      m_auto = m_auto.andThen(new Delay(200));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, -26));
      m_auto = m_auto.andThen(new AutoTurn(m_drivetrain, -63.5));
      m_auto = m_auto.andThen(new Automoveslow(m_drivetrain, -2));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));      
    }else if(y == "Rouge Centre"||y== "Bleu Centre"){     
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new Delay(200));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 20));
      m_auto = m_auto.andThen(new Delay(250));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
      m_auto = m_auto.andThen(new Automoveslow(m_drivetrain, -24));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
    }else if(y == "Rouge Gauche" || y== "Bleu Gauche"){
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1250));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(750));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 3));
      m_auto = m_auto.andThen(new AutoTurn(m_drivetrain, -71));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, 27));
      m_auto = m_auto.andThen(new Delay(200));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
      m_auto = m_auto.andThen(new Automove(m_drivetrain, -21));
      m_auto = m_auto.andThen(new AutoTurn(m_drivetrain, 69));
      m_auto = m_auto.andThen(new Automoveslow(m_drivetrain, -10));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, -1));
      m_auto = m_auto.andThen(new Delay(1000));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 1));
      m_auto = m_auto.andThen(new Delay(500));
      m_auto = m_auto.andThen(new AutoShoot(m_shooter, 0));
      m_auto = m_auto.andThen(new AutoIntake(m_intake, 0));
    }
    
   /*  m_auto = m_auto.andThen(new Automove(m_drivetrain, -500));
    m_auto = m_auto.andThen(new AutoStrafe(m_drivetrain, -500));
    m_auto = m_auto.andThen(new AutoIntake(m_intake));
    m_auto = m_auto.andThen(new AutoShoot(m_shooter));*/
    //They say that Water Temple is way of the Hero
    //They say an example command will be run in autonomous
    return m_auto;
  }
}
