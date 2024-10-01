// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight.*/
  private NetworkTable m_table;
  private String m_tableName;
  public double yaw;

  public double getdegRotationToTarget() {
  

    return (0);
  }
  public Limelight(NetworkTable table) {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
 