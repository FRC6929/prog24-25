// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.SPI;
public class Drivetrain extends SubsystemBase {
  private final CANSparkMax m_drive_fl = new CANSparkMax(Constants.ConsDrivetrain.Motor_LF, MotorType.kBrushless);
  private final CANSparkMax m_drive_fr = new CANSparkMax(Constants.ConsDrivetrain.Motor_RF, MotorType.kBrushless);
  private final CANSparkMax m_drive_bl = new CANSparkMax(Constants.ConsDrivetrain.Motor_LB, MotorType.kBrushless);
  private final CANSparkMax m_drive_br = new CANSparkMax(Constants.ConsDrivetrain.Motor_RB, MotorType.kBrushless);
  double yspeed = 0;
  double xspeed = 0;
  double zspeed = 0;
 // private final MecanumDrive m_MecanumDrive = new MecanumDrive(m_drive_fl, m_drive_bl, m_drive_fr, m_drive_br );
  private final AHRS ahrs = new AHRS(SPI.Port.kMXP);
  public Drivetrain(){

      resetNavx();
      m_drive_fr.setInverted(true);
      m_drive_br.setInverted(true);
      m_drive_bl.setInverted(false);
      m_drive_fl.setInverted(false);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double get_encoder(){
      return (-m_drive_fl.getEncoder().getPosition()-m_drive_bl.getEncoder().getPosition()+m_drive_fr.getEncoder().getPosition()+m_drive_br.getEncoder().getPosition())/4;
//THEY SAY THAT MECANUM_DRIVE IS ON THE PATH OF MAGIC;
  }

  public void reset_encoders(){
    m_drive_fr.getEncoder().setPosition(0);
    m_drive_fl.getEncoder().setPosition(0);
    m_drive_br.getEncoder().setPosition(0);
    m_drive_bl.getEncoder().setPosition(0);
  }

  public double getHeading() {
    double heading = -ahrs.getYaw();
    if (heading  > 180 || heading < 180) {
      heading = Math.IEEEremainder(heading, 360);
      
    }
    return heading;
    }

  public void resetNavx(){
    ahrs.reset();
  }
  public void drive(double x, double y, double z, double angle) {
    if(Math.abs(x) < 0.05){
      x =0;
    }
    if(Math.abs(y) < 0.05){
      y =0;
    }
    if(Math.abs(z) < 0.05){
      z =0;
    }
    if(x == 0){
      xspeed =0;
    }else if(Math.abs(xspeed)< Math.abs(x)){
      xspeed += .015*Math.signum(x);
    }else{
      xspeed = x;
    }
    if(y == 0){
      yspeed = 0;
    }else if(Math.abs(yspeed)< Math.abs(y)){
      yspeed += .015*Math.signum(y);
    }else{
      yspeed = y;
    }
    if(z == 0){
      zspeed = 0;
    }else if(Math.abs(zspeed)< Math.abs(z)){
      zspeed += .015*Math.signum(z);
    }else{
      zspeed = z;
    }
    m_drive_fl.set(yspeed + xspeed + zspeed);
    m_drive_bl.set(yspeed - xspeed + zspeed);
    m_drive_fr.set(yspeed - xspeed - zspeed);
    m_drive_br.set(yspeed + xspeed - zspeed);
     // m_MecanumDrive.driveCartesian(z,x,y);
      SmartDashboard.putBoolean("noteStopper",Intake.LimitSwitch());
      SmartDashboard.putNumber("x", x);
      SmartDashboard.putNumber("y", y);
      SmartDashboard.putNumber("z", z);
      SmartDashboard.putNumber("angle", angle);

  }
  public void drive2(double x, double y, double z, double angle){
    SmartDashboard.putBoolean("noteStopper",Intake.LimitSwitch());
    SmartDashboard.putNumber("pitch", ahrs.getPitch());
    SmartDashboard.putNumber("yaw", ahrs.getYaw());
    SmartDashboard.putNumber("roll", ahrs.getRoll());
    if(Math.abs(x)<0.05) x= 0;
    if(Math.abs(y)<0.05) y= 0;
    if(Math.abs(z)<0.05) z= 0;
    if(Math.abs(y)>0&& x== 0 && z == 0){
      m_drive_bl.set(y);
      m_drive_br.set(y);
      m_drive_fl.set(y);
      m_drive_fr.set(y);
    }
    else if(Math.abs(x)> 0 && y == 0 && z == 0){
      m_drive_bl.set(-x);
      m_drive_br.set(x);
      m_drive_fl.set(x);
      m_drive_fr.set(-x);
    }
    else if(Math.abs(z)>0 && y == 0 && x == 0){
      m_drive_bl.set(z);
      m_drive_br.set(-z);
      m_drive_fl.set(z);
      m_drive_fr.set(-z);
    }
    else if(Math.abs(y)>0&& Math.abs(x)> 0 && z == 0){
      m_drive_bl.set((y-x)/2);
      m_drive_br.set((y+x)/2);
      m_drive_fl.set((y+x)/2);
      m_drive_fr.set((y-x)/2);
    }

    //FAIT PAR ALEXIS (A VÉRIFIER);
    //Y manque quand (x positif) + (z positif);   
    else if(Math.abs(y)>0 && Math.abs(z)>0 && x == 0){
      m_drive_bl.set((y+z)/2);
      m_drive_br.set((y-z)/2);
      m_drive_fl.set((y+z)/2);
      m_drive_fr.set((y-z)/2);
    }
    else if(Math.abs(x)>0 && Math.abs(z)>0 && x == 0){
      m_drive_bl.set((-x+z)/2);
      m_drive_br.set((x-z)/2);
      m_drive_fl.set((x+z)/2);
      m_drive_fr.set((-x-z)/2);
    }
    else if(Math.abs(x)>0 && Math.abs(y)>0 && Math.abs(z)>0){
      m_drive_bl.set((y-x+z)/3);
      m_drive_br.set((y+x-z)/3);
      m_drive_fl.set((y+x+z)/3);
      m_drive_fr.set((y-x-z)/3);
    }
    else{
      m_drive_bl.set(0);
      m_drive_br.set(0);
      m_drive_fl.set(0);
      m_drive_fr.set(0);
    }
  }
}
