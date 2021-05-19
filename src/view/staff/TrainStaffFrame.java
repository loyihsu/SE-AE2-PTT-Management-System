package src.view.staff;

import src.database.Database;

import src.database.types.Staff;
import src.view.components.JLabelAndComboBox;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainStaffFrame extends JFrame {
    JLabelAndField skill;
    JLabelAndComboBox namePicker;
    JButton sendButton;
    ArrayList<Staff> nameList;

    Database database;

    public TrainStaffFrame(ActionListener listener, Database database) {
        setSize(300, 200);
        setTitle("Train Staff");

        this.database = database;

        JPanel contentPanel = new JPanel();

        nameList = database.getStaffTable().getTable();

        namePicker = new JLabelAndComboBox("Name", generateSelections(nameList), null);
        skill = new JLabelAndField("Skill to add");

        sendButton = new JButton("Train");
        sendButton.addActionListener(listener);

        contentPanel.setLayout(new GridLayout(0,1));
        contentPanel.add(namePicker);
        contentPanel.add(skill);

        this.setLayout(new BorderLayout());
        this.add(contentPanel);
        this.add(sendButton, BorderLayout.SOUTH);
    }

    private ArrayList<String> generateSelections(ArrayList<Staff> input) {
        ArrayList<String> output = new ArrayList<String>();

        for (Staff item: input) {
            output.add("(" + item.getId() + ", " + item.getName() + ")");
        }

        return output;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public void trainStaff(Database database) {
        Staff staff = nameList.get(namePicker.getUserInput());
        for (String training: parseTrainings(skill.getUserInput())) {
            staff.addTrainingReceived(training);
        }
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
