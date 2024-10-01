// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {
  public final class ConsDrivetrain{
  public static final int Motor_LF = 1;
  public static final int Motor_RF = 2;
  public static final int Motor_LB = 3;
  public static final int Motor_RB = 4;
//THEY SAY THE LAURENT IS FOOLISH;
  }
  public final class ConsIntake{
  public static final int Motor_Intake = 6;
  public static final int Motor_lowIntake = 5;
  }
  public final class ConsShooter{
  public static final int Motor_VortexShooter1 = 11;
  public static final int Motor_VortexShooter2 = 12;
  }
  public final class ConsHookshot{
    public static final int Motor_Hookshot_U = 8;//motor up
    public static final int Motor_Hookshot_D = 9;//motor down 
  }
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
