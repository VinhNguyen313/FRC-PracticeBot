/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.LiftDownIntake;
import frc.robot.commands.LiftUpIntake;
import frc.robot.commands.PIDPositionDrive;
import frc.robot.util.VortxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
   public boolean isYToggled = false;

   /*
    * RobotMap.Controller.MAIN is the ID of the controller, you can check this
    * using Driver Station
    */
   public VortxController main = new VortxController(RobotMap.Controller.MAIN);

   public OI() {
      main.a.whenPressed(new PIDPositionDrive(100, .5, .5, .5));
      main.x.whenPressed(new LiftDownIntake());
      main.y.whenPressed(new LiftUpIntake());
   }

   public void updateY() {
      if (main.getYButtonPressed())
         isYToggled = !isYToggled;

   }

   public boolean getYToggle() {
      return isYToggled;
   }

   public double getDriveValue() {
      return -(main.getTriggerAxis(Hand.kRight) - main.getTriggerAxis(Hand.kLeft));
   }

   public double getTurnValue() {
      return main.getX(Hand.kLeft);
   }

   public void log() {
      SmartDashboard.putBoolean("isYToggled (Quick Turn Mode)", isYToggled);
   }
}