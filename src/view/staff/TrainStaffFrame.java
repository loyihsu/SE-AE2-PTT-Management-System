package src.view.staff;

import src.database.Database;

import src.database.types.Staff;
import src.view.components.JLabelAndBox;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrainStaffFrame extends JFrame {
    JLabelAndField skill;
    JLabelAndBox namePicker;
    JButton sendButton;

    public TrainStaffFrame(ActionListener listener) {
        setSize(300, 200);
        setTitle("Train Staff");

        JPanel contentPanel = new JPanel();

        namePicker = new JLabelAndBox("Name");
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

    public JButton getSendButton() {
        return sendButton;
    }

    public void trainStaff(Database database) {
        int sid = selectStaffId(namePicker.getUserInput());
        Staff staff = database.getStaffTable().find(sid);

        for (String training: parseTrainings(skill.getUserInput())) {
            staff.addTrainingReceived(training);
        }
    }

    // TODO: Implement this
    private int selectStaffId(Object input) {
        return 1;
    }

    // TODO: Implement this
    private ArrayList<String> parseTrainings(String input) {
        return new ArrayList<String>();
    }
}
