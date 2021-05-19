package src;

import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import src.view.*;
import src.database.*;
import src.database.filedb.*;
import src.view.ApplicationView;
import src.view.requirement.*;
import src.view.staff.*;

import javax.swing.*;

public class ApplicationController implements ActionListener {
    private DatabaseDecorator database;
    private ApplicationView view;

    public ApplicationController(DatabaseDecorator database) {
        this.database = database;
        this.view = new ApplicationView(this, database.getDatabase());
    }

    public DatabaseDecorator getDatabase() {
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
        String filepath = new File("./src/database/filedb/file.txt").getAbsolutePath();
        Database database = new FileDB(filepath);
        ApplicationController controller = new ApplicationController(new DatabaseDecorator(database));

//        JFrame gui = controller.getView();
//        gui.setVisible(true);

        database.write();
    }
}
