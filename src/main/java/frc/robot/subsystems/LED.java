package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase{
    public AddressableLED LEDstrip;
    public AddressableLEDBuffer LEDBuffer;
    public int rainbowFirstPixelHue;
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
        for (var i = 0; i < LEDBuffer.getLength(); i++) {
            LEDBuffer.setRGB(i, r, g, b);
        }
        LEDstrip.setData(LEDBuffer);
    }
    public void setYellow() {
        setLED(255, 255, 0);
    }
    public void setPurple() {
        setLED(160, 32, 240);
    }
    public void rainbow() {
        for (var i = 0; i < LEDBuffer.getLength(); i++) {
            final var hue = (rainbowFirstPixelHue + (i * 180 / LEDBuffer.getLength())) % 180;
            LEDBuffer.setHSV(i, hue, 255, 128);
        }
        rainbowFirstPixelHue += 3;
        rainbowFirstPixelHue %= 180;
      }
}   