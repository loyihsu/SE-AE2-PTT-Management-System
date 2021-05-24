package src.view.popups.staff;

import src.ApplicationController;
import src.database.types.Staff;
import src.view.components.JLabelAndComboBox;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class STrainingFrame extends JFrame {
    private final JLabelAndField skill;
    private final JLabelAndComboBox namePicker;
    private final JButton sendButton;
    private final ArrayList<Staff> nameList;
    private final ApplicationController parent;

    public STrainingFrame(ApplicationController controller) {
        parent = controller;
        nameList = controller.getDatabase().getStaffTable().getTable();

        // Setup window
        setSize(300, 200);
        setTitle("Train Staff");

        // Setup UI elements
        JPanel contentPanel = new JPanel();

        namePicker = new JLabelAndComboBox("Name", generateChoices(nameList), null);
        skill = new JLabelAndField("Skill to add");

        sendButton = new JButton("Train");
        sendButton.addActionListener(controller);

        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.add(namePicker);
        contentPanel.add(skill);

        this.setLayout(new BorderLayout());
        this.add(contentPanel);
        this.add(sendButton, BorderLayout.SOUTH);
    }

    // ===============================================
    // Getters and Helpers
    // ===============================================
    public JButton getSendButton() {
        return sendButton;
    }

    private ArrayList<String> generateChoices(ArrayList<Staff> input) {
        ArrayList<String> output = new ArrayList<String>();
        for (Staff item : input) {
            output.add("(" + item.getId() + ", " + item.getName() + ")");
        }
        return output;
    }

    public void trainStaff() {
        Staff staff = nameList.get(namePicker.getUserInput());
        for (String training : parent.parseTrainingsString(skill.getUserInput())) {
            staff.addTrainingReceived(training);
        }
    }
}
