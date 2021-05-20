package src.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JLabelAndComboBox extends JComponent {
    private final JLabel label;
    private final JComboBox box;

    public JLabelAndComboBox(String string, ArrayList<String> selections, ActionListener listener) {
        // Setup Component
        this.setLayout(new GridLayout(0, 2));

        // Setup UI elements
        label = new JLabel(string);
        box = new JComboBox();

        for (String selection : selections) {
            box.addItem(selection);
        }

        this.add(label);
        this.add(box);

        box.addActionListener(listener);
    }

    // ===============================================
    // Getter and Helpers
    // ===============================================
    public JComboBox getBox() {
        return box;
    }

    /**
     * Get the index of selected item.
     *
     * @return Index of the item selected.
     */
    public int getUserInput() {
        return box.getSelectedIndex();
    }

    /**
     * Change choices to the contents of the String[] provided.
     *
     * @param choices The choices in String[] format.
     */
    public void changeChoices(String[] choices) {
        box.setModel(new DefaultComboBoxModel(choices));
        box.repaint();
    }
}
