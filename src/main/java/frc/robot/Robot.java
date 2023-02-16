// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BlinkLED;
import frc.robot.subsystems.LED;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private LED led = new LED();
  private final Joystick joystick = new Joystick(1);
  private final JoystickButton yellowLED = new JoystickButton(joystick, 1);
  private final JoystickButton purpleLED = new JoystickButton(joystick, 2);
  private final JoystickButton whiteLED = new JoystickButton(joystick, 3);
  private final JoystickButton stopLED = new JoystickButton(joystick, 4);
  private final JoystickButton blinkLED = new JoystickButton(joystick, 5);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public void configureButtonBindings(){
    yellowLED.onTrue(new InstantCommand(() -> led.setLED(255, 255, 0)));
    purpleLED.onTrue(new InstantCommand(() -> led.setLED(255, 0, 255)));
    whiteLED.onTrue(new InstantCommand(() -> led.setLED(255, 255, 255)));
    stopLED.onTrue(new InstantCommand(() -> led.stopLED()));
    blinkLED.toggleOnTrue(new BlinkLED(led));
  }

  @Override
  public void robotInit() {

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    led.stopLED();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {
    configureButtonBindings();
    //led.startLED(true, true, true);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}
}
