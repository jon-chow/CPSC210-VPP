package ui.menus;

import ui.configurables.InterfaceAesthetics;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

// a class for building preset JComponents
public class JComponentBuilder {

    // EFFECTS: returns a JPanel with specified color and dimensions
    public static JPanel createJPanel(Color backgroundColor, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setMaximumSize(new Dimension(width, height));

        return panel;
    }

    // EFFECTS: returns a JButton with text, events, specified dimensions
    public static JButton createJButton(String text, String actionCommand,
                                        ActionListener listener, float fontSize, int width, int height)
            throws IOException, FontFormatException {

        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
        button.setBackground(InterfaceAesthetics.BUTTON_COLOR);
        button.setBorder(new LineBorder(InterfaceAesthetics.BUTTON_BORDER_COLOR, 4));
        button.setSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setFont(Font.createFont(Font.PLAIN, InterfaceAesthetics.fontFile).deriveFont(fontSize));

        return button;
    }

    // EFFECTS: returns a JTextField with text and specified dimensions
    public static JTextField createJTextField(String text, float fontSize, int width, int height,
                                              int textHorizAlignment, boolean editable)
            throws IOException, FontFormatException {
        JTextField textField = new JTextField(text);
        textField.setBackground(InterfaceAesthetics.BUTTON_COLOR);
        textField.setBorder(new LineBorder(InterfaceAesthetics.BUTTON_BORDER_COLOR, 4));
        textField.setMaximumSize(new Dimension(width, height));
        textField.setHorizontalAlignment(textHorizAlignment);
        textField.setFont(Font.createFont(Font.PLAIN, InterfaceAesthetics.fontFile).deriveFont(fontSize));
        textField.setEditable(editable);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (editable) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().equals("") && editable) {
                    textField.setText(text);
                }
            }
        });

        return textField;
    }

    // EFFECTS: returns a JTextArea with text and specified dimensions
    public static JTextArea createJTextArea(String text, float fontSize, int width, int height)
            throws IOException, FontFormatException {
        JTextArea textArea = new JTextArea(text);
        textArea.setBackground(InterfaceAesthetics.BUTTON_COLOR);
        textArea.setBorder(new LineBorder(InterfaceAesthetics.BUTTON_BORDER_COLOR, 4));
        textArea.setMaximumSize(new Dimension(width, height));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(Font.createFont(Font.PLAIN, InterfaceAesthetics.fontFile).deriveFont(fontSize));
        textArea.setEditable(false);

        return textArea;
    }
}
