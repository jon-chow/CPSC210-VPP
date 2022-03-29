package ui.menus.ingame;

import model.configurables.FileLocations;
import ui.app.GuiApp;
import ui.app.PixelPetGame;
import ui.menus.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.configurables.JComponentBuilder.*;

// menu at the start of the game
public class GameMenu extends Menu {
    public static final File persistenceFile = new File(FileLocations.persistenceDir);

    private JPanel topBarButtonContainer;
    private JPanel promptContainer;
    private JPanel bottomBarButtonContainer;

    private PetEnvironment petEnvironment;

    private JTextArea mainMenuPromptText;
    private JPanel mainMenuButtonContainer;

    private boolean isPetStatsMenuLoaded = false;
    private boolean isShopMenuLoaded = false;
    private boolean isInventoryMenuLoaded = false;

    // EFFECTS: constructs the main menu
    public GameMenu(GuiApp ui, JLayeredPane menu) throws IOException, FontFormatException {
        super(ui, menu);
        initMenu();
    }

    // EFFECTS: initializes assets to the game menu
    protected void initMenu() throws IOException, FontFormatException {
        ui.changeBackground("PixelPetBackground2.png");
        generateInterface();
    }

    // EFFECTS: generates the top bar, bottom bar, and game environment
    private void generateInterface() throws IOException, FontFormatException {
        ui.clearMenu();
        createTopBarItems();
        createEnvironmentContainer();
        createPromptContainer();
        createBottomBarItems();

        menu.add(topBarButtonContainer);
        menu.add(petEnvironment);
        menu.add(promptContainer);
        menu.add(bottomBarButtonContainer);
        ui.pack();
    }

    // EFFECTS: creates the top bar with main menu and settings buttons
    private void createTopBarItems() throws IOException, FontFormatException {
        JButton mainMenuButton = createJButton("Main Menu", "mainMenuClicked", this,
                24f, 180, 50);
        mainMenuButton.setBackground(BUTTON_COLOR);

//        JButton settingsButton = createJButton("Settings", "settingsClicked", this,
//                24f, 160, 50);
//        settingsButton.setBackground(BUTTON_COLOR);

        JButton saveButton = createJButton("Save", "saveClicked", this,
                24f, 160, 50);
        saveButton.setBackground(BUTTON_COLOR);

        topBarButtonContainer = createJPanel(GAME_BAR_UI_COLOR, width, 50);
        topBarButtonContainer.setLayout(new GridBagLayout());
        topBarButtonContainer.add(mainMenuButton);
        topBarButtonContainer.add(Box.createHorizontalStrut(5));
        topBarButtonContainer.add(saveButton);
//        topBarButtonContainer.add(settingsButton);
    }

    // EFFECTS: creates the pixel pet environment
    private void createEnvironmentContainer() throws IOException, FontFormatException {
        petEnvironment = new PetEnvironment(ui, width - 100, height - 150);
        petEnvironment.repaint();
    }

    // EFFECTS: creates the container for prompts
    private void createPromptContainer() {
        promptContainer = createJPanel(TRANSPARENT, width, height);
        promptContainer.setVisible(false);
    }

    // EFFECTS: creates the bottom bar with shop, inventory, and pet stats buttons
    private void createBottomBarItems() throws IOException, FontFormatException {
        JButton shopButton = createJButton("Shop", "shopClicked", this,
                24f, 100, 50);
        shopButton.setBackground(BUTTON_COLOR);

        JButton inventoryButton = createJButton("Inventory", "inventoryClicked", this,
                24f, 180, 50);
        inventoryButton.setBackground(BUTTON_COLOR);

        JButton petStatsButton = createJButton("Pet Stats", "petStatsClicked", this,
                24f, 180, 50);
        petStatsButton.setBackground(BUTTON_COLOR);

        bottomBarButtonContainer = createJPanel(GAME_BAR_UI_COLOR, width, 50);
        bottomBarButtonContainer.setLayout(new GridBagLayout());
        bottomBarButtonContainer.add(inventoryButton);
        bottomBarButtonContainer.add(Box.createHorizontalStrut(5));
        bottomBarButtonContainer.add(petStatsButton);
        bottomBarButtonContainer.add(Box.createHorizontalStrut(5));
        bottomBarButtonContainer.add(shopButton);
    }

    // EFFECTS: toggles the visibility of the prompts
    //          toggling opposite the visibility of the top/bottom bar, pet environment
    public void togglePrompts(boolean isVisibile) {
        promptContainer.setVisible(isVisibile);

        topBarButtonContainer.setVisible(!isVisibile);
        petEnvironment.setVisible(!isVisibile);
        bottomBarButtonContainer.setVisible(!isVisibile);
    }

    // EFFECTS: creates a prompt asking if the player wants to return to the main menu
    private void generateMainMenuPrompt() throws IOException, FontFormatException {
        promptContainer.removeAll();
        createMainMenuPromptText();

        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));
        promptContainer.add(Box.createVerticalStrut(height / 4));
        promptContainer.add(mainMenuPromptText);
        promptContainer.add(Box.createVerticalStrut(10));
        promptContainer.add(mainMenuButtonContainer);
        togglePrompts(true);
    }

    // EFFECTS: creates the main menu prompt text components
    private void createMainMenuPromptText() throws IOException, FontFormatException {
        mainMenuPromptText = createJTextArea("Are you sure you'd like to return to the main menu?"
                        + "\n\nNote: Be sure you have saved your game before continuing!",
                28f, width - 100, 220);

        JButton cancelButton = createJButton("Cancel", "cancelMainMenuClicked", this,
                24f, 100, 50);
        cancelButton.setBackground(BUTTON_COLOR_2);

        JButton confirmButton = createJButton("Confirm", "confirmMainMenuClicked", this,
                24f, 100, 50);
        confirmButton.setBackground(BUTTON_COLOR_2);

        mainMenuButtonContainer = createJPanel(TRANSPARENT, width, height);
        mainMenuButtonContainer.add(cancelButton);
        mainMenuButtonContainer.add(confirmButton);
        menu.getRootPane().setDefaultButton(confirmButton);
    }

    // MODIFIES: this
    // EFFECTS: renders the ui graphics for the gameMenu
    public void renderGraphics(PixelPetGame game) {
//        petEnvironment.renderGraphics(game);

        if (isPetStatsMenuLoaded) {
            PetStatsMenu.updatePetStats(ui);
        }

        if (isShopMenuLoaded) {
            ShopMenu.updateShopMenu(ui);
        }

        if (isInventoryMenuLoaded) {
            InventoryMenu.updateInventoryMenu(ui);
        }
    }

    // EFFECTS: helper for actionPerformed; performs the desired action
    protected void performAction(String command, JComponent source) throws IOException, FontFormatException {
        performActionMainMenu(command, source);
        performActionSaveMenu(command, source);
        performActionShopMenu(command, source);
        performActionInventoryMenu(command, source);
        performActionPetStatsMenu(command, source);

        if (command.equals("cancelMainMenuClicked") || command.equals("cancelSaveClicked")
                || command.equals("continueSaveSuccessfulClicked") || command.equals("backClicked")) {
            togglePrompts(false);
        }
    }

    // EFFECTS: helper for performAction; main menu specific
    private void performActionMainMenu(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("mainMenuClicked")) {
            generateMainMenuPrompt();
        } else if (command.equals("confirmMainMenuClicked")) {
            ui.returnToMainMenu();
        }
    }

    // EFFECTS: helper for performAction; save menu specific
    private void performActionSaveMenu(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("saveClicked")) {
            SaveMenu.generateSavePrompt(menu, this, promptContainer, width, height);
        } else if (command.equals("confirmSaveClicked")) {
            SaveMenu.displaySaveSuccessful(ui, menu, this, promptContainer, width, height);
        }
    }

    // EFFECTS: helper for performAction; shop menu specific
    private void performActionShopMenu(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("shopClicked")) {
            ShopMenu.generateShop(ui, menu, this, promptContainer, width, height);
        } else if (command.equals("shopItemBuyClicked")) {
            ShopMenu.buyItem(ui);
            ShopMenu.createShopItemsPanel(ui, width, 20f);
        }
    }

    // EFFECTS: helper for performAction; inventory menu specific
    private void performActionInventoryMenu(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("inventoryClicked")) {
            InventoryMenu.generateInventory(ui, menu, this, promptContainer, width, height);
        } else if (command.equals("inventoryItemUseClicked")) {
            InventoryMenu.giveItemToPet(ui);
            InventoryMenu.createInventoryItemsPanel(ui, width, 20f);
        }
    }

    // EFFECTS: helper for performAction; pet stats menu specific
    private void performActionPetStatsMenu(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("petStatsClicked")) {
            PetStatsMenu.generatePetStats(ui, menu, this, promptContainer, width, height);
        }
    }

    // GETTER:
//    public PetEnvironment getPetEnvironment() {
//        return petEnvironment;
//    }

    // SETTER:
    public void setPetStatsMenuLoaded(boolean petStatsMenuLoaded) {
        isPetStatsMenuLoaded = petStatsMenuLoaded;
    }

    public void setShopMenuLoaded(boolean shopMenuLoaded) {
        isShopMenuLoaded = shopMenuLoaded;
    }

    public void setInventoryMenuLoaded(boolean inventoryMenuLoaded) {
        isInventoryMenuLoaded = inventoryMenuLoaded;
    }
}