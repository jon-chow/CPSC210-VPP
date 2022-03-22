package ui.menus.mainmenu;

import ui.app.GuiApp;
import ui.menus.Menu;
import ui.menus.settings.LoadMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static model.configurables.FileLocations.*;
import static ui.app.PixelPetGame.STARTING_MONEY;

import static ui.configurables.JComponentBuilder.*;
import static ui.configurables.InterfaceAesthetics.*;

// menu at the start of the game
public class MainMenu extends Menu {
    private JButton newGameButton;
    private JButton loadButton;
    private JButton settingsButton;

    // EFFECTS: constructs the main menu
    public MainMenu(GuiApp ui, JLayeredPane menu) throws IOException, FontFormatException {
        super(ui, menu);
        initMenu();
    }

    // EFFECTS: initializes assets to the main menu
    protected void initMenu() throws IOException, FontFormatException {
        ui.changeBackground("PixelPetBackground.png");
        ui.clearMenu();

        BufferedImage logoBufferedImg = ImageIO.read(new File(assetsDir + "Logo.png"));
        Image logoImg = new ImageIcon(logoBufferedImg).getImage()
                .getScaledInstance(400, 240,  java.awt.Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(logoImg));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        menu.add(Box.createVerticalStrut(height / 12));
        menu.add(logo);
        menu.add(generateButtons());
        menu.getRootPane().setDefaultButton(newGameButton);
        ui.pack();
    }

    // EFFECTS: generates the panel for the menu buttons
    private JPanel generateButtons() throws IOException, FontFormatException {
        JPanel buttonsBox = createJPanel(TRANSPARENT,
                width / 3, height / 3);
        buttonsBox.setLayout(new GridLayout(3, 1,0,10));
        buttonsBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        createButtons();

        buttonsBox.add(newGameButton);
        buttonsBox.add(loadButton);
//        buttonsBox.add(settingsButton);

        return buttonsBox;
    }

    // EFFECTS: creates the new game, load, and settings buttons
    private void createButtons() throws IOException, FontFormatException {
        newGameButton = createJButton("New Game", "newGameButtonClicked",
                this, 24f, width, height);
        loadButton = createJButton("Load", "loadButtonClicked",
                this, 24f, width, height);
        settingsButton = createJButton("Settings", "settingsButtonClicked",
                this, 24f, width, height);
    }

    // EFFECTS: helper for actionPerformed; performs the desired action
    protected void performAction(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("newGameButtonClicked")) {
            new NewPlayerMenu(ui, menu, STARTING_MONEY);
        } else if (command.equals("loadButtonClicked")) {
            new LoadMenu(ui, menu);
        } else if (command.equals("settingsButtonClicked")) {
            System.out.println("settings");
        }
    }
}
