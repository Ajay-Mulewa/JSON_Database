package server;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    Database database = new Database();
    Controller controller = new Controller();
    public void run() {
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS))) {
            System.out.println("Server started!");
            ExecutorService executor = Executors.newCachedThreadPool();
            while (true) {

                Socket socket = server.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                String receivedRequest = input.readUTF();
                Map getCommands = new Gson().fromJson(receivedRequest, Map.class);

                if ("exit".equals(getCommands.get("type"))) {
                    Command exitRequest = new ExitRequest(database);
                    controller.setCommand(exitRequest);
                    String displayMessage = controller.executeCommand();
                    output.writeUTF(displayMessage);
                    socket.close();
                    input.close();
                    output.close();
                    executor.shutdown();
                    break;
                } else {
                    executor.submit(() -> new Session(database, socket, input, output, controller, getCommands).start());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
