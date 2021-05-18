package src.view.staff;

import src.view.components.JLabelAndBox;
import src.view.components.JLabelAndField;

import javax.swing.*;
import java.awt.*;

public class TrainStaffFrame extends JFrame {
    JLabelAndField skill;
    JLabelAndBox namePicker;
    JButton sendButton;

    public TrainStaffFrame() {
        setSize(300, 200);
        setTitle("Train Staff");

        JPanel contentPanel = new JPanel();

        namePicker = new JLabelAndBox("Name");
        skill = new JLabelAndField("Skill to add");

        sendButton = new JButton("Train");

        contentPanel.setLayout(new GridLayout(0,1));
        contentPanel.add(namePicker);
        contentPanel.add(skill);

        this.setLayout(new BorderLayout());
        this.add(contentPanel);
        this.add(sendButton, BorderLayout.SOUTH);
    }
}
