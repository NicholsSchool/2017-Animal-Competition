package org.usfirst.frc.team4930.robot.commands;

import org.usfirst.frc.team4930.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class ValueUp extends Command
{

  public String reference;

  public ValueUp(String reference) {
    this.reference = reference;
  }

  protected void initialize() {}

  protected void execute() {
    double newValue = RobotMap.values.get(reference) + 0.01;
    if (newValue <= 1.0) {
      RobotMap.values.put(reference, newValue);
    }
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {}

  protected void interrupted() {}
}
