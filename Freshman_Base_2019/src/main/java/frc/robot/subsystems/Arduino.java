package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Arduino {
    //I2C communication protocol
    private final static I2C wire;

    //colors for LEDs
    public enum Colors {
        Red, Blue, None
    }

    //I2C port to use with Arduino
    private static final int address;
    //data to be written to Arduino
    public static byte[] writeData;

    static {
        address = 0x1;
        wire = new I2C(Port.kOnboard, address);
        writeData = new byte[5];
    }

    public static void setAllianceColor(Alliance color) {
        switch (color) {
            case Red:
                writeData[0] = 0;
                break;
            case Blue:
                writeData[0] = 1;
                break;
            case Invalid:
                writeData[0] = 2;
                break;
        }
    }

    public static void write() {
        //write data to Arduino as byte array
        wire.writeBulk(writeData);
    }
}