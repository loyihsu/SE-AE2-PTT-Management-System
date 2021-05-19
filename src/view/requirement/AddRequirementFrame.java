package src.view.requirement;

import src.database.types.Requirement;
import src.database.types.builder.RequirementBuilder;
import src.view.components.JLabelAndBox;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRequirementFrame extends JFrame {
    JLabelAndField staffNumber, startDate, endDate, skill;
    JLabelAndBox coursePicker;
    JButton sendButton;
    public AddRequirementFrame(ActionListener listener) {
        setSize(300, 200);
        setTitle("Add Staff");

        JPanel contentPanel = new JPanel();

        coursePicker = new JLabelAndBox("Course");

        staffNumber = new JLabelAndField("# of Staff Needed");
        startDate = new JLabelAndField("Start Date");
        endDate = new JLabelAndField("End Date");
        skill = new JLabelAndField("Skills Needed");

        sendButton = new JButton("Create");
        sendButton.addActionListener(listener);

        contentPanel.setLayout(new GridLayout(0,1));
        contentPanel.add(coursePicker);
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
                .setCourseId(courseIdSelector(coursePicker.getUserInput()))
                .setStartDate(startDate.getUserInput())
                .setEndDate(endDate.getUserInput())
                .setTrainingsNeeded(parseTrainings(skill.getUserInput()))
                .build();
    }

    // TODO: Implement this
    private int courseIdSelector(Object input) {
        return 0;
    }

    // TODO: Implement this
    private ArrayList<String> parseTrainings(String input) {
        return new ArrayList<String>();
    }
}
