package src.view.requirement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RequirementPanel extends JPanel {
    JLabel hello;
    RequirementControlPanel controlPanel;

    public RequirementPanel(ActionListener controller) {
        this.setLayout(new BorderLayout());

        hello = new JLabel("Lab info (Lab ID, Course ID, ...) + assigned staff in table form.");
        this.add(hello, BorderLayout.CENTER);

        controlPanel = new RequirementControlPanel(controller);
        this.add(controlPanel, BorderLayout.EAST);
    }

    public RequirementControlPanel getControlPanel() {
        return controlPanel;
    }
}
