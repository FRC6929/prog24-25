// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ConsShooter;
import frc.robot.commands.IntakeCommand;

public class Hooter extends SubsystemBase {
  private final CANSparkFlex motor_r = new CANSparkFlex(ConsShooter.Motor_VortexShooter1, MotorType.kBrushless);
  private final CANSparkFlex neovortex = new CANSparkFlex(ConsShooter.Motor_VortexShooter2, MotorType.kBrushless);
  double yspeed;
  Intake m_intake;
  /** Creates a new Hooter. */
  public Hooter() {}
  public void Shoot(double y){
    SmartDashboard.putNumber("shooterspeed", y);
    yspeed = y;
    neovortex.set(yspeed);
    motor_r.set(-yspeed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
