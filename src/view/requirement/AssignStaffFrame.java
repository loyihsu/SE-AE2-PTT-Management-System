package src.view.requirement;

import src.database.interfaces.Database;
import src.database.types.Assignment;
import src.database.types.Requirement;
import src.database.types.Staff;
import src.view.components.JLabelAndComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssignStaffFrame extends JFrame {
    JLabelAndComboBox lab, staff;
    JButton sendButton;
    Database database;
    ArrayList<Requirement> requirements;
    ArrayList<Staff> qualifiedStaff;

    public AssignStaffFrame(ActionListener listener, Database database) {
        setSize(300, 200);
        setTitle("Assign Staff");

        this.database = database;

        requirements = database.getRequirementTable().getTable();

        ActionListener selectionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Requirement item = requirements.get(lab.getUserInput());
                ArrayList<String> trainingsNeeded = item.getTrainingsNeeded();
                qualifiedStaff = database.getStaffTable().findWithSkills(trainingsNeeded);
                staff.changeSelections(convertToArray(generateStaffSelections(qualifiedStaff)));
            }

            private String[] convertToArray(ArrayList<String> input) {
                String[] output = new String[input.size()];
                for (int idx = 0, length = input.size(); idx < length; idx++) {
                    output[idx] = input.get(idx);
                }
                return output;
            }
        };

        lab = new JLabelAndComboBox("Lab", generateRequirementSelections(), selectionListener);

        staff = setupStaffAccordingToLab();

        sendButton = new JButton("Assign");
        sendButton.addActionListener(listener);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.add(lab);
        contentPanel.add(staff);

        this.setLayout(new BorderLayout());
        this.add(contentPanel);
        this.add(sendButton, BorderLayout.SOUTH);
    }

    private JLabelAndComboBox setupStaffAccordingToLab() {
        Requirement item = requirements.get(lab.getUserInput());
        ArrayList<String> trainingsNeeded = item.getTrainingsNeeded();
        qualifiedStaff = database.getStaffTable().findWithSkills(trainingsNeeded);
        return new JLabelAndComboBox("Staff", generateStaffSelections(qualifiedStaff), null);
    }

    private ArrayList<String> generateStaffSelections(ArrayList<Staff> qualifiedStaff) {
        ArrayList<String> output = new ArrayList<String>();

        for (Staff staff : qualifiedStaff) {
            output.add("(" + staff.getId() + ", " + staff.getName() + ")");
        }

        return output;
    }

    private ArrayList<String> generateRequirementSelections() {
        requirements = database.getRequirementsWithoutEnoughPeople();
        ArrayList<String> output = new ArrayList<String>();

        for (Requirement item : requirements) {
            output.add("(Lab " + item.getId() + ")");
        }

        return output;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public Assignment getUserInput(Database database) {
        Staff staff = qualifiedStaff.get(this.staff.getUserInput());
        Requirement requirement = requirements.get(lab.getUserInput());
        return new Assignment(staff, requirement);
    }
}
