package src.view.requirement;

import javax.swing.*;
import java.awt.*;

public class RequirementControlPanel extends JPanel {
    JButton button1, button2;

    public RequirementControlPanel() {
        this.setLayout(new GridLayout(0,1));
        button1 = new JButton("Add new Lab");
        button2 = new JButton("Assign Staff to Lab");
        this.add(button1);
        this.add(button2);
    }
}
