package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TabContentPanel extends JPanel {
    JButton topButton, middleButton, lowerButton;

    public TabContentPanel(ActionListener listener, String topButtonLabel, String middleButtonLabel, String lowButtonLabel) {
        this.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();

        JLabel hello = new JLabel("Hello");
        contentPanel.add(hello);

        this.add(contentPanel, BorderLayout.CENTER);

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
