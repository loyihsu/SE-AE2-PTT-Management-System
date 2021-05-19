package src;

import src.database.DatabaseDecorator;
import src.database.filedb.FileDB;
import src.database.interfaces.tables.Database;
import src.database.types.Requirement;
import src.database.types.Staff;
import src.view.ApplicationView;
import src.view.requirement.AddRequirementFrame;
import src.view.requirement.AssignStaffFrame;
import src.view.staff.AddStaffFrame;
import src.view.staff.TrainStaffFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
            String result = JOptionPane.showInputDialog(null, "Which staff (id) would you like to remove?");
            try {
                int id = Integer.parseInt(result);
                Staff staff = database.getDatabase().getStaffTable().find(id);
                if (staff == null) {
                    JOptionPane.showMessageDialog(null, "Invalid input: Staff doesn't exist.", "Operation Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove (" + id + "," + staff.getName() + ")");
                    if (confirm == JOptionPane.YES_OPTION) {
                        database.cleanlyRemoveStaff(staff);
                        view.getStaffPanel().refreshTable();
                        view.getRequirementPanel().refreshTable();
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input.", "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if (event.getSource() == view.getRequirementPanel().getTopButton()) {
            // Add New Requirement
            setPopupAndSetVisible(new AddRequirementFrame(this));
        } else if (event.getSource() == view.getRequirementPanel().getMiddleButton()) {
            // Assign Staff to Lab
            setPopupAndSetVisible(new AssignStaffFrame(this, database));
        } else if (event.getSource() == view.getRequirementPanel().getLowerButton()) {
            // Remove Requirement
            String result = JOptionPane.showInputDialog(null, "Which lab (id) would you like to remove?");
            try {
                int id = Integer.parseInt(result);
                Requirement requirement = database.getDatabase().getRequirementTable().find(id);
                if (requirement == null) {
                    JOptionPane.showMessageDialog(null, "Invalid input: Lab doesn't exist.", "Operation Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove (Lab " + id + ")");
                    if (confirm == JOptionPane.YES_OPTION) {
                        database.cleanlyRemoveRequirement(requirement);
                        view.getStaffPanel().refreshTable();
                        view.getRequirementPanel().refreshTable();
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input.", "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if (popup instanceof AddRequirementFrame) {
            AddRequirementFrame current = (AddRequirementFrame) popup;
            if (event.getSource() == current.getSendButton()) {
                int id = database.getDatabase().getRequirementTable().getTable().size() + 1;
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
                int id = database.getDatabase().getStaffTable().getTable().size() + 1;
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
        if (popup == null || popup.isVisible() == false) {
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
