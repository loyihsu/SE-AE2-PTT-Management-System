package src.view.requirement;

import src.view.components.JLabelAndBox;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;

public class AddRequirementFrame extends JFrame {
    JLabelAndField staffNumber, startDate, endDate, skill;
    JLabelAndBox coursePicker;
    JButton sendButton;
    public AddRequirementFrame() {
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Staff");

        JPanel contentPanel = new JPanel();

        coursePicker = new JLabelAndBox("Course");

        staffNumber = new JLabelAndField("# of Staff Needed");
        startDate = new JLabelAndField("Start Date");
        endDate = new JLabelAndField("End Date");
        skill = new JLabelAndField("Skills Needed");

        sendButton = new JButton("Create");

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

}
