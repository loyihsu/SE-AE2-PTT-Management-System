package src;

import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import src.database.*;
import src.database.filedb.*;
import src.view.ApplicationView;
import src.view.requirement.*;
import src.view.staff.*;

import javax.swing.*;

public class ApplicationController implements ActionListener {
    private DatabaseDecorator database;
    private ApplicationView view;
    private JFrame popup;

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
        if (event.getSource() == view.getStaffPanel().getTopButton()) {
            // Add New Staff
            setPopupAndSetVisible(new AddStaffFrame());
        } else if (event.getSource() == view.getStaffPanel().getMiddleButton()) {
            // Train Staff
            setPopupAndSetVisible(new TrainStaffFrame());
        } else if (event.getSource() == view.getStaffPanel().getLowerButton()) {
            // Remove Staff

        } else if (event.getSource() == view.getRequirementPanel().getTopButton()) {
            // Add New Requirement
            setPopupAndSetVisible(new AddRequirementFrame());
        } else if (event.getSource() == view.getRequirementPanel().getMiddleButton()) {
            // Assign Staff to Lab
            setPopupAndSetVisible(new AssignStaffFrame());
        } else if (event.getSource() == view.getRequirementPanel().getLowerButton()) {
            // Remove Requirement

        }

        // To redraw the table
        // view.get...Panel().refreshTable();
    }

    private void setPopupAndSetVisible(JFrame view) {
        if (popup == null || popup.isVisible() == false){
            popup = view;
            popup.setVisible(true);
        }
    }

    public static void main(String[] args) throws IOException {
        // This code dynamically find the file.txt file in the database/filedb folder and create a Database object from it.
        String filepath = new File("./src/database/filedb/file.txt").getAbsolutePath();
        Database database = new FileDB(filepath);
        ApplicationController controller = new ApplicationController(new DatabaseDecorator(database));

        JFrame gui = controller.getView();
        gui.setVisible(true);

//        database.write();
    }
}
