package ui.menus.ingame;

import ui.app.GuiApp;
import ui.menus.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// menu at the start of the game
public class GameMenu extends Menu {

    // EFFECTS: constructs the main menu
    public GameMenu(GuiApp ui, JLayeredPane menu) throws IOException, FontFormatException {
        super(ui, menu);
        initMenu();
    }

    // EFFECTS: initializes assets to the main menu
    public void initMenu() throws IOException, FontFormatException {

    }

    // EFFECTS: helper for actionPerformed; performs the desired action
    protected void performAction(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("")) {
        } else if (command.equals("")) {
        }
    }
}
