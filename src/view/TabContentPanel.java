package src.view;

import src.database.interfaces.tables.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class TabContentPanel extends JPanel {
    JButton topButton, middleButton, lowerButton;
    JTable table;
    DefaultTableModel tableModel;
    TableSelector selector;
    Database database;

    public TabContentPanel(ActionListener listener, TableSelector tableSelector, Database database, String topButtonLabel, String middleButtonLabel, String lowButtonLabel) {
        this.setLayout(new BorderLayout());
        this.selector = tableSelector;
        this.database = database;

        drawTable();

        JPanel controlPanel = new JPanel();

        controlPanel.setLayout(new GridLayout(0, 1));

        topButton = new JButton(topButtonLabel);
        middleButton = new JButton(middleButtonLabel);
        lowerButton = new JButton(lowButtonLabel);

        topButton.addActionListener(listener);
        middleButton.addActionListener(listener);
        lowerButton.addActionListener(listener);

        controlPanel.add(topButton);
        controlPanel.add(middleButton);
        controlPanel.add(lowerButton);

        this.add(controlPanel, BorderLayout.EAST);
    }

    private void drawTable() {
        String[] columns;
        if (selector == TableSelector.REQUIREMENT) {
            String[] columnNames = {"Lab ID", "Course ID", "Number of Staff Needed", "Start Date", "End Date", "Trainings Needed", "Assigned Staff"};
            columns = columnNames;
        } else {
            String[] columnNames = {"Staff ID", "Name", "Date of Joining", "Trainings Received", "Responsible for Labs"};
            columns = columnNames;
        }
        Object[][] matrixData = (selector == TableSelector.REQUIREMENT) ? database.getRequirementsDisplayMatrix() : database.getStaffDisplayMatrix();

        tableModel = new DefaultTableModel(matrixData, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane tableScrollPane;
        table = new JTable(tableModel);
        table.setModel(tableModel);
        tableScrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        this.add(tableScrollPane, BorderLayout.CENTER);
    }

    public void refreshTable() {
        String[] columns;
        if (selector == TableSelector.REQUIREMENT) {
            String[] columnNames = {"Lab ID", "Course ID", "Number of Staff Needed", "Start Date", "End Date", "Trainings Needed", "Assigned Staff"};
            columns = columnNames;
        } else {
            String[] columnNames = {"Staff ID", "Name", "Date of Joining", "Trainings Received", "Responsible for Labs"};
            columns = columnNames;
        }
        Object[][] matrixData = (selector == TableSelector.REQUIREMENT) ? database.getRequirementsDisplayMatrix() : database.getStaffDisplayMatrix();

        tableModel = new DefaultTableModel(matrixData, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(tableModel);
        table.repaint();
    }

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
