package ui.menus.ingame;

import model.Player;
import model.configurables.FileLocations;
import model.goodsandservices.Item;
import model.goodsandservices.Shop;
import model.persistence.PersistenceWriter;
import model.pets.Pet;
import ui.app.GuiApp;
import ui.app.PixelPetGame;
import ui.menus.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static model.configurables.FileLocations.backupSpriteDir;
import static ui.configurables.InterfaceAesthetics.*;
import static ui.configurables.JComponentBuilder.*;

// menu at the start of the game
public class GameMenu extends Menu {
    private static final File persistenceFile = new File(FileLocations.persistenceDir);

    private JPanel topBarButtonContainer;
    private JPanel promptContainer;
    private JPanel bottomBarButtonContainer;

    private PetEnvironment petEnvironment;

    // Pet Stats Menu Fields
    private final TitledBorder generalBorder = BorderFactory.createTitledBorder("General");
    private JPanel generalContainer;
    private JTextArea nameText;
    private JTextArea animalTypeText;
    private JTextArea breedText;
    private JTextArea ageText;
    private final TitledBorder careLevelsBorder = BorderFactory.createTitledBorder("Care Levels");
    private JPanel careLevelsContainer;
    private JTextArea happinessBar;
    private JTextArea thirstBar;
    private JTextArea hungerBar;
    private JTextArea healthBar;
    private final TitledBorder characteristicsBorder = BorderFactory.createTitledBorder("Characteristics");
    private JPanel characteristicsContainer;
    private JTextArea likesText;
    private JTextArea dislikesText;
    private JTextArea personalitiesText;
    private JTextArea cannotHavesText;

    // Shop Menu Fields
    private final String shopName = "Kira Kira Pets";
    private final TitledBorder shopItemsBorder = BorderFactory.createTitledBorder("Items");
    private JPanel shopItemsContainer;
    private final TitledBorder shopItemInfoBorder = BorderFactory.createTitledBorder("Information");
    private JPanel shopItemInfoContainer;
    private JTextArea shopPlayerMoneyText;
    private JTextArea shopItemNameText;
    private JTextArea shopItemTypeText;
    private JTextArea shopItemPriceText;
    private JTextArea shopItemQuantityText;
    private JPanel shopItemBuyButtonContainer;
    private String shopSelectedItemName = "";
    private String shopSelectedItemType = "";

    // Inventory Menu Fields
    private final TitledBorder inventoryItemsBorder = BorderFactory.createTitledBorder("Items");
    private JPanel inventoryItemsContainer;
    private final TitledBorder inventoryItemInfoBorder = BorderFactory.createTitledBorder("Information");
    private JPanel inventoryItemInfoContainer;
    private JTextArea inventoryPlayerMoneyText;
    private JTextArea inventoryItemNameText;
    private JTextArea inventoryItemTypeText;
    private JTextArea inventoryItemQuantityText;
    private JPanel inventoryItemUseButtonContainer;
    private String inventorySelectedItemName = "";
    private String inventorySelectedItemType = "";

    // Save Menu Fields
    private JTextArea savePromptText;
    private JPanel saveButtonContainer;
    private JTextArea saveSuccessfulText;
    private JPanel saveSuccessfulButtonContainer;

    // Main Menu Prompt Fields
    private JTextArea mainMenuPromptText;
    private JPanel mainMenuButtonContainer;

    private boolean isPetStatsMenuLoaded = false;

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
    private void togglePrompts(boolean isVisibile) {
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


    // CODE FOR THE SAVE MENU...
    // EFFECTS: creates a prompt asking if the player wants to save the game
    private void generateSavePrompt() throws IOException, FontFormatException {
        promptContainer.removeAll();
        createSavePromptText();

        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));
        promptContainer.add(Box.createVerticalStrut(height / 3));
        promptContainer.add(savePromptText);
        promptContainer.add(Box.createVerticalStrut(10));
        promptContainer.add(saveButtonContainer);
        togglePrompts(true);
    }

    // EFFECTS: creates the save prompt text components
    private void createSavePromptText() throws IOException, FontFormatException {
        savePromptText = createJTextArea("Are you sure you want to save the game?",
                28f, width - 100, 100);

        JButton cancelButton = createJButton("Cancel", "cancelSaveClicked", this,
                24f, 100, 50);
        cancelButton.setBackground(BUTTON_COLOR_2);

        JButton confirmButton = createJButton("Confirm", "confirmSaveClicked", this,
                24f, 100, 50);
        confirmButton.setBackground(BUTTON_COLOR_2);

        saveButtonContainer = createJPanel(TRANSPARENT, width, height);
        saveButtonContainer.add(cancelButton);
        saveButtonContainer.add(confirmButton);
        menu.getRootPane().setDefaultButton(confirmButton);
    }

    // EFFECTS: displays the save successful
    private void displaySaveSuccessful() throws IOException, FontFormatException {
        new PersistenceWriter(persistenceFile, ui.getGame());

        promptContainer.removeAll();
        createSaveSuccessfulText();

        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));
        promptContainer.add(Box.createVerticalStrut(height / 4));
        promptContainer.add(saveSuccessfulText);
        promptContainer.add(Box.createVerticalStrut(10));
        promptContainer.add(saveSuccessfulButtonContainer);
        togglePrompts(true);
    }

    // EFFECTS: creates the save successful prompt text components
    private void createSaveSuccessfulText() throws IOException, FontFormatException {
        saveSuccessfulText = createJTextArea("Your session has been saved successfully!",
                28f, width - 100, 100);

        JButton continueButton = createJButton("Continue", "continueSaveSuccessfulClicked", this,
                24f, 100, 50);
        continueButton.setBackground(BUTTON_COLOR_2);

        saveSuccessfulButtonContainer = createJPanel(TRANSPARENT, width, height);
        saveSuccessfulButtonContainer.add(continueButton);
        menu.getRootPane().setDefaultButton(continueButton);
    }


    // CODE FOR THE PET STATS MENU...
    // EFFECTS: generates pet stats menu
    private void generatePetStats() throws IOException, FontFormatException {
        promptContainer.removeAll();
        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));

        JButton backButton = createJButton("Back", "backClicked", this,
                24f, width, 40);
        backButton.setBackground(BUTTON_COLOR_2);

        JPanel backButtonContainer = createJPanel(TRANSPARENT, width, 48);
        backButtonContainer.add(backButton);

        generateGeneral();
        generateCareLevels();
        generateCharacteristics();

        promptContainer.add(Box.createVerticalStrut(50));
        promptContainer.add(generalContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(careLevelsContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(characteristicsContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(backButtonContainer);
        togglePrompts(true);
        isPetStatsMenuLoaded = true;
    }

    // EFFECTS: generates the general component for pet stats menu
    private void generateGeneral() throws IOException, FontFormatException {
        createGeneralPanel(width - 200, 40, 20f);
        generalContainer.setLayout(new BoxLayout(generalContainer, BoxLayout.Y_AXIS));
        generalContainer.add(nameText);
        generalContainer.add(animalTypeText);
        generalContainer.add(breedText);
        generalContainer.add(ageText);
    }

    // EFFECTS: creates the general texts
    private void createGeneralPanel(int width, int height, float fontSize) throws IOException, FontFormatException {
        nameText = createJTextArea("Name: " + ui.getGame().getPet().getName(),
                fontSize, width, height);

        animalTypeText = createJTextArea("Animal Type: " + ui.getGame().getPet().getAnimalType(),
                fontSize, width, height);

        breedText = createJTextArea("Breed: " + ui.getGame().getPet().getBreed(),
                fontSize, width, height);

        ageText = createJTextArea("Age: " + ui.getGame().getPet().getAge(),
                fontSize, width, height);

        generalContainer = createJPanel(GAME_BAR_UI_COLOR, this.width - 100, (height + 8) * 4);
        generalContainer.setBorder(generalBorder);
        generalBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: generates the care levels component for pet stats menu
    private void generateCareLevels() throws IOException, FontFormatException {
        createCareLevelsPanel(width - 200, 30, 18f);
        careLevelsContainer.setLayout(new BoxLayout(careLevelsContainer, BoxLayout.Y_AXIS));
        careLevelsContainer.add(happinessBar);
        careLevelsContainer.add(Box.createVerticalStrut(5));
        careLevelsContainer.add(hungerBar);
        careLevelsContainer.add(Box.createVerticalStrut(5));
        careLevelsContainer.add(thirstBar);
        careLevelsContainer.add(Box.createVerticalStrut(5));
        careLevelsContainer.add(healthBar);
    }

    // EFFECTS: creates the happiness, hunger, thirst, health bars
    private void createCareLevelsPanel(int width, int height, float fontSize) throws IOException, FontFormatException {
        happinessBar = createJTextArea("Happiness: "
                        + ui.getGame().getPet().getHappiness() + "/" + PixelPetGame.MAX_HAPPINESS,
                fontSize, width, height);
        happinessBar.setBackground(HAPPINESS_BAR_COLOR);

        hungerBar = createJTextArea("Hunger: "
                        + ui.getGame().getPet().getHunger() + "/" + PixelPetGame.MAX_HUNGER,
                fontSize, width, height);
        hungerBar.setBackground(HUNGER_BAR_COLOR);

        thirstBar = createJTextArea("Thirst: "
                        + ui.getGame().getPet().getThirst() + "/" + PixelPetGame.MAX_THIRST,
                fontSize, width, height);
        thirstBar.setBackground(THIRST_BAR_COLOR);

        healthBar = createJTextArea("Health: "
                        + ui.getGame().getPet().getHealth() + "/" + PixelPetGame.MAX_HEALTH,
                fontSize, width, height);
        healthBar.setBackground(HEALTH_BAR_COLOR);

        careLevelsContainer = createJPanel(GAME_BAR_UI_COLOR, this.width - 100, (height + 8) * 4 + 15);
        careLevelsContainer.setBorder(careLevelsBorder);
        careLevelsBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: updates the pet stats ui
    private void updatePetStats() {
        happinessBar.setText("Happiness: " + ui.getGame().getPet().getHappiness() + "/" + PixelPetGame.MAX_HAPPINESS);
        hungerBar.setText("Hunger: " + ui.getGame().getPet().getHunger() + "/" + PixelPetGame.MAX_HUNGER);
        thirstBar.setText("Thirst: " + ui.getGame().getPet().getThirst() + "/" + PixelPetGame.MAX_THIRST);
        healthBar.setText("Health: " + ui.getGame().getPet().getHealth() + "/" + PixelPetGame.MAX_HEALTH);
        ageText.setText("Age: " + ui.getGame().getPet().getAge());
    }

    // EFFECTS: generates the characteristics component for pet stats menu
    private void generateCharacteristics() throws IOException, FontFormatException {
        createCharacteristicsPanel(width - 200, 40, 16f);
        characteristicsContainer.setLayout(new BoxLayout(characteristicsContainer, BoxLayout.Y_AXIS));
        characteristicsContainer.add(likesText);
        characteristicsContainer.add(dislikesText);
        characteristicsContainer.add(personalitiesText);
        characteristicsContainer.add(cannotHavesText);
    }

    // EFFECTS: creates the characteristics texts
    private void createCharacteristicsPanel(int width, int height, float fontSize)
            throws IOException, FontFormatException {
        likesText = createJTextArea("Likes: "
                        + arrayToString(ui.getGame().getPet().getLikes()),
                fontSize, width, height);

        dislikesText = createJTextArea("Dislikes: "
                        + arrayToString(ui.getGame().getPet().getDislikes()),
                fontSize, width, height);

        personalitiesText = createJTextArea("Personalities: "
                        + arrayToString(ui.getGame().getPet().getPersonalities()),
                fontSize, width, height);

        cannotHavesText = createJTextArea("Cannot Have: "
                        + arrayToString(ui.getGame().getPet().getCannotHaves()),
                fontSize, width, height);

        characteristicsContainer = createJPanel(GAME_BAR_UI_COLOR, this.width - 100, (height + 8) * 4);
        characteristicsContainer.setBorder(characteristicsBorder);
        characteristicsBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: converts an ArrayList of String to a cleaner format
    private String arrayToString(ArrayList<String> arrayList) {
        return arrayList.toString().replaceAll("\\[|\\]","");
    }


    // CODE FOR THE SHOP MENU
    // EFFECTS: generates shop menu
    private void generateShop() throws IOException, FontFormatException {
        promptContainer.removeAll();
        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));
        shopSelectedItemName = "";
        shopSelectedItemType = "";

        JButton backButton = createJButton("Back", "backClicked", this,
                24f, width, 40);
        backButton.setBackground(BUTTON_COLOR_2);

        JPanel backButtonContainer = createJPanel(TRANSPARENT, width, 48);
        backButtonContainer.add(backButton);

        generateShopItemsPanel();
        generateShopItemInfoPanel();

        promptContainer.add(Box.createVerticalStrut(50));
        promptContainer.add(backButtonContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(shopItemsContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(shopItemInfoContainer);
        togglePrompts(true);
    }

    // EFFECTS: generates the items list component for shop menu
    private void generateShopItemsPanel() throws IOException, FontFormatException {
        shopItemsContainer = createJPanel(GAME_BAR_UI_COLOR, width - 200, 320);
        shopItemsContainer.setLayout(new BoxLayout(shopItemsContainer, BoxLayout.Y_AXIS));
        shopItemsContainer.setBorder(shopItemsBorder);
        shopItemsBorder.setTitleColor(BUTTON_TEXT_COLOR);
        createShopItemsPanel(20f);
    }

    // EFFECTS: creates the shop item buttons
    private void createShopItemsPanel(float fontSize) throws IOException, FontFormatException {
        Shop shop = ui.getGame().getShopByName(shopName);
        ArrayList<Item> items = shop.getShopItems();
        ArrayList<Integer> quantities = shop.getQuantityInStock();

        shopItemsContainer.removeAll();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            JPanel itemContainer = createJPanel(GAME_BAR_UI_COLOR, width - 200, 56);
            JButton itemButton = createJButton(item.getType() + ": \'" + item.getName()
                            + "\' x" + quantities.get(i) + " ($" + item.getPrice() + ")",
                    item.getName() + "~" + item.getType()
                            + "~$" + item.getPrice() + "~" + quantities.get(i),
                    shopButtonClicked, fontSize, width - 200, 48);
            itemButton.setPreferredSize(new Dimension(width - 300, 48));


            item.getSpritesDir();
            itemContainer.add(itemButton);
            itemContainer.add(new JLabel(createItemIcon(item, 48, 48)));
            shopItemsContainer.add(itemContainer);
        }

        JPanel pseudoContainer = createJPanel(TRANSPARENT, width - 200, 56);
        pseudoContainer.setPreferredSize(new Dimension(width - 200, 56));
        shopItemsContainer.add(pseudoContainer);
    }

    // ActionListener for shop item buttons
    private ActionListener shopButtonClicked = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> parsedInfo =
                    new ArrayList<>(Arrays.asList(e.getActionCommand().split("~")));
            String itemName = parsedInfo.get(0);
            String itemType = parsedInfo.get(1);

            shopSelectedItemName = itemName;
            shopSelectedItemType = itemType;

            shopItemNameText.setText("Item Name: " + itemName);
            shopItemTypeText.setText("Item Type: " + itemType);
            shopItemPriceText.setText("Price: " + parsedInfo.get(2));
            shopItemQuantityText.setText("Quantity: " + parsedInfo.get(3));
        }
    };

    // EFFECTS: generates the item info component for shop menu
    private void generateShopItemInfoPanel() throws IOException, FontFormatException {
        createShopItemInfoPanel(20f);
        shopItemInfoContainer.add(shopItemNameText);
        shopItemInfoContainer.add(Box.createVerticalStrut(5));
        shopItemInfoContainer.add(shopItemTypeText);
        shopItemInfoContainer.add(Box.createVerticalStrut(5));
//        shopItemInfoContainer.add(shopItemPriceText);
//        shopItemInfoContainer.add(Box.createVerticalStrut(5));
//        shopItemInfoContainer.add(shopItemQuantityText);
//        shopItemInfoContainer.add(Box.createVerticalStrut(5));
        shopItemInfoContainer.add(shopItemBuyButtonContainer);
        shopItemInfoContainer.add(Box.createVerticalStrut(5));
        shopItemInfoContainer.add(shopPlayerMoneyText);
    }

    // EFFECTS: creates the shop item buttons
    private void createShopItemInfoPanel(float fontSize)
            throws IOException, FontFormatException {
        shopItemNameText = createJTextArea("Item Name: ",
                fontSize, width / 2, 50);
        shopItemTypeText = createJTextArea("Item Type: ",
                fontSize, width / 2, 50);
        shopItemPriceText = createJTextArea("Price: ",
                fontSize, width / 2, 50);
        shopItemQuantityText = createJTextArea("Quantity: ",
                fontSize, width / 2, 50);

        shopItemBuyButtonContainer = createJPanel(TRANSPARENT, width, 58);
        JButton buyButton = createJButton("Buy", "shopItemBuyClicked", this,
                fontSize, width / 2, 50);
        buyButton.setPreferredSize(new Dimension(width / 2, 50));
        buyButton.setBackground(BUTTON_COLOR_2);
        shopItemBuyButtonContainer.add(buyButton);

        shopPlayerMoneyText = createJTextArea("Money: " + ui.getGame().getPlayer().getMoney(),
                fontSize, width / 2, 50);

        shopItemInfoContainer = createJPanel(GAME_BAR_UI_COLOR,width - 200, (50 + 8 + 5) * 4);
        shopItemInfoContainer.setLayout(new BoxLayout(shopItemInfoContainer, BoxLayout.Y_AXIS));
        shopItemInfoContainer.setBorder(shopItemInfoBorder);
        shopItemInfoBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: checks to see if buying the item is valid,
    //          then handles the player transaction if valid
    private void buyItem(String itemName, String itemType) {
        Player player = ui.getGame().getPlayer();
        Shop shop = ui.getGame().getShopByName(shopName);
        Item itemToCheck = validItem(shop.getShopItems(), itemName, itemType);

        if (itemToCheck != null) {
            if (player.buyItemFrom(itemToCheck, 1, shop)) {
                shopPlayerMoneyText.setText("Money: $" + player.getMoney());
            }
        }
    }

    // EFFECTS: returns the item if there exists an item of the name itemName and type itemType
    private Item validItem(ArrayList<Item> itemsList, String itemName, String itemType) {
        for (Item item : itemsList) {
            String comparingName = item.getName().toLowerCase();
            String comparingType = item.getType().toLowerCase();
            boolean foundItemName = (comparingName.equals(itemName.toLowerCase()));
            boolean foundItemType = (comparingType.equals(itemType.toLowerCase()));

            if (foundItemName && foundItemType) {
                return item;
            }
        }
        return null;
    }

    // EFFECTS: creates an icon for the item
    private ImageIcon createItemIcon(Item item, int width, int height) throws IOException {
        BufferedImage iconBufferedImg = ImageIO.read(new File(backupSpriteDir));

        try {
            iconBufferedImg = ImageIO.read(new File(item.getSpritesDir()
                    + (item.getName() + "_" + item.getType()).toLowerCase() + ".png"));
        } catch (Exception e) {
            // sprite was unable to load
        }

        Image iconImg = new ImageIcon(iconBufferedImg).getImage()
                .getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(iconImg);
    }


    // CODE FOR THE INVENTORY MENU
    // EFFECTS: generates inventory menu
    private void generateInventory() throws IOException, FontFormatException {
        promptContainer.removeAll();
        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));
        inventorySelectedItemName = "";
        inventorySelectedItemType = "";

        JButton backButton = createJButton("Back", "backClicked", this,
                24f, width, 40);
        backButton.setBackground(BUTTON_COLOR_2);

        JPanel backButtonContainer = createJPanel(TRANSPARENT, width, 48);
        backButtonContainer.add(backButton);

        generateInventoryItemsPanel();
        generateInventoryItemInfoPanel();

        promptContainer.add(Box.createVerticalStrut(50));
        promptContainer.add(backButtonContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(inventoryItemsContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(inventoryItemInfoContainer);
        togglePrompts(true);
    }

    // EFFECTS: generates the items list component for inventory menu
    private void generateInventoryItemsPanel() throws IOException, FontFormatException {
        inventoryItemsContainer = createJPanel(GAME_BAR_UI_COLOR, width - 200, 320);
        inventoryItemsContainer.setLayout(new BoxLayout(inventoryItemsContainer, BoxLayout.Y_AXIS));
        inventoryItemsContainer.setBorder(inventoryItemsBorder);
        inventoryItemsBorder.setTitleColor(BUTTON_TEXT_COLOR);
        createInventoryItemsPanel(20f);
    }

    // EFFECTS: creates the inventory item buttons
    private void createInventoryItemsPanel(float fontSize) throws IOException, FontFormatException {
        Player player = ui.getGame().getPlayer();
        ArrayList<Item> items = player.getInventory();
        ArrayList<Integer> quantities = player.getInventoryQuantity();

        inventoryItemsContainer.removeAll();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            JPanel itemContainer = createJPanel(GAME_BAR_UI_COLOR, width - 200, 56);
            JButton itemButton = createJButton(item.getType() + ": \'" + item.getName()
                            + "\' x" + quantities.get(i),
                    item.getName() + "~" + item.getType()
                            + "~" + quantities.get(i),
                    inventoryButtonClicked, fontSize, width - 200, 48);
            itemButton.setPreferredSize(new Dimension(width - 300, 48));


            item.getSpritesDir();
            itemContainer.add(itemButton);
            itemContainer.add(new JLabel(createItemIcon(item,48, 48)));
            inventoryItemsContainer.add(itemContainer);
        }

        JPanel pseudoContainer = createJPanel(TRANSPARENT, width - 200, 56);
        pseudoContainer.setPreferredSize(new Dimension(width - 200, 56));
        inventoryItemsContainer.add(pseudoContainer);
    }

    // ActionListener for inventory item buttons
    private ActionListener inventoryButtonClicked = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> parsedInfo =
                    new ArrayList<>(Arrays.asList(e.getActionCommand().split("~")));
            String itemName = parsedInfo.get(0);
            String itemType = parsedInfo.get(1);

            inventorySelectedItemName = itemName;
            inventorySelectedItemType = itemType;

            inventoryItemNameText.setText("Item Name: " + itemName);
            inventoryItemTypeText.setText("Item Type: " + itemType);
            inventoryItemQuantityText.setText("Quantity: " + parsedInfo.get(2));
        }
    };

    // EFFECTS: generates the item info component for inventory menu
    private void generateInventoryItemInfoPanel() throws IOException, FontFormatException {
        createInventoryItemInfoPanel(20f);
        inventoryItemInfoContainer.add(inventoryItemNameText);
        inventoryItemInfoContainer.add(Box.createVerticalStrut(5));
        inventoryItemInfoContainer.add(inventoryItemTypeText);
        inventoryItemInfoContainer.add(Box.createVerticalStrut(5));
//        inventoryItemInfoContainer.add(inventoryItemQuantityText);
//        inventoryItemInfoContainer.add(Box.createVerticalStrut(5));
        inventoryItemInfoContainer.add(inventoryItemUseButtonContainer);
        inventoryItemInfoContainer.add(Box.createVerticalStrut(5));
        inventoryItemInfoContainer.add(inventoryPlayerMoneyText);
    }

    // EFFECTS: creates the inventory item buttons
    private void createInventoryItemInfoPanel(float fontSize) throws IOException, FontFormatException {
        inventoryItemNameText = createJTextArea("Item Name: ",
                fontSize, width / 2, 50);
        inventoryItemTypeText = createJTextArea("Item Type: ",
                fontSize, width / 2, 50);
        inventoryItemQuantityText = createJTextArea("Quantity: ",
                fontSize, width / 2, 50);

        inventoryItemUseButtonContainer = createJPanel(TRANSPARENT, width, 58);
        JButton useButton = createJButton("Use", "inventoryItemUseClicked", this,
                fontSize, width / 2, 50);
        useButton.setPreferredSize(new Dimension(width / 2, 50));
        useButton.setBackground(BUTTON_COLOR_2);
        inventoryItemUseButtonContainer.add(useButton);

        inventoryPlayerMoneyText = createJTextArea("Money: " + ui.getGame().getPlayer().getMoney(),
                fontSize, width / 2, 50);

        inventoryItemInfoContainer = createJPanel(GAME_BAR_UI_COLOR,width - 200, (50 + 8 + 5) * 4);
        inventoryItemInfoContainer.setLayout(new BoxLayout(inventoryItemInfoContainer, BoxLayout.Y_AXIS));
        inventoryItemInfoContainer.setBorder(inventoryItemInfoBorder);
        inventoryItemInfoBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: checks to see if giving the item is valid,
    //          then handles the player-to-pet transaction if valid
    private void giveItemToPet(String itemName, String itemType) {
        Player player = ui.getGame().getPlayer();
        Pet pet = ui.getGame().getPet();
        Item itemToCheck = validItem(player.getInventory(), itemName, itemType);

        if (itemToCheck != null) {
            if (player.giveItemTo(itemToCheck, pet, 1)) {
                // successfully gave item
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: renders the ui graphics for the gameMenu
    public void renderGraphics(PixelPetGame game) {
//        petEnvironment.renderGraphics(game);

        if (isPetStatsMenuLoaded) {
            updatePetStats();
        }

        if (shopPlayerMoneyText != null) {
            shopPlayerMoneyText.setText("Money: $" + ui.getPlayer().getMoney());
        }

        if (inventoryPlayerMoneyText != null) {
            inventoryPlayerMoneyText.setText("Money: $" + ui.getPlayer().getMoney());
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
            generateSavePrompt();
        } else if (command.equals("confirmSaveClicked")) {
            displaySaveSuccessful();
        }
    }

    // EFFECTS: helper for performAction; shop menu specific
    private void performActionShopMenu(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("shopClicked")) {
            generateShop();
        } else if (command.equals("shopItemBuyClicked")) {
            buyItem(shopSelectedItemName, shopSelectedItemType);
            createShopItemsPanel(20f);
        }
    }

    // EFFECTS: helper for performAction; inventory menu specific
    private void performActionInventoryMenu(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("inventoryClicked")) {
            generateInventory();
        } else if (command.equals("inventoryItemUseClicked")) {
            giveItemToPet(inventorySelectedItemName, inventorySelectedItemType);
            createInventoryItemsPanel(20f);
        }
    }

    // EFFECTS: helper for performAction; pet stats menu specific
    private void performActionPetStatsMenu(String command, JComponent source)
            throws IOException, FontFormatException {
        if (command.equals("petStatsClicked")) {
            generatePetStats();
        }
    }

    // GETTER:
//    public PetEnvironment getPetEnvironment() {
//        return petEnvironment;
//    }
}
