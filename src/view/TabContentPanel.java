package src.view;

import src.database.DatabaseDecorator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TabContentPanel extends JPanel {
    JScrollPane tableScrollPane;
    JButton topButton, middleButton, lowerButton;

    public TabContentPanel(ActionListener listener, String mode, DatabaseDecorator databaseDecorator, String topButtonLabel, String middleButtonLabel, String lowButtonLabel) {
        this.setLayout(new BorderLayout());

        drawTable(databaseDecorator, mode);
        this.add(tableScrollPane, BorderLayout.CENTER);

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

    public void drawTable(DatabaseDecorator databaseDecorator, String mode) {
        String[] columns;
        if (mode.equals("R")) {
            String[] columnNames = {"Lab ID", "Course ID", "Number of Staff Needed", "Start Date", "End Date", "Trainings Needed", "Assigned Staff"};
            columns = columnNames;
        } else {
            String[] columnNames = {"Staff ID", "Name", "Date of Joining", "Trainings Received", "Responsible for Labs"};
            columns = columnNames;
        }

        Object[][] matrixData = {{"1", "1", "1", "1", "1", "1", "1"}};
//        Object[][] matrixData = (mode == "R") ? databaseDecorator.getRequirementsDisplayMatrix() : databaseDecorator.getStaffDisplayMatrix();

        JTable table = new JTable(matrixData, columns);
        tableScrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
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
