package frc.robot.subsystems;
import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase{
    public AddressableLED LEDstrip;
    public AddressableLEDBuffer LEDBuffer;
    public int rainbowFirstPixelHue;
    public boolean blinkStatus;

    public LED() {
        LEDstrip = new AddressableLED(3);
        LEDBuffer = new AddressableLEDBuffer(300);
        LEDstrip.setLength(LEDBuffer.getLength());
        LEDstrip.setData(LEDBuffer);
    }

    public void startLED(){
        LEDstrip.start();
    }

    public void stopLED(){
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

    public void rainbow() {
        for (int i = 0; i < LEDBuffer.getLength(); i++) {
            final var hue = (rainbowFirstPixelHue + (i * 180 / LEDBuffer.getLength())) % 180;
            LEDBuffer.setHSV(i, hue, 255, 128);
        }
        rainbowFirstPixelHue += 3;
        rainbowFirstPixelHue %= 180;
    }

    public void startBlink() throws InterruptedException {
        while (blinkStatus = true) {
            stopLED();
            TimeUnit.SECONDS.sleep((long) 0.5);
            startLED();
            TimeUnit.SECONDS.sleep((long) 0.5);
        }
    }

    public void stopBlink() throws InterruptedException {
        blinkStatus = false;
        stopLED();
    }
}