package src;

import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import src.database.*;
import src.database.filedb.*;
import src.view.*;
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
            setPopupAndSetVisible(new AddStaffFrame(this));
        } else if (event.getSource() == view.getStaffPanel().getMiddleButton()) {
            // Train Staff
            setPopupAndSetVisible(new TrainStaffFrame(this, database.getDatabase()));
        } else if (event.getSource() == view.getStaffPanel().getLowerButton()) {
            // Remove Staff

        } else if (event.getSource() == view.getRequirementPanel().getTopButton()) {
            // Add New Requirement
            setPopupAndSetVisible(new AddRequirementFrame(this));
        } else if (event.getSource() == view.getRequirementPanel().getMiddleButton()) {
            // Assign Staff to Lab
            setPopupAndSetVisible(new AssignStaffFrame(this, database.getDatabase()));
        } else if (event.getSource() == view.getRequirementPanel().getLowerButton()) {
            // Remove Requirement

        } else if (popup instanceof AddRequirementFrame) {
            AddRequirementFrame current = (AddRequirementFrame) popup;
            if (event.getSource() == current.getSendButton()) {
                int id = database.getDatabase().getRequirementTable().getTable().size()+1;
                database.getDatabase().getRequirementTable().add(current.getUserInput(id));
                view.getRequirementPanel().refreshTable();
                popup.setVisible(false);
                popup = null;
            }
        } else if (popup instanceof AssignStaffFrame) {
            AssignStaffFrame current = (AssignStaffFrame) popup;
            if (event.getSource() == current.getSendButton()) {
                database.getDatabase().getAssignmentTable().add(current.getUserInput(database.getDatabase()));
                view.getRequirementPanel().refreshTable();
                view.getStaffPanel().refreshTable();
                popup.setVisible(false);
                popup = null;
            }
        } else if (popup instanceof AddStaffFrame) {
            AddStaffFrame current = (AddStaffFrame) popup;
            if (event.getSource() == current.getSendButton()) {
                int id = database.getDatabase().getStaffTable().getTable().size()+1;
                database.getDatabase().getStaffTable().add(current.getUserInput(id));
                view.getStaffPanel().refreshTable();
                popup.setVisible(false);
                popup = null;
            }
        } else if (popup instanceof TrainStaffFrame) {
            TrainStaffFrame current = (TrainStaffFrame) popup;
            if (event.getSource() == current.getSendButton()) {
                current.trainStaff(database.getDatabase());
                view.getStaffPanel().refreshTable();
                popup.setVisible(false);
                popup = null;
            }
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
        ApplicationController controller = new ApplicationController(new DatabaseDecorator(database));

        JFrame gui = controller.getView();
        gui.setVisible(true);

//        database.write();
    }
}
