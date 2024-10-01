// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonome;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class AutoTurn extends Command {
  private final Drivetrain m_drivetrain;
  boolean m_finished = false;
  double m_deltaangle;
  double currentangle;
  double oldangle;
  double heading;
  /** Creates a new AutoTurn. */
  public AutoTurn(Drivetrain drivetrain, double angle) {
    m_drivetrain = drivetrain;
    m_deltaangle = angle;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    oldangle = m_drivetrain.getHeading();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentangle =m_drivetrain.getHeading();
    heading = currentangle - (oldangle + m_deltaangle);
    if (heading  > 180 || heading < 180) {
      heading = Math.IEEEremainder(heading, 360); 
    }
    m_drivetrain.drive(0,0,heading/180, currentangle);
    if(Math.abs(heading) <=10){
      end(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0, 0, currentangle);
    this.m_finished = true;
    this.cancel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_finished;
  }
}
