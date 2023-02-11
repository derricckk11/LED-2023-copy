package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED2 extends SubsystemBase {
    private DigitalOutput r,g,b;

    public LED2() {
        r = new DigitalOutput(0);
        g = new DigitalOutput(1);
        b = new DigitalOutput(2);
        // r.enablePWM(0);
        // g.enablePWM(0);
        // b.enablePWM(0);
    }

    public void startLED() {
        // r.pulse(6d);
        g.set(true);
        // b.pulse(2d);
    }

    public void stopLED() {
        /*r.set(false);
        g.set(false);
        b.set(false);*/
        g.set(false);
    }
} 
