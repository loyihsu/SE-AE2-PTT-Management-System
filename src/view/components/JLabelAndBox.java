package src.view.components;

import javax.swing.*;
import java.awt.*;

public class JLabelAndBox extends JComponent {
    JLabel label;
    JComboBox box;
    public JLabelAndBox(String string) {
        this.setLayout(new GridLayout(0, 2));
        label = new JLabel(string);
        box = new JComboBox();
        this.add(label);
        this.add(box);
    }
    public Object getUserInput() {
        return box.getSelectedItem();
    }
}
