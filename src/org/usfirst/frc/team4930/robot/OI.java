package org.usfirst.frc.team4930.robot;

import org.usfirst.frc.team4930.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI
{
  // joysticks 0-1-2
  public Joystick j0 = new Joystick(0);
  public Joystick j1 = new Joystick(1);
  public Joystick j2 = new Joystick(2);

  // all joystick0 buttons
  public JoystickButton j0b1 = new JoystickButton(j0, 1);
  public JoystickButton j0b4 = new JoystickButton(j0, 4);
  public JoystickButton j0b7 = new JoystickButton(j0, 7);
  public JoystickButton j0b8 = new JoystickButton(j0, 8);
  public JoystickButton j0b9 = new JoystickButton(j0, 9);
  public JoystickButton j0b10 = new JoystickButton(j0, 10);
  public JoystickButton j0b11 = new JoystickButton(j0, 11);
  public JoystickButton j0b12 = new JoystickButton(j0, 12);

  // all joystick1 buttons
  public JoystickButton j1b1 = new JoystickButton(j1, 1);
  public JoystickButton j1b3 = new JoystickButton(j1, 3);
  public JoystickButton j1b7 = new JoystickButton(j1, 7);
  public JoystickButton j1b8 = new JoystickButton(j1, 8);
  public JoystickButton j1b9 = new JoystickButton(j1, 9);
  public JoystickButton j1b10 = new JoystickButton(j1, 10);
  public JoystickButton j1b11 = new JoystickButton(j1, 11);
  public JoystickButton j1b12 = new JoystickButton(j1, 12);

  // all joystick2 buttons
  public JoystickButton j2b1 = new JoystickButton(j2, 1);
  public JoystickButton j2b2 = new JoystickButton(j2, 2);
  public JoystickButton j2b3 = new JoystickButton(j2, 3);
  public JoystickButton j2b4 = new JoystickButton(j2, 4);
  public JoystickButton j2b8 = new JoystickButton(j2, 8);
  public JoystickButton j2b10 = new JoystickButton(j2, 10);
  public JoystickButton j2b12 = new JoystickButton(j2, 12);

  public OI() {

    // joystick0 button actions
    j0b1.whileHeld(new IntakeFuel());
    j0b4.whileHeld(new UnloadHopper());
    j0b7.whenPressed(new ValueDown("agitate"));
    j0b8.whenPressed(new ValueUp("agitate"));
    j0b9.whenPressed(new ValueDown("loader"));
    j0b10.whenPressed(new ValueUp("loader"));
    j0b11.whenPressed(new ValueDown("shooter"));
    j0b12.whenPressed(new ValueUp("shooter"));

    // joystick1 button actions
    j1b1.whenPressed(new ShiftUp());
    j1b1.whenReleased(new ShiftDown());
    j1b3.whenPressed(new ToggleCamera());
    j1b7.whenPressed(new ValueDown("intake_in"));
    j1b8.whenPressed(new ValueUp("intake_in"));
    j1b9.whenPressed(new ValueDown("intake_out"));
    j1b10.whenPressed(new ValueUp("intake_out"));
    j1b11.whenPressed(new ValueDown("clear_fuel"));
    j1b12.whenPressed(new ValueUp("clear_fuel"));

    // joystick2 button actions
    j2b1.whileHeld(new ShootingSequence());
    j2b2.whenPressed(new PlaceGear());
    j2b3.whileHeld(new ClimbRope());
    j2b4.whileHeld(new ClearShooter());
    j2b8.whenPressed(new ReplayRecord());
    j2b10.whenPressed(new ReplayStopRecord());
    j2b12.whenPressed(new ReplayPlayback());
  }

  public static void motorControllerSafetyAndBrakes(boolean safety, boolean brake) {
    RobotMap.dtMasterMotors.setSafetyEnabled(safety);
    Robot.driveTrain.brakeMode(brake);
    RobotMap.intake.setSafetyEnabled(safety);
    Robot.intake.brakeMode(false);
    RobotMap.loader.setSafetyEnabled(safety);
    Robot.loader.brakeMode(brake);
    RobotMap.shooter.setSafetyEnabled(safety);
    Robot.shooter.brakeMode(false);
    RobotMap.gadgetL.setSafetyEnabled(safety);
    RobotMap.gadgetR.setSafetyEnabled(safety);
    Robot.gearGadget.brakeMode(brake);
  }
}
