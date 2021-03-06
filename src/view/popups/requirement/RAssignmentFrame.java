package src.view.popups.requirement;

import src.ApplicationController;
import src.database.interfaces.Database;
import src.database.types.Assignment;
import src.database.types.Requirement;
import src.database.types.Staff;
import src.view.components.JLabelAndComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RAssignmentFrame extends JFrame {
    private final JLabelAndComboBox lab;
    private final JLabelAndComboBox staff;
    private final JButton sendButton;
    private final Database database;
    private ArrayList<Requirement> requirements;
    private ArrayList<Staff> qualifiedStaff;

    public RAssignmentFrame(ApplicationController controller) {
        database = controller.getDatabase();
        requirements = database.getRequirementTable().getTable();

        // Setup window
        setSize(300, 150);
        setTitle("Assign Staff");

        // Setup UI elements
        lab = new JLabelAndComboBox("Lab", generateRequirementChoices(), controller);

        Requirement item = requirements.get(lab.getUserInput());
        ArrayList<String> trainingsNeeded = item.getTrainingsNeeded();
        qualifiedStaff = database.getStaffTable().findWithSkills(trainingsNeeded);
        staff = new JLabelAndComboBox("Staff", generateStaffChoices(), null);

        sendButton = new JButton("Assign");
        sendButton.addActionListener(controller);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.add(lab);
        contentPanel.add(staff);

        this.setLayout(new BorderLayout());
        this.add(contentPanel);
        this.add(sendButton, BorderLayout.SOUTH);
    }

    // ===============================================
    // Helpers
    // ===============================================
    private ArrayList<String> generateRequirementChoices() {
        requirements = database.getRequirementsWithoutEnoughPeople();
        ArrayList<String> output = new ArrayList<String>();
        for (Requirement item : requirements) {
            output.add("(Lab " + item.getId() + ")");
        }
        return output;
    }

    private ArrayList<String> generateStaffChoices() {
        ArrayList<String> output = new ArrayList<String>();
        for (Staff staff : qualifiedStaff) {
            output.add("(" + staff.getId() + ", " + staff.getName() + ")");
        }
        return output;
    }

    public void updateStaffChoices() {
        ArrayList<String> selections = generateStaffChoices();
        String[] array = selections.toArray(new String[selections.size()]);
        staff.changeChoices(array);
    }

    public Assignment getUserInput() {
        Staff staff = qualifiedStaff.get(this.staff.getUserInput());
        Requirement requirement = requirements.get(lab.getUserInput());
        return new Assignment(staff, requirement);
    }

    // ===============================================
    // Getters and Setters
    // ===============================================
    public JButton getSendButton() {
        return sendButton;
    }

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    public JLabelAndComboBox getLab() {
        return lab;
    }

    public JLabelAndComboBox getStaff() {
        return staff;
    }

    public void setQualifiedStaff(ArrayList<Staff> qualifiedStaff) {
        this.qualifiedStaff = qualifiedStaff;
    }
}
