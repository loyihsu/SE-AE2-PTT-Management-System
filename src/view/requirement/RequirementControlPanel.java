package src.view.requirement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RequirementControlPanel extends JPanel {
    JButton newLab, assignStaff;

    public RequirementControlPanel(ActionListener controller) {
        this.setLayout(new GridLayout(0,1));
        newLab = new JButton("Add new Lab");
        newLab.addActionListener(controller);
        assignStaff = new JButton("Assign Staff to Lab");
        assignStaff.addActionListener(controller);
        this.add(newLab);
        this.add(assignStaff);
    }

    public JButton getNewLab() {
        return newLab;
    }

    public JButton getAssignStaff() {
        return assignStaff;
    }
}
