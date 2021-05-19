package src.view.staff;

import src.database.types.Staff;
import src.database.types.builder.StaffBuilder;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class AddStaffFrame extends JFrame {
    JLabelAndField name, date, skill;
    JButton sendButton;

    public AddStaffFrame(ActionListener listener) {
        setSize(300, 200);
        setTitle("Add Staff");

        JPanel contentPanel = new JPanel();

        name = new JLabelAndField("Name");
        date = new JLabelAndField("Date of Joining");
        skill = new JLabelAndField("Skill to add");

        sendButton = new JButton("Create Staff");
        sendButton.addActionListener(listener);

        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.add(name);
        contentPanel.add(date);
        contentPanel.add(skill);

        this.setLayout(new BorderLayout());
        this.add(contentPanel);
        this.add(sendButton, BorderLayout.SOUTH);
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public Staff getUserInput(int id) {
        StaffBuilder staffBuilder = StaffBuilder.getInstance();

        return staffBuilder
                .setId(id)
                .setName(name.getUserInput())
                .setDateOfJoining(date.getUserInput())
                .setTrainingsReceived(parseTrainings(skill.getUserInput()))
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
