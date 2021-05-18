package src.view.staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StaffControlPanel extends JPanel {
    private JButton addStaff, trainStaff;

    public StaffControlPanel(ActionListener controller) {
        this.setLayout(new GridLayout(0, 1));
        addStaff = new JButton("Add new Staff");
        addStaff.addActionListener(controller);
        trainStaff = new JButton("Train Staff");
        trainStaff.addActionListener(controller);
        this.add(addStaff);
        this.add(trainStaff);
    }

    public JButton getAddStaff() {
        return addStaff;
    }

    public JButton getTrainStaff() {
        return trainStaff;
    }
}
