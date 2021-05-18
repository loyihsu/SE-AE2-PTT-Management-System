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
    private Database database;
    private ApplicationView view;
    private JFrame popup;

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
        if (event.getSource() == view.getStaffPanel().getControlPanel().getAddStaff()) {
            setPopupAndSetVisible(new AddStaffFrame());
        } else if (event.getSource() == view.getStaffPanel().getControlPanel().getTrainStaff()) {
            setPopupAndSetVisible(new TrainStaffFrame());
        } else if (event.getSource() == view.getRequirementPanel().getControlPanel().getNewLab()) {
            setPopupAndSetVisible(new AddRequirementFrame());
        } else if (event.getSource() == view.getRequirementPanel().getControlPanel().getAssignStaff()) {
            setPopupAndSetVisible(new AssignStaffFrame());
        }
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
        ApplicationController controller = new ApplicationController(database);

//        JFrame gui = controller.getView();
//        gui.setVisible(true);

        database.write();
    }
}
