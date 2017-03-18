package org.usfirst.frc.team4930.robot;

import org.usfirst.frc.team4930.robot.sensors.Dial;
import org.usfirst.frc.team4930.robot.sensors.Encoders;
import org.usfirst.frc.team4930.robot.sensors.Gyro;
import org.usfirst.frc.team4930.robot.sensors.ToggleSwitch;
import org.usfirst.frc.team4930.robot.subsystems.Climber;
import org.usfirst.frc.team4930.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4930.robot.subsystems.GearGadget;
import org.usfirst.frc.team4930.robot.subsystems.Intake;
import org.usfirst.frc.team4930.robot.subsystems.Loader;
import org.usfirst.frc.team4930.robot.subsystems.Shifter;
import org.usfirst.frc.team4930.robot.subsystems.Shooter;
import org.usfirst.frc.team4930.robot.utilities.Dashboard;
import org.usfirst.frc.team4930.robot.utilities.ReplayPlayer;
import org.usfirst.frc.team4930.robot.utilities.ReplayRecorder;

import com.ctre.CANTalon;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot
{

  public static OI oi;
  public static Dashboard dashboard;
  public static CANTalon testMotor;

  // subsystems
  public static Climber climber;
  public static DriveTrain driveTrain;
  public static GearGadget gearGadget;
  public static Intake intake;
  public static Loader loader;
  public static Shifter shifter;
  public static Shooter shooter;

  // sensors
  public static Dial dial;
  public static Encoders encoders;
  public static Gyro gyro;
  public static ToggleSwitch allianceToggle;

  // auto replay setup
  public static Integer dialNumber;
  public static Command autoCommand;
  public static String autoDescription;
  public static String replayFilePath;
  public static ReplayPlayer replayPlayer;
  public static ReplayRecorder replayRecorder;

  // initial robot states
  public static boolean inLowGear = true; // robot must start in low gear!
  public static boolean isRecording = false;
  public static boolean isReplaying = false;
  public static boolean isAuto = false;

  public void robotInit() {

    // initialize robot mappings
    RobotMap.init();

    // instantiate drive train first
    driveTrain = new DriveTrain();

    // instantiate the rest of the subsystems and sensors
    climber = new Climber();
    gearGadget = new GearGadget();
    encoders = new Encoders();
    intake = new Intake();
    loader = new Loader();
    shifter = new Shifter();
    shooter = new Shooter();
    dial = new Dial();
    allianceToggle = new ToggleSwitch();
    gyro = new Gyro();

    // instantiate replay code
    replayPlayer = new ReplayPlayer();
    replayRecorder = new ReplayRecorder();

    // instantiate dashboard and oi last
    oi = new OI();
    dashboard = new Dashboard();

    // set default settings for subsystems and sensors
    Robot.climber.brakeMode(true);
    Robot.driveTrain.brakeMode(true);
    Robot.gearGadget.brakeMode(true);
    Robot.intake.brakeMode(false);
    Robot.loader.brakeMode(true);
    Robot.shooter.brakeMode(false);
    Robot.gyro.calibrating();

    // setup USB camera
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(320, 240);
    camera.setFPS(20);

  }

  public void autonomousInit() {
    dial.setSelectedReplay();
    Robot.encoders.reset();
    isAuto = true;
    autoCommand.start();
  }

  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    dashboard.update();
  }

  public void teleopInit() {
    isAuto = false;
    if (autoCommand != null) {
      autoCommand.cancel();
    }
    Robot.encoders.reset();
  }

  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    dial.setSelectedReplay();
    dashboard.update();
  }

  public void disabledInit() {}

  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  public void testInit() {
    testMotor = new CANTalon(21);
  }

  public void testPeriodic() {
    LiveWindow.run();
    testMotor.set(0.4);
  }
}
