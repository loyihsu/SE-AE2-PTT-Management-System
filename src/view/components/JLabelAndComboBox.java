package src.view.components;

import src.database.types.Staff;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JLabelAndComboBox extends JComponent {
    JLabel label;
    JComboBox box;
    public JLabelAndComboBox(String string, ArrayList<String> selections) {
        this.setLayout(new GridLayout(0, 2));
        label = new JLabel(string);
        box = new JComboBox();
        for (String selection: selections) {
            box.addItem(selection);
        }

        this.add(label);
        this.add(box);
    }
    public int getUserInput() {
        return box.getSelectedIndex();
    }
}
