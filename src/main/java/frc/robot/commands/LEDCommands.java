package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LED;

public class BlinkLED extends CommandBase {
    private LED led;
    private Thread t = new Thread(() -> {
        try {
            while (true) {
                this.led.stopLED();
                wait(500l);
                this.led.startLED();
                wait(500l);
            }
        } catch (InterruptedException e) {}
    });

    public BlinkLED(LED led) {
        this.led = led;
        
        addRequirements(led);
    }

    @Override
    public void initialize() {
        t.start();
    }

    @Override
    public void end(boolean i) {
        t.interrupt();
        this.led.stopLED();
    }
}