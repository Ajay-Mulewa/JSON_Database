package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final String PATH = System.getProperty("user.dir") + "/src/client/data/";

    public static void main(String[] args) {
        RequestArgs requestArgs = new RequestArgs();
        JCommander.newBuilder()
                .addObject(requestArgs)
                .build()
                .parse(args);
        String arguments;
        if (requestArgs.fileName != null) {
            arguments = readFileAsString(requestArgs.fileName);
        } else {
            arguments = new Gson().toJson(requestArgs);
        }
        Client client = new Client(arguments);
        client.run();
    }

    public static String readFileAsString(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(PATH + fileName)));
        } catch (IOException e) {
            System.out.println("File not found" + e.getMessage());
        }
        return "";
    }
}

