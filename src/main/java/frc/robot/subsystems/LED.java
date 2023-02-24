package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
    public enum LEDState {
        WHITE,
        PURPLE,
        YELLOW,
        BLACK,
        RAINBOW
    }

    private Map<LEDState, Command> commands = Map.of(
        LEDState.WHITE, new InstantCommand(() -> setLED(100, 100, 100), this),
        LEDState.PURPLE, new InstantCommand(() -> setLED(70, 0, 100), this),
        LEDState.YELLOW, new InstantCommand(() -> setLED(150, 75, 0), this),
        LEDState.BLACK, new InstantCommand(() -> setLED(0, 0, 0), this),
        LEDState.RAINBOW, new RainbowLED(this).repeatedly()
    );
    private AddressableLED strip;
    private AddressableLEDBuffer buffer;
    private LEDState state;

    public LED() { 
        strip = new AddressableLED(3);
        buffer = new AddressableLEDBuffer(300);
        strip.setLength(buffer.getLength());
        state = LEDState.BLACK;
        startLED();
    }

    public void setState(LEDState state) {
        getStateCommand().cancel();
        this.state = state;
        startLED();
    }

    public Command getStateCommand() {
        return commands.get(state);
    }

    public void startLED() {
        strip.start();
        getStateCommand().schedule();
    }

    public void stopLED() {
        getStateCommand().cancel();
        setBlack();
        strip.stop();
    }

    public void setLED(int r, int g, int b) {
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, r, g, b);
        }
        strip.setData(buffer);
    }

    public void setBlack() {
        setLED(0, 0, 0);
    }

    private static class RainbowLED extends CommandBase {
        private LED led;

        private RainbowLED(LED led) {
            this.led = led;
            addRequirements(led);
        }

        @Override
        public void execute() {
            int len = led.buffer.getLength();
            double time = System.currentTimeMillis();
            for (int i = 0; i < len; i++) {
                int hue = (int) Math.floor((((time / 50 + i) / len) % 1) * 255);
                led.buffer.setHSV(i, hue, 255, 128);
            }
            led.strip.setData(led.buffer);
        }

        @Override
        public void end(boolean i) {
            led.setBlack();
        }
    }
}