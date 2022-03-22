package ui.menus.ingame;

import model.configurables.FileLocations;
import model.persistence.PersistenceWriter;
import ui.app.GuiApp;
import ui.app.PixelPetGame;
import ui.menus.Menu;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.configurables.JComponentBuilder.*;

// menu at the start of the game
public class GameMenu extends Menu {
    private static final File persistenceFile = new File(FileLocations.persistenceDir);

    private JPanel topBarButtonContainer;
    private JPanel promptContainer;
    private JPanel bottomBarButtonContainer;

    private PetEnvironment petEnvironment;

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

    private JTextArea mainMenuPromptText;
    private JPanel mainMenuButtonContainer;

    private JTextArea savePromptText;
    private JPanel saveButtonContainer;
    private JTextArea saveSuccessfulText;
    private JPanel saveSuccessfulButtonContainer;

    private boolean isPetStatsMenuOpen = false;

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

        JButton backButton = createJButton("Back", "backPetStatsClicked", this,
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


    // MODIFIES: this, petEnvironment
    // EFFECTS: renders the ui graphics for the gameMenu
    public void renderGraphics(PixelPetGame game) {
        petEnvironment.renderGraphics(game);

        if (isPetStatsMenuOpen) {
            try {
                updatePetStats();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // TODO:
    // EFFECTS: helper for actionPerformed; performs the desired action
    protected void performAction(String command, JComponent source) throws IOException, FontFormatException {
        if (command.equals("mainMenuClicked")) {
            generateMainMenuPrompt();
        } else if (command.equals("confirmMainMenuClicked")) {
            ui.returnToMainMenu();
        } else if (command.equals("saveClicked")) {
            generateSavePrompt();
        } else if (command.equals("confirmSaveClicked")) {
            new PersistenceWriter(persistenceFile, ui.getGame());
            displaySaveSuccessful();
        } else if (command.equals("shopClicked")) {
            // TODO
        } else if (command.equals("inventoryClicked")) {
            // TODO
        } else if (command.equals("petStatsClicked")) {
            isPetStatsMenuOpen = true;
            generatePetStats();
        } else if (command.equals("cancelMainMenuClicked") || command.equals("cancelSaveClicked")
                || command.equals("continueSaveSuccessfulClicked") || command.equals("backPetStatsClicked")) {
            togglePrompts(false);
        }
    }

    // GETTER:
    public PetEnvironment getPetEnvironment() {
        return petEnvironment;
    }
}
