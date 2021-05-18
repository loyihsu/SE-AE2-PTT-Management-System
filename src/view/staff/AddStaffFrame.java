package src.view.staff;

import src.view.components.JLabelAndField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddStaffFrame extends JFrame {
    JLabelAndField name, date, skill;
    JButton sendButton;
    public AddStaffFrame() {
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Staff");

        JPanel contentPanel = new JPanel();

        name = new JLabelAndField("Name");
        date = new JLabelAndField("Date of Joining");
        skill = new JLabelAndField("Skill to add");

        sendButton = new JButton("Create Staff");

        contentPanel.setLayout(new GridLayout(0,1));
        contentPanel.add(name);
        contentPanel.add(date);
        contentPanel.add(skill);

        this.setLayout(new BorderLayout());
        this.add(contentPanel);
        this.add(sendButton, BorderLayout.SOUTH);
    }
}
