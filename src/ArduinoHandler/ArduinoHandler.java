package ArduinoHandler;

import com.fazecast.jSerialComm.SerialPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArduinoHandler {

    public static SerialPort sp = SerialPort.getCommPort("COM5");

    public static void inicializa() {

        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

        if (sp.openPort()) {
            System.out.println("Port is open");
        } else {
            System.out.println("Failed to open port");
            return;
        }
    }

    public static void encerra() {

        if (sp.closePort()) {
            System.out.println("\nPort is closed");

        } else {
            System.out.println("Failed to close port");
            return;
        }
    }

    public static void recebeDados() throws IOException {

        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 2000, 0);
        BufferedReader in = new BufferedReader(new InputStreamReader(sp.getInputStream()));

        while (true) {

            System.out.println(in.readLine());

        }

    }
}
