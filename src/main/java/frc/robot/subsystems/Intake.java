// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ConsIntake;

public class Intake extends SubsystemBase {
  private final CANSparkMax roulette = new CANSparkMax(ConsIntake.Motor_Intake, MotorType.kBrushless);
  private final CANSparkMax lowerroulette = new CANSparkMax(ConsIntake.Motor_lowIntake, MotorType.kBrushless);
  static DigitalInput notestopper = new DigitalInput(0);
  static AnalogInput infrared = new AnalogInput(3);
  /** Creates a new Intake. */
  public Intake() {}
  public static boolean LimitSwitch(){
    boolean a = !notestopper.get();
    return a;
  }
  public static int Infrared(){
    boolean a = false;
    int b = infrared.getValue();
    if(infrared.getValue()> 0){}
    return b;
  }
  public void roulette(double a){
      roulette.set(a);
      lowerroulette.set(-a);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
