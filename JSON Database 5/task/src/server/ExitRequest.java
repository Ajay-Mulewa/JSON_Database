package server;

public class ExitRequest implements Command {

    private final Database database;

    public ExitRequest(Database database) {
        this.database = database;
    }

    public String execute() {
        return database.exit();
    }
}

