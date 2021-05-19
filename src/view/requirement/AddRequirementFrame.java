package src.view.requirement;

import src.database.types.Requirement;
import src.database.types.builder.RequirementBuilder;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class AddRequirementFrame extends JFrame {
    JLabelAndField course, staffNumber, startDate, endDate, skill;
    JButton sendButton;

    public AddRequirementFrame(ActionListener listener) {
        setSize(300, 200);
        setTitle("Add Staff");

        JPanel contentPanel = new JPanel();

        course = new JLabelAndField("Course");
        staffNumber = new JLabelAndField("# of Staff Needed");
        startDate = new JLabelAndField("Start Date");
        endDate = new JLabelAndField("End Date");
        skill = new JLabelAndField("Skills Needed");

        sendButton = new JButton("Create");
        sendButton.addActionListener(listener);

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

    public JButton getSendButton() {
        return sendButton;
    }

    public Requirement getUserInput(int id) {
        RequirementBuilder builder = RequirementBuilder.getInstance();

        return builder
                .setId(id)
                .setCourseId(Integer.parseInt(course.getUserInput()))
                .setStartDate(startDate.getUserInput())
                .setEndDate(endDate.getUserInput())
                .setNumberOfStaffNeeded(Integer.parseInt(staffNumber.getUserInput()))
                .setTrainingsNeeded(parseTrainings(skill.getUserInput()))
                .build();
    }

    private ArrayList<String> parseTrainings(String input) {
        ArrayList<String> output = new ArrayList<String>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            output.add(scanner.next());
        }
        return output;
    }
}
