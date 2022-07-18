package server;

import client.RequestArgs;

public class SetElementRequest implements Command {

    private final Database database;

    public SetElementRequest(Database database) {
        this.database = database;
    }

    public String execute() {
        return database.set(GetCommands.key, GetCommands.value);
    }
}
