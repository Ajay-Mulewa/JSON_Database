package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class Session extends Thread{
    private final Database database;
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Controller controller;
    private final Map getCommands;

    public Session(Database database, Socket socketForClient, DataInputStream dataInputStream,
                   DataOutputStream dataOutputStream, Controller controller, Map getCommands) {
        this.database = database;
        this.socket = socketForClient;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.controller = controller;
        this.getCommands = getCommands;
    }

    @Override
    public void run() {

        GetCommands.type = getCommands.get("type").toString();
        GetCommands.key = getCommands.get("key").toString();
        if (getCommands.get("type").equals("set")) {
            GetCommands.value = getCommands.get("value").toString();
        }

        String requestType = GetCommands.type;
        String displayMessage;

        switch (requestType) {
            case "set":
                Command setCommand = new SetElementRequest(database);
                controller.setCommand(setCommand);
                displayMessage = controller.executeCommand();
                break;

            case "get":
                Command getCommand = new GetElementRequest(database);
                controller.setCommand(getCommand);
                displayMessage = controller.executeCommand();
                break;

            case "delete":
                Command deleteCommand = new DeleteElementRequest(database);
                controller.setCommand(deleteCommand);
                displayMessage = controller.executeCommand();
                break;

            default:
                displayMessage = "Unknown command";
        }

        try {
            dataOutputStream.writeUTF(displayMessage);
            socket.close();
            dataOutputStream.close();
            dataInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
