// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.LED.LEDState;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private LED led = new LED();
  private Joystick joystick = new Joystick(1);
  private JoystickButton yellowLED = new JoystickButton(joystick, 1);
  private JoystickButton purpleLED = new JoystickButton(joystick, 2);
  private JoystickButton led3 = new JoystickButton(joystick, 3);
  private JoystickButton led4 = new JoystickButton(joystick, 4);
  private JoystickButton led5 = new JoystickButton(joystick, 5);
  private JoystickButton led6 = new JoystickButton(joystick, 6);
  private JoystickButton led7 = new JoystickButton(joystick, 7);
  private JoystickButton led8 = new JoystickButton(joystick, 8);
  private JoystickButton led9 = new JoystickButton(joystick, 9);
  private JoystickButton led10 = new JoystickButton(joystick, 10);
  private JoystickButton led11 = new JoystickButton(joystick, 11);
  //private JoystickButton led12 = new JoystickButton(joystick, 12); //not assigned
  //private JoystickButton led13= new JoystickButton(joystick, 13); //not assigned
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public void configureButtonBindings(){
    yellowLED.onTrue(new InstantCommand(() -> led.setState(LEDState.YELLOW)));
    purpleLED.onTrue(new InstantCommand(() -> led.setState(LEDState.PURPLE)));
    led3.onTrue(new InstantCommand(() -> led.setState(LEDState.RED)));
    led4.onTrue(new InstantCommand(() -> led.setState(LEDState.WHITE)));
    led5.onTrue(new InstantCommand(() -> led.setState(LEDState.BLUE)));
    led6.onTrue(new InstantCommand(() -> led.setState(LEDState.RAINBOW)));
    led7.onTrue(new InstantCommand(() -> led.setState(LEDState.RAINBOWCYCLE)));
    //led8.onTrue(new InstantCommand(() -> led.setState(LEDState.GENDERFLUID)));
    led9.onTrue(new InstantCommand(() -> led.setState(LEDState.TRANS)));
    //led7.onTrue(new InstantCommand(() -> led.setState(LEDState.GAY)));
    led8.onTrue(new InstantCommand(() -> led.setState(LEDState.LESBIAN)));
    //led9.onTrue(new InstantCommand(() -> led.setState(LEDState.BI)));
    led10.onTrue(new InstantCommand(() -> led.setState(LEDState.NONBINARY)));
    led11.toggleOnTrue(new LED.BlinkLED(led).repeatedly());
  }

  @Override
  public void robotInit() {
    //led.startLED();
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
    led.startLED();
    System.out.println("led has started");
    configureButtonBindings();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledPeriodic() {}
}
