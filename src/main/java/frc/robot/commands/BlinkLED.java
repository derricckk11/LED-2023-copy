package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.LED;

public class BlinkLED extends SequentialCommandGroup {
    private double[] buffer;
    public BlinkLED(LED led){
        addCommands(
            new InstantCommand(() -> led.startLED()),
            new SequentialCommandGroup(
                new InstantCommand(() -> {
                    buffer = led.getColor();
                    led.setBlack();}),
                new WaitCommand(1d),
                new InstantCommand(() -> led.setLED(buffer[0], buffer[1], buffer[2])),
                new WaitCommand(1d)
            ).repeatedly()
        );
    }
}
