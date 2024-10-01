// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonome;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class AutoStrafe extends Command {
  private Drivetrain m_drivetrain;
  double dist;
  private double encodeur_start;
  private boolean m_finished;
  double speed;
  /** Creates a new AutoStrafe. */
  public AutoStrafe(Drivetrain drivetrain, double a) {
    m_drivetrain = drivetrain;
    dist = -a;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encodeur_start = m_drivetrain.get_encoder();

    if(this.dist > 0){
      speed = 0.1;
    }
    else if(this.dist < 0){
      speed = -0.1;
    }
    else{
      end(true);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.drive(speed,0, 0, 0);
    
    if(Math.abs(m_drivetrain.get_encoder() - encodeur_start) >= Math.abs(dist/6)){
      end(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0, 0, 0);
    this.m_finished = true;
    this.cancel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.m_finished;
  }
}
