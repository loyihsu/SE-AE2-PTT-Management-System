package src.view.requirement;

import src.database.Database;
import src.database.types.*;
import src.view.components.JLabelAndBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AssignStaffFrame extends JFrame {
    JLabelAndBox lab, staff;
    JButton sendButton;

    public AssignStaffFrame(ActionListener listener) {
        setSize(300, 200);
        setTitle("Assign Staff");

        lab = new JLabelAndBox("Lab ID");
        staff = new JLabelAndBox("Staff");

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
