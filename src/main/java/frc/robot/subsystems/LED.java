package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase{
    public AddressableLED LEDstrip;
    public AddressableLEDBuffer LEDBuffer;
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
}