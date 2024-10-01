// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ConsHookshot;

public class Grappin extends SubsystemBase {
  /** Creates a new Grappin. */
  private final CANSparkMax m_Up = new CANSparkMax(ConsHookshot.Motor_Hookshot_U,MotorType.kBrushless);
  private final CANSparkMax m_Down = new CANSparkMax(ConsHookshot.Motor_Hookshot_D, MotorType.kBrushless);
  private final Solenoid m_lock = new Solenoid(PneumaticsModuleType.CTREPCM, 0);


  public Grappin() {
    m_Down.setInverted(false);
    m_Up.setInverted(false);

  }

public void Hookshot(double a) {
  m_Down.set(a);
  m_Up.set(a);
  if(a>0|| a<0){
    m_lock.set(true);
  }else if(a == 0){
    m_lock.set(false);
  }
  SmartDashboard.putBoolean("m_lock", m_lock.get());
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
//They say that Hookshot Upgrade is in Robotique