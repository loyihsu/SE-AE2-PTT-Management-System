package src.view.requirement;

import javax.swing.*;
import java.awt.*;

public class RequirementPanel extends JPanel {
    JLabel hello;

    public RequirementPanel() {
        this.setLayout(new BorderLayout());

        hello = new JLabel("Lab info (Lab ID, Course ID, ...) + assigned staff in table form.");
        this.add(hello, BorderLayout.CENTER);

        JPanel controlPanel = new RequirementControlPanel();
        this.add(controlPanel, BorderLayout.EAST);
    }

}
