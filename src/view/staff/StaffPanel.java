package src.view.staff;

import javax.swing.*;
import java.awt.*;

public class StaffPanel extends JPanel {
    JLabel world;

    public StaffPanel() {
        this.setLayout(new BorderLayout());

        world = new JLabel("Staff information such as id, name... and assigned to in table form.");
        this.add(world, BorderLayout.CENTER);

        JPanel controlPanel = new StaffControlPanel();
        this.add(controlPanel, BorderLayout.EAST);
    }
}
