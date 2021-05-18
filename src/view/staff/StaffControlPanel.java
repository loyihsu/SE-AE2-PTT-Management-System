package src.view.staff;

import javax.swing.*;
import java.awt.*;

public class StaffControlPanel extends JPanel {
    JButton button1, button2;

    public StaffControlPanel() {
        this.setLayout(new GridLayout(0, 1));
        button1 = new JButton("Add new Staff");
        button2 = new JButton("Train Staff");
        this.add(button1);
        this.add(button2);

    }
}
