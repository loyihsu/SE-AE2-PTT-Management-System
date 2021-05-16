import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import datatype.*;
import database.*;
import database.filedb.*;

public class ApplicationController {
    private Database database;
    private ApplicationView view;

    public ApplicationController() {
        // This code dynamically find the file.txt file in the database/filedb folder and create a Database object from it.
        // Running from javac.
        try {
            database = new FileDB(new File("./database/filedb/file.txt").getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        view = new ApplicationView();
    }

    public Database getDatabase() {
        return database;
    }

    public ApplicationView getView() {
        return view;
    }

    public static void main(String[] args) {
        ApplicationController controller = new ApplicationController();
        Database db = controller.getDatabase();

        // This is testing the write function of the database.
        try {
            db.write();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
