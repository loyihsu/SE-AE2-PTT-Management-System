package src.view.popups.staff;

import src.ApplicationController;
import src.database.types.Staff;
import src.database.types.builder.StaffBuilder;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;

public class SCreationFrame extends JFrame {
    JLabelAndField name, date, skill;
    JButton sendButton;
    ApplicationController parent;

    public SCreationFrame(ApplicationController controller) {
        parent = controller;

        // Setup window
        setSize(300, 200);
        setTitle("Add Staff");

        // Setup UI elements
        JPanel contentPanel = new JPanel();

        name = new JLabelAndField("Name");
        date = new JLabelAndField("Date of Joining");
        skill = new JLabelAndField("Skill to add");

        sendButton = new JButton("Create Staff");
        sendButton.addActionListener(controller);

        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.add(name);
        contentPanel.add(date);
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

    public Staff getUserInput(int id) {
        return StaffBuilder
                .getInstance()
                .setId(id)
                .setName(name.getUserInput())
                .setDateOfJoining(date.getUserInput())
                .setTrainingsReceived(parent.parseTrainingsString(skill.getUserInput()))
                .build();

    }
}
