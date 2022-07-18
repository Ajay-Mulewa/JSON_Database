package server;

import client.RequestArgs;

public class DeleteElementRequest implements Command {

    private Database database;

    public DeleteElementRequest(Database database) {
        this.database = database;
    }

    public String execute() {
        return database.delete(GetCommands.key);
    }
}
