package src.view.requirement;

import src.database.Database;
import src.database.types.*;
import src.view.components.JLabelAndComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssignStaffFrame extends JFrame {
    JLabelAndComboBox lab, staff;
    JButton sendButton;

    public AssignStaffFrame(ActionListener listener) {
        setSize(300, 200);
        setTitle("Assign Staff");


        // TODO
        lab = new JLabelAndComboBox("Lab ID", new ArrayList<String>());
        staff = new JLabelAndComboBox("Staff", new ArrayList<String>());

        sendButton = new JButton("Assign");
        sendButton.addActionListener(listener);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.add(lab);
        contentPanel.add(staff);

        this.setLayout(new BorderLayout());
        this.add(contentPanel);
        this.add(sendButton, BorderLayout.SOUTH);
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public Assignment getUserInput(Database database) {
        int sid = extractSid(staff.getUserInput());
        int rid = extractRid(lab.getUserInput());

        Staff staff = database.getStaffTable().find(sid);
        Requirement requirement = database.getRequirementTable().find(rid);

        return new Assignment(staff, requirement);
    }

    // TODO: Implement this
    private int extractSid(Object input) {
        return 1;
    }

    // TODO: Implement this
    private int extractRid(Object input) {
        return 1;
    }
}
