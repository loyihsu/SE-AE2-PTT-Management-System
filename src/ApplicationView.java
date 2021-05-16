import database.*;
import database.filedb.*;

public class ApplicationView {
    private Database database;
    private ApplicationController controller;
    
    public ApplicationView(ApplicationController c, Database db) {
        database = db;
        controller = c;
    }
}
