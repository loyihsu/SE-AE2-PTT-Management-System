package src.view.components;

import javax.swing.*;
import java.awt.*;

public class JLabelAndField extends JComponent {
    JLabel label;
    JTextField textField;
    public JLabelAndField(String string) {
        this.setLayout(new GridLayout(0, 2));
        label = new JLabel(string);
        textField = new JTextField();
        this.add(label);
        this.add(textField);
    }
}
