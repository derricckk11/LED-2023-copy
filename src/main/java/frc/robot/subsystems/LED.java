package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
    private AddressableLED strip;
    private AddressableLEDBuffer buffer;
    private int rainbowFirstPixelHue;

    public LED() {
        strip = new AddressableLED(3);
        buffer = new AddressableLEDBuffer(300);
        strip.setLength(buffer.getLength());
        strip.setData(buffer);
    }

    public void startLED() {
        strip.start();
    }

    public void stopLED() {
        strip.stop();
    }

    public double[] getColor() {
        var led = buffer.getLED(0);
        return new double[]{led.red, led.green, led.blue};
    }

    public void setLED(int r, int g, int b) {
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, r, g, b);
        }
        strip.setData(buffer);
    }

    public void setLED(double r, double g, double b) {
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, (int)(r*255), (int)(g*255), (int)(b*255));
        }
        strip.setData(buffer);
    }

    public void setPurple() {
        setLED(160, 32, 240);
    }

    public void setYellow() {
        setLED(255,255,0);
    }

    public void setWhite() {
        setLED(255, 255, 255);
    }

    public void setBlack() {
        setLED(0, 0, 0);
    }

    public void rainbow() {
        for (int i = 0; i < buffer.getLength(); i++) {
            final var hue = (rainbowFirstPixelHue + (i * 180 / buffer.getLength())) % 180;
            buffer.setHSV(i, hue, 255, 128);
        }
        rainbowFirstPixelHue += 3;
        rainbowFirstPixelHue %= 180;
    }
}