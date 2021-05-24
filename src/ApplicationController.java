package src;

import src.database.filedb.FileDB;
import src.database.interfaces.Database;
import src.database.types.Requirement;
import src.database.types.Staff;
import src.database.types.interfaces.AssignmentElement;
import src.view.ApplicationView;
import src.view.ModeSelector;
import src.view.popups.requirement.RAssignmentFrame;
import src.view.popups.requirement.RCreationFrame;
import src.view.popups.staff.SCreationFrame;
import src.view.popups.staff.STrainingFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationController implements ActionListener {
    private final Database database;
    private final ApplicationView view;
    private JFrame popup;

    public ApplicationController(Database database) {
        this.database = database;
        this.view = new ApplicationView(this);
        ShutDownTask task = new ShutDownTask(database);
        Runtime.getRuntime().addShutdownHook(task);
    }

    // ===============================================
    // Application Starter
    // ===============================================
    public static void main(String[] args) throws IOException {
        // This code dynamically find the file.txt file in the database/filedb folder and create a Database object from it.
        String filepath = new File("./src/database/filedb/file.txt").getAbsolutePath();
        Database database = new FileDB(filepath);
        ApplicationController controller = new ApplicationController(database);

        JFrame gui = controller.getView();
        gui.setVisible(true);
    }

    // ===============================================
    // Getters
    // ===============================================
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
        if (event.getSource() == view.getStaffPanel().getTopButton()) {
            // Add New Staff
            setPopupAndSetVisible(new SCreationFrame(this));
        } else if (event.getSource() == view.getStaffPanel().getMiddleButton()) {
            // Train Staff
            setPopupAndSetVisible(new STrainingFrame(this));
        } else if (event.getSource() == view.getStaffPanel().getLowerButton()) {
            // Remove Staff
            createRemoverOptionPane(ModeSelector.STAFF);
        } else if (event.getSource() == view.getRequirementPanel().getTopButton()) {
            // Add New Requirement
            setPopupAndSetVisible(new RCreationFrame(this));
        } else if (event.getSource() == view.getRequirementPanel().getMiddleButton()) {
            // Assign Staff to Lab
            setPopupAndSetVisible(new RAssignmentFrame(this));
        } else if (event.getSource() == view.getRequirementPanel().getLowerButton()) {
            // Remove Requirement
            createRemoverOptionPane(ModeSelector.REQUIREMENT);
        } else if (popup instanceof RCreationFrame) {
            RCreationFrame current = (RCreationFrame) popup;
            if (event.getSource() == current.getSendButton()) {
                int id = database.getNextId(ModeSelector.REQUIREMENT);
                database.getRequirementTable().add(current.getUserInput(id));
                view.getRequirementPanel().refreshTable();
                popup.setVisible(false);
                popup = null;
            }
        } else if (popup instanceof RAssignmentFrame) {
            RAssignmentFrame current = (RAssignmentFrame) popup;
            if (event.getSource() == current.getSendButton()) {
                database.getAssignmentTable().add(current.getUserInput());
                view.getRequirementPanel().refreshTable();
                view.getStaffPanel().refreshTable();
                popup.setVisible(false);
                popup = null;
            } else if (event.getSource() == current.getLab().getBox()) {
                Requirement item = current.getRequirements().get(current.getLab().getUserInput());
                ArrayList<String> trainingsNeeded = item.getTrainingsNeeded();
                ArrayList<Staff> qualifiedStaff = database.getStaffTable().findWithSkills(trainingsNeeded);
                current.setQualifiedStaff(qualifiedStaff);
                current.updateStaffChoices();
            }
        } else if (popup instanceof SCreationFrame) {
            SCreationFrame current = (SCreationFrame) popup;
            if (event.getSource() == current.getSendButton()) {
                int id = database.getNextId(ModeSelector.STAFF);
                database.getStaffTable().add(current.getUserInput(id));
                view.getStaffPanel().refreshTable();
                popup.setVisible(false);
                popup = null;
            }
        } else if (popup instanceof STrainingFrame) {
            STrainingFrame current = (STrainingFrame) popup;
            if (event.getSource() == current.getSendButton()) {
                current.trainStaff();
                view.getStaffPanel().refreshTable();
                popup.setVisible(false);
                popup = null;
            }
        }
    }

    // ===============================================
    // Helpers
    // ===============================================

    /**
     * Set the popup and display.
     * It will ignore the request if a popup is currently being displayed.
     *
     * @param view The next view to be displayed.
     */
    private void setPopupAndSetVisible(JFrame view) {
        if (popup == null || popup.isVisible() == false) {
            popup = view;
            popup.setVisible(true);
        }
    }

    /**
     * Uses the ModeSelector to serve different remover JOptionPane.
     *
     * @param mode ModeSelector REQUIREMENT or STAFF
     */
    private void createRemoverOptionPane(ModeSelector mode) {
        String modeName = (mode == ModeSelector.REQUIREMENT) ? "Lab" : "Staff";
        String result = JOptionPane.showInputDialog(null, "Which " + modeName.toLowerCase() + " (id) would you like to remove?");
        try {
            int id = Integer.parseInt(result);
            AssignmentElement item;
            if (mode == ModeSelector.REQUIREMENT) {
                item = database.getRequirementTable().find(id);
            } else {
                item = database.getStaffTable().find(id);
            }
            if (item == null) {
                JOptionPane.showMessageDialog(null, "Invalid input: " + modeName + " doesn't exist.",
                        "Operation Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to remove " + item.getDisplayString());
                if (confirm == JOptionPane.YES_OPTION) {
                    database.cleanlyRemove(item);
                    view.getStaffPanel().refreshTable();
                    view.getRequirementPanel().refreshTable();
                }
            }
        } catch (NumberFormatException e) {
            if (!result.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input.", "Operation Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Extracted function to parse training string to ArrayList. (separator: whitespace).
     *
     * @param input The raw String.
     * @return The ArrayList with the parse result.
     */
    public ArrayList<String> parseTrainingsString(String input) {
        ArrayList<String> output = new ArrayList<String>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            output.add(scanner.next());
        }
        return output;
    }

    // ===============================================
    // Write back on exit
    // ===============================================
    public static class ShutDownTask extends Thread {
        Database database;

        public ShutDownTask(Database database) {
            this.database = database;
        }

        public void run() {
            try {
                database.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
