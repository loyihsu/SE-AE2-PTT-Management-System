package src.view.popups.requirement;

import src.ApplicationController;
import src.database.types.Requirement;
import src.database.types.builder.RequirementBuilder;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;

public class RCreationFrame extends JFrame {
    private final JLabelAndField course;
    private final JLabelAndField staffNumber;
    private final JLabelAndField startDate;
    private final JLabelAndField endDate;
    private final JLabelAndField skill;
    private final JButton sendButton;
    private final ApplicationController parent;

    public RCreationFrame(ApplicationController controller) {
        parent = controller;

        // Setup window
        setSize(300, 200);
        setTitle("Add Lab");

        // Setup UI elements
        JPanel contentPanel = new JPanel();

        course = new JLabelAndField("Course");
        staffNumber = new JLabelAndField("# of Staff Needed");
        startDate = new JLabelAndField("Start Date");
        endDate = new JLabelAndField("End Date");
        skill = new JLabelAndField("Skills Needed");

        sendButton = new JButton("Create");
        sendButton.addActionListener(controller);

        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.add(course);
        contentPanel.add(staffNumber);
        contentPanel.add(startDate);
        contentPanel.add(endDate);
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

    public Requirement getUserInput(int id) {
        return RequirementBuilder
                .getInstance()
                .setId(id)
                .setCourseId(Integer.parseInt(course.getUserInput()))
                .setStartDate(startDate.getUserInput())
                .setEndDate(endDate.getUserInput())
                .setNumberOfStaffNeeded(Integer.parseInt(staffNumber.getUserInput()))
                .setTrainingsNeeded(parent.parseTrainingsString(skill.getUserInput()))
                .build();
    }
}
