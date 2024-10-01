// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveCommand extends Command {
  private Drivetrain m_drivetrain;
  private Joystick m_joystick;
  double x;
  double y;
  double z;
  /** Creates a new DriveCommand. */
  public DriveCommand(Drivetrain drivetrain, Joystick joystick) {
    m_drivetrain = drivetrain;
    m_joystick = joystick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    //BOUTON trigger droite (VITESSE "LENTE")
    if(m_joystick.getRawButton(8)||m_joystick.getRawButton(7)){
      x= .18*m_joystick.getRawAxis(0);
      y= -.18*m_joystick.getRawAxis(1);
      z= .18*m_joystick.getRawAxis(2);
    }
    // AUCUN BOUTON (VITESSE "NORMALE")
    else {
    x= .45*m_joystick.getRawAxis(0);
    y= -.74*m_joystick.getRawAxis(1);
    z = .325*m_joystick.getRawAxis(2);
    }
     m_drivetrain.drive(x,y,z,m_drivetrain.getHeading());
  }
//Laurent c un topîkoeur ;)
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
