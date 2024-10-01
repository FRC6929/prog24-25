// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Align extends Command {
  private final Drivetrain m_drivetrain;
  private final Limelight m_limelight;
  private final Joystick m_joystick;
  
  /** Creates a new Align. */
  public Align(Drivetrain drivetrain, Limelight limelight, Joystick joystick) {
    m_drivetrain = drivetrain;
    m_limelight = limelight;
    m_joystick = joystick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain, limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {  
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");

//read values periodically
  double a = tx.getDouble(0.0);

//post to smart dashboard periodically
  SmartDashboard.putNumber("LimelightX", a);
   // x = -.2*m_joystick.getRawAxis(0);
    //y = .2*m_joystick.getRawAxis(1);
    //SmartDashboard.putNumber("yaw", m_limelight.yaw);
    //m_drivetrain.drive(x, y, -m_limelight.getdegRotationToTarget()/90*0.2, m_drivetrain.getHeading());
    if (a < 2 && a > -2)
      m_drivetrain.drive(.36*m_joystick.getRawAxis(0), -.36*m_joystick.getRawAxis(1), 0, 0);
    else
      m_drivetrain.drive(.36*m_joystick.getRawAxis(0), -.36*m_joystick.getRawAxis(1), ((a)/90)*0.8, 0);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
