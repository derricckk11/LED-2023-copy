package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
    private AddressableLED LEDstrip;
    private AddressableLEDBuffer LEDBuffer;
    private int rainbowFirstPixelHue;
    private Thread t = new Thread(() -> {
        try {
            while (true) {
                stopLED();
                wait(500l);
                startLED();
                wait(500l);
            }
        } catch (InterruptedException e) {}
    });

    public LED() {
        LEDstrip = new AddressableLED(3);
        LEDBuffer = new AddressableLEDBuffer(300);
        LEDstrip.setLength(LEDBuffer.getLength());
        LEDstrip.setData(LEDBuffer);
    }

    public void startLED() {
        LEDstrip.start();
    }

    public void stopLED() {
        LEDstrip.stop();
    }

    public void setLED(int r, int g, int b) {
        for (int i = 0; i < LEDBuffer.getLength(); i++) {
            LEDBuffer.setRGB(i, r, g, b);
        }
        LEDstrip.setData(LEDBuffer);
    }

    public void setPurple() {
        setLED(160, 32, 240);
    }

    public void setYellow() {
        setLED(255,255,0);
    }

    public void rainbow() {
        for (int i = 0; i < LEDBuffer.getLength(); i++) {
            final var hue = (rainbowFirstPixelHue + (i * 180 / LEDBuffer.getLength())) % 180;
            LEDBuffer.setHSV(i, hue, 255, 128);
        }
        rainbowFirstPixelHue += 3;
        rainbowFirstPixelHue %= 180;
    }

    public void blink(){
        t.start();
    }

    public void stopBlink(boolean i){
        t.interrupt();
        stopLED();
    }
}