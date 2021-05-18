package src.view.requirement;

import src.view.components.JLabelAndBox;

import javax.swing.*;
import java.awt.*;

public class AssignStaffFrame extends JFrame {
    JLabelAndBox lab, staff;
    JButton sendButton;
    public AssignStaffFrame() {
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Assign Staff");

        lab = new JLabelAndBox("Lab ID");
        staff = new JLabelAndBox("Staff");
        sendButton = new JButton("Assign");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.add(lab);
        contentPanel.add(staff);

        this.setLayout(new BorderLayout());
        this.add(contentPanel);
        this.add(sendButton, BorderLayout.SOUTH);
    }
}
