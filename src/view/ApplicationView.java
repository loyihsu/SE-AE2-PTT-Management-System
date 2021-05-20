package src.view;

import src.ApplicationController;
import src.view.components.ModeSelector;
import src.view.components.TabContentPanel;

import javax.swing.*;

public class ApplicationView extends JFrame {
    private final JTabbedPane tabbedPane;

    TabContentPanel requirementPanel, staffPanel;

    public ApplicationView(ApplicationController controller) {
        // Setup the window
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("PTT Management System");

        // Setup UI elements
        tabbedPane = new JTabbedPane();

        // Requirement Panel
        requirementPanel = new TabContentPanel(controller, ModeSelector.REQUIREMENT,
                "Add New Lab", "Assign Staff to Lab", "Remove Lab");
        tabbedPane.addTab("Labs", requirementPanel);

        // Staff Panel
        staffPanel = new TabContentPanel(controller, ModeSelector.STAFF,
                "Add New Staff", "Train Staff", "Remove Staff");
        tabbedPane.addTab("Staff", staffPanel);

        this.add(tabbedPane);
    }

    // ===============================================
    // Getters
    // ===============================================
    public TabContentPanel getRequirementPanel() {
        return requirementPanel;
    }

    public TabContentPanel getStaffPanel() {
        return staffPanel;
    }
}
