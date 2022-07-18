package server;

import client.RequestArgs;

public class GetElementRequest implements Command {

    private Database database;

    public GetElementRequest(Database database) {
        this.database = database;
    }

    public String execute() {
        return database.get(GetCommands.key);
    }
}
