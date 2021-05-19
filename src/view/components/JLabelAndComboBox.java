package src.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JLabelAndComboBox extends JComponent {
    JLabel label;
    JComboBox box;

    public JLabelAndComboBox(String string, ArrayList<String> selections, ActionListener listener) {
        this.setLayout(new GridLayout(0, 2));
        label = new JLabel(string);
        box = new JComboBox();
        for (String selection : selections) {
            box.addItem(selection);
        }

        this.add(label);
        this.add(box);

        box.addActionListener(listener);
    }

    public int getUserInput() {
        return box.getSelectedIndex();
    }

    public void changeSelections(String[] selections) {
        box.setModel(new DefaultComboBoxModel(selections));
        box.repaint();
    }
}
