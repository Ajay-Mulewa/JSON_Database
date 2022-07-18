package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 23456;

    private final String args;

    public Client(String args) {
        this.args = args;
    }
    public void run() {
            System.out.println("Client started!");
            try (
                    Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream())
            ) {
                String outputMsg = this.args;
                output.writeUTF(outputMsg);
                System.out.printf("Sent: %s%n", outputMsg);

                String receivedMsg = input.readUTF();
                System.out.printf("Received: %s%n", receivedMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
