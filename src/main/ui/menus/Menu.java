package ui.menus;

import ui.app.GuiApp;

import javax.swing.*;
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
}
