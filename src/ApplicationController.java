import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import datatype.*;
import database.*;
import database.filedb.*;

public class ApplicationController implements ActionListener {
    private Database database;
    private ApplicationView view;

    public ApplicationController(Database database) {
        this.database = database;
        this.view = new ApplicationView(this, database);
    }

    public Database getDatabase() {
        return database;
    }

    public ApplicationView getView() {
        return view;
    }

    // ===============================================
    // ActionListener
    // ===============================================

    public void actionPerformed(ActionEvent event) {

    }

    public static void main(String[] args) throws IOException {
        // This code dynamically find the file.txt file in the database/filedb folder and create a Database object from it.
        String filepath = new File("./database/filedb/file.txt").getAbsolutePath();
        Database database = new FileDB(filepath);
        ApplicationController controller = new ApplicationController(database);
        database.write();
    }
}
