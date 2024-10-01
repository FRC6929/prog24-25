// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Churros;

public class ChurrosCommand extends Command {
  private final Churros m_churros;
  private final XboxController m_joystick;
  double x;
  /** Creates a new ChurrosCommand. */
  public ChurrosCommand(Churros churros,XboxController joy) {
    m_churros = churros;
    m_joystick=joy;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(churros);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_joystick.getPOV()== 0){
      x= .13;
    }else if(m_joystick.getPOV() == 180){
      x= -.13;
    }else{ 
      x= 0;
    }
    m_churros.activate(x);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
