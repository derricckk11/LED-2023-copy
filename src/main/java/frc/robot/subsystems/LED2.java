package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED2 extends SubsystemBase {
    private DigitalOutput r,g,b;

    public LED2() {
        r = new DigitalOutput(0);
        g = new DigitalOutput(1);
        b = new DigitalOutput(2);
    }

    public void startLED(boolean red, boolean green, boolean blue) {
        r.set(red);
        g.set(green);
        b.set(blue);
    }

    public void stopLED() {
        r.set(false);
        g.set(false);
        b.set(false);
    }
} 
