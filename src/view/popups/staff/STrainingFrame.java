package src.view.popups.staff;

import src.ApplicationController;
import src.database.interfaces.Database;
import src.database.types.Staff;
import src.view.components.JLabelAndComboBox;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class STrainingFrame extends JFrame {
    JLabelAndField skill;
    JLabelAndComboBox namePicker;
    JButton sendButton;
    ArrayList<Staff> nameList;
    ApplicationController parent;
    Database database;

    public STrainingFrame(ApplicationController controller) {
        parent = controller;
        database = controller.getDatabase();
        nameList = database.getStaffTable().getTable();

        // Setup window
        setSize(300, 200);
        setTitle("Train Staff");

        // Setup UI elements
        JPanel contentPanel = new JPanel();

        namePicker = new JLabelAndComboBox("Name", generateSelections(nameList), null);
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

    private ArrayList<String> generateSelections(ArrayList<Staff> input) {
        ArrayList<String> output = new ArrayList<String>();
        for (Staff item : input) {
            output.add("(" + item.getId() + ", " + item.getName() + ")");
        }
        return output;
    }

    public void trainStaff(Database database) {
        Staff staff = nameList.get(namePicker.getUserInput());
        for (String training : parent.parseTrainingsString(skill.getUserInput())) {
            staff.addTrainingReceived(training);
        }
    }
}
