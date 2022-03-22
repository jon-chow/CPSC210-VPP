package ui.menus.ingame;

import ui.app.GuiApp;
import ui.menus.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.menus.JComponentBuilder.*;

// menu at the start of the game
public class GameMenu extends Menu {
    private JPanel topBarButtonContainer;
    private JPanel bottomBarButtonContainer;

    private PetEnvironment petEnvironment;

    // EFFECTS: constructs the main menu
    public GameMenu(GuiApp ui, JLayeredPane menu) throws IOException, FontFormatException {
        super(ui, menu);
        initMenu();
    }

    // EFFECTS: initializes assets to the game menu
    protected void initMenu() throws IOException, FontFormatException {
        ui.changeBackground("PixelPetBackground2.png");
        generateTopBar();
    }

    // EFFECTS: creates a prompt asking for the player's name
    private void generateTopBar() throws IOException, FontFormatException {
        ui.clearMenu();
        createTopBarItems();
        createEnvironmentContainer();
        createBottomBarItems();

        menu.add(topBarButtonContainer);
        menu.add(petEnvironment);
        menu.add(bottomBarButtonContainer);
        ui.pack();
    }

    // EFFECTS: creates the top bar with main menu and settings buttons
    private void createTopBarItems() throws IOException, FontFormatException {
        JButton mainMenuButton = createJButton("Main Menu", "mainMenuClicked", this,
                24f, 180, 50);
        mainMenuButton.setBackground(BUTTON_COLOR);

        JButton settingsButton = createJButton("Settings", "settingsClicked", this,
                24f, 160, 50);
        settingsButton.setBackground(BUTTON_COLOR);

        topBarButtonContainer = createJPanel(GAME_BAR_UI_COLOR, width, 50);
        topBarButtonContainer.setLayout(new GridBagLayout());
        topBarButtonContainer.add(mainMenuButton);
        topBarButtonContainer.add(Box.createHorizontalStrut(5));
        topBarButtonContainer.add(settingsButton);
    }

    // EFFECTS: creates the pixel pet environment
    private void createEnvironmentContainer() throws IOException {
        petEnvironment = new PetEnvironment(ui, width - 100, height - 150);
        petEnvironment.repaint();
    }

    // EFFECTS: creates the bottom bar with shop, inventory, and pet stats buttons
    private void createBottomBarItems() throws IOException, FontFormatException {
        JButton shopButton = createJButton("Shop", "shopClicked", this,
                24f, 100, 50);
        shopButton.setBackground(BUTTON_COLOR_2);

        JButton inventoryButton = createJButton("Inventory", "inventoryClicked", this,
                24f, 180, 50);
        inventoryButton.setBackground(BUTTON_COLOR_2);

        JButton petStatsButton = createJButton("Pet Stats", "petStatsClicked", this,
                24f, 180, 50);
        petStatsButton.setBackground(BUTTON_COLOR_2);

        bottomBarButtonContainer = createJPanel(GAME_BAR_UI_COLOR, width, 50);
        bottomBarButtonContainer.setLayout(new GridBagLayout());
        bottomBarButtonContainer.add(inventoryButton);
        bottomBarButtonContainer.add(Box.createHorizontalStrut(5));
        bottomBarButtonContainer.add(petStatsButton);
        bottomBarButtonContainer.add(Box.createHorizontalStrut(5));
        bottomBarButtonContainer.add(shopButton);
    }

    // TODO:
    // EFFECTS: helper for actionPerformed; performs the desired action
    protected void performAction(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("mainMenuClicked")) {
            System.out.println("prompting main menu");
        } else if (command.equals("settingsClicked")) {
            System.out.println("opening settings");
        } else if (command.equals("shopClicked")) {
            System.out.println("opening shop");
        } else if (command.equals("inventoryClicked")) {
            System.out.println("showing inventory");
        } else if (command.equals("petStatsClicked")) {
            System.out.println("showing pet status");
        }
    }

    // GETTER:
    public PetEnvironment getPetEnvironment() {
        return petEnvironment;
    }
}
