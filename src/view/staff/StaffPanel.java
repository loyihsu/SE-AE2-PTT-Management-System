package src.view.staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StaffPanel extends JPanel {
    JLabel world;
    StaffControlPanel controlPanel;

    public StaffPanel(ActionListener controller) {
        this.setLayout(new BorderLayout());

        world = new JLabel("Staff information such as id, name... and assigned to in table form.");
        this.add(world, BorderLayout.CENTER);

        controlPanel = new StaffControlPanel(controller);
        this.add(controlPanel, BorderLayout.EAST);
    }

    public StaffControlPanel getControlPanel() {
        return controlPanel;
    }
}
