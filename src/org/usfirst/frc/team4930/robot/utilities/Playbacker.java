package org.usfirst.frc.team4930.robot.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.usfirst.frc.team4930.robot.Robot;

public class Playbacker
{
  Scanner scanner;
  boolean onTime;
  double nextTimestamp;
  long startTime;

  boolean isInverted = false;

  public void setupPlayback() throws FileNotFoundException {
    // set boolean values, instaniate scanner, and set startTime
    scanner = new Scanner(new File(Robot.autoFile));
    // useDelimiter lets the scanner know to to differentiate between values
    scanner.useDelimiter(",|\\n");
    onTime = true;
    startTime = System.currentTimeMillis();
  }

  public void play() {
    // sets the motor values if the current time matches the timestamp
    if ((scanner != null) && scanner.hasNextDouble()) {
      if (onTime) {
        nextTimestamp = scanner.nextDouble();
      }
      // deltaTime makes sure that the player sets the motor values at the correct times
      double timeDelta = nextTimestamp - (System.currentTimeMillis() - startTime);
      if (timeDelta <= 0) {
        double joystick0Y = scanner.nextDouble();
        double joystick1Y = scanner.nextDouble();
        if (isInverted) {
          Robot.driveTrain.move(joystick1Y, joystick0Y);
        } else {
          Robot.driveTrain.move(joystick0Y, joystick1Y);
        }
        onTime = true;
      } else {
        // hold the player back until the current time matches the timestamp
        onTime = false;
      }
    } else {
      Robot.isPlaying = false;
    }
  }

  public void endPlayback() {
    // clean up
    Robot.driveTrain.stop();
    if (scanner != null) {
      scanner.close();
      scanner = null;
    }
  }
}
