package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase{
    public AddressableLED LEDstrip;
    public LED() {
        LEDstrip = new AddressableLED(0);
        AddressableLEDBuffer LEDBuffer = new AddressableLEDBuffer(10);
        LEDstrip.setLength(LEDBuffer.getLength());
        LEDstrip.setData(LEDBuffer);
    }
    public void startLED(){
        LEDstrip.start();
    }
    public void stopLED(){
        LEDstrip.stop();
    }
}