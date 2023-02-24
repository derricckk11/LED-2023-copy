package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.LED;

public class BlinkLED extends SequentialCommandGroup {
    public BlinkLED(LED led){
        super(
            new InstantCommand(() -> led.stopLED()),
            new WaitCommand(0.5d),
            new InstantCommand(() -> led.startLED()),
            new WaitCommand(0.5d)
        );
    }
}
