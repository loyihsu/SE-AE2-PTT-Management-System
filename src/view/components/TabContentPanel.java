package src.view.components;

import src.ApplicationController;
import src.view.ModeSelector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TabContentPanel extends JPanel {
    private final JButton topButton;
    private final JButton middleButton;
    private final JButton lowerButton;
    private final JTable table;
    private DefaultTableModel tableModel;
    private final ModeSelector selector;
    private final ApplicationController parent;

    public TabContentPanel(ApplicationController controller, ModeSelector modeSelector, String topButtonLabel, String middleButtonLabel, String lowButtonLabel) {
        parent = controller;

        // Setup panel
        this.setLayout(new BorderLayout());
        this.selector = modeSelector;

        // Setup table model
        tableModel = generateDataModel();

        // Setup UI elements
        // Table
        JScrollPane tableScrollPane;

        table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        this.add(tableScrollPane, BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();

        controlPanel.setLayout(new GridLayout(0, 1));

        topButton = new JButton(topButtonLabel);
        middleButton = new JButton(middleButtonLabel);
        lowerButton = new JButton(lowButtonLabel);

        topButton.addActionListener(controller);
        middleButton.addActionListener(controller);
        lowerButton.addActionListener(controller);

        controlPanel.add(topButton);
        controlPanel.add(middleButton);
        controlPanel.add(lowerButton);

        this.add(controlPanel, BorderLayout.EAST);
    }

    // ===============================================
    // Helpers
    // ===============================================
    public void refreshTable() {
        tableModel = generateDataModel();
        table.setModel(tableModel);
        table.repaint();
    }

    private DefaultTableModel generateDataModel() {
        String[] columns;
        // Select table headers and contents based on TableSelector
        if (selector == ModeSelector.REQUIREMENT) {
            String[] columnNames = {"Lab ID", "Course ID", "Number of Staff Needed", "Start Date", "End Date", "Trainings Needed", "Assigned Staff"};
            columns = columnNames;
        } else {
            String[] columnNames = {"Staff ID", "Name", "Date of Joining", "Trainings Received", "Responsible for Labs"};
            columns = columnNames;
        }
        Object[][] matrixData = (selector == ModeSelector.REQUIREMENT) ? parent.getDatabase().getRequirementsDisplayMatrix() : parent.getDatabase().getStaffDisplayMatrix();

        return new DefaultTableModel(matrixData, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    // ===============================================
    // Getters
    // ===============================================
    public JButton getTopButton() {
        return topButton;
    }

    public JButton getMiddleButton() {
        return middleButton;
    }

    public JButton getLowerButton() {
        return lowerButton;
    }
}
