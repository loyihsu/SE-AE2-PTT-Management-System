package src.view;

import src.ApplicationController;
import src.database.*;

import javax.swing.*;

public class ApplicationView extends JFrame {
    private DatabaseDecorator database;
    private ApplicationController controller;
    private JTabbedPane tabbedPane;

    TabContentPanel requirementPanel, staffPanel;

    public ApplicationView(ApplicationController controller, DatabaseDecorator database) {
        this.database = database;
        this.controller = controller;

        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Application");

        tabbedPane = new JTabbedPane();

        requirementPanel = new TabContentPanel(controller, "R", database, "Add New Lab", "Assign Staff to Lab", "Remove Lab");
        tabbedPane.addTab("Labs", requirementPanel);

        staffPanel = new TabContentPanel(controller, "S", database, "Add New Staff", "Train Staff", "Remove Staff");
        tabbedPane.addTab("Staff", staffPanel);

        this.add(tabbedPane);
    }

    public TabContentPanel getRequirementPanel() {
        return requirementPanel;
    }

    public TabContentPanel getStaffPanel() {
        return staffPanel;
    }
}
