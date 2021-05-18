package src.view;

import src.ApplicationController;
import src.database.*;
import src.view.requirement.RequirementPanel;
import src.view.staff.StaffPanel;

import javax.swing.*;

public class ApplicationView extends JFrame {
    private Database database;
    private ApplicationController controller;
    private JTabbedPane tabbedPane;
    RequirementPanel requirementPanel;
    StaffPanel staffPanel;

    public ApplicationView(ApplicationController c, Database db) {
        database = db;
        controller = c;

        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Application");

        tabbedPane = new JTabbedPane();
        requirementPanel = new RequirementPanel(c);
        tabbedPane.addTab("Labs", requirementPanel);
        staffPanel = new StaffPanel(c);
        tabbedPane.addTab("Staff", staffPanel);

        this.add(tabbedPane);
    }

    public RequirementPanel getRequirementPanel() {
        return requirementPanel;
    }

    public StaffPanel getStaffPanel() {
        return staffPanel;
    }
}
