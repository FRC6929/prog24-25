// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.commands.Align;

  public class Camera extends SubsystemBase {
    UsbCamera camera180;
    NetworkTableEntry cameraSelection;


  /** Creates a new Camera. */
  public Camera() {
   // camera180 = CameraServer.startAutomaticCapture(0);

     //   cameraSelection = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");

  }

  //They say that biggoron crafts a Light Arrow
    // This method will be called once per scheduler run
  
  @Override
  public void periodic() {

}
  }
