// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Autonome.Delay;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class Switchlacam extends Command {
  boolean a = false;
  /** Creates a new Switchlacam. */
  UsbCamera camera100;
  VideoSink server;
  private final Joystick m_joystick;
  public Switchlacam(Camera camera, Joystick joystick) {
    m_joystick = joystick;
    addRequirements(camera);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {if(!a){
    camera100 = CameraServer.startAutomaticCapture(0);

    camera100.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    camera100.setFPS(12);
    a = true;
  }else{
    end(true);
  }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  
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
