package ui.menus;

import ui.app.GuiApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents an abstract menu
public abstract class Menu implements ActionListener {
    protected int width;
    protected int height;

    protected GuiApp ui;
    protected JLayeredPane menu;

    // EFFECTS: constructs an abstract menu
    public Menu(GuiApp ui, JLayeredPane menu) {
        width = ui.getWidth();
        height = ui.getHeight();
        this.ui = ui;
        this.menu = menu;
    }

    // EFFECTS: initiates the menu
    protected abstract void initMenu() throws Exception;

    // EFFECTS: triggers when an event from a JComponent is called
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        JComponent source = (JComponent) e.getSource();

        try {
            performAction(command, source);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ui.revalidate();
        ui.repaint();
        ui.pack();
    }

    // EFFECTS: helper for actionPerformed; performs the desired action
    protected abstract void performAction(String command, JComponent source) throws Exception;
}
