package org.usfirst.frc.team4930.robot.commands;

import org.usfirst.frc.team4930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftDown extends Command
{

  public ShiftDown() {
    requires(Robot.pneumatics);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    Robot.pneumatics.enableLowGear();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}
