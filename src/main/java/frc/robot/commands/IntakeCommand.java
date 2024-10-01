// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends Command {
  private final Intake m_intake;
  private boolean m_inout;//true in / false out
  private XboxController m_controller;
  private double a;
  boolean b;

  /** Creates a new IntakeCommand. */
  public IntakeCommand(Intake intake, boolean inout, XboxController x) {
    m_intake = intake;
    m_inout = inout;
    m_controller = x;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    b =false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Intake.LimitSwitch()){
      b = true;
    }
    if(!m_inout){
      m_intake.roulette(-0.5);
    }else{
      if(b == false){
        m_intake.roulette(.75);
      }else if(SmartDashboard.getNumber("shooterspeed", 0) != 0){ 
        m_intake.roulette(1);
      }else{
        m_intake.roulette(0);
      }
    }
    
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.roulette(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
