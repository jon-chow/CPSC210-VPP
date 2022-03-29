package ui.menus.ingame;

import ui.app.GuiApp;
import ui.app.PixelPetGame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.configurables.JComponentBuilder.*;

// represents the pet stats menu
public class PetStatsMenu {
    private static final TitledBorder generalBorder = BorderFactory.createTitledBorder("General");
    private static JPanel generalContainer;
    private static JTextArea nameText;
    private static JTextArea animalTypeText;
    private static JTextArea breedText;
    private static JTextArea ageText;

    private static final TitledBorder careLevelsBorder = BorderFactory.createTitledBorder("Care Levels");
    private static JPanel careLevelsContainer;
    private static JTextArea happinessBar;
    private static JTextArea thirstBar;
    private static JTextArea hungerBar;
    private static JTextArea healthBar;

    private static final TitledBorder characteristicsBorder = BorderFactory.createTitledBorder("Characteristics");
    private static JPanel characteristicsContainer;
    private static JTextArea likesText;
    private static JTextArea dislikesText;
    private static JTextArea personalitiesText;
    private static JTextArea cannotHavesText;

    // EFFECTS: generates pet stats menu
    public static void generatePetStats(GuiApp ui, JLayeredPane menu, GameMenu gameMenu,
                                        JPanel promptContainer, int width, int height)
            throws IOException, FontFormatException {
        promptContainer.removeAll();
        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));

        JButton backButton = createJButton("Back", "backClicked", gameMenu,
                24f, width, 40);
        backButton.setBackground(BUTTON_COLOR_2);

        JPanel backButtonContainer = createJPanel(TRANSPARENT, width, 48);
        backButtonContainer.add(backButton);

        generateGeneral(ui, width);
        generateCareLevels(ui, width);
        generateCharacteristics(ui, width);

        promptContainer.add(Box.createVerticalStrut(50));
        promptContainer.add(generalContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(careLevelsContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(characteristicsContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(backButtonContainer);
        gameMenu.togglePrompts(true);
    }

    // EFFECTS: generates the general component for pet stats menu
    private static void generateGeneral(GuiApp ui, int width) throws IOException, FontFormatException {
        createGeneralPanel(ui, width - 200, 40, 20f);
        generalContainer.setLayout(new BoxLayout(generalContainer, BoxLayout.Y_AXIS));
        generalContainer.add(nameText);
        generalContainer.add(animalTypeText);
        generalContainer.add(breedText);
        generalContainer.add(ageText);
    }

    // EFFECTS: creates the general texts
    private static void createGeneralPanel(GuiApp ui, int width, int height, float fontSize)
            throws IOException, FontFormatException {
        nameText = createJTextArea("Name: " + ui.getGame().getPet().getName(),
                fontSize, width, height);

        animalTypeText = createJTextArea("Animal Type: " + ui.getGame().getPet().getAnimalType(),
                fontSize, width, height);

        breedText = createJTextArea("Breed: " + ui.getGame().getPet().getBreed(),
                fontSize, width, height);

        ageText = createJTextArea("Age: " + ui.getGame().getPet().getAge(),
                fontSize, width, height);

        generalContainer = createJPanel(GAME_BAR_UI_COLOR, width + 100, (height + 8) * 4);
        generalContainer.setBorder(generalBorder);
        generalBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: generates the care levels component for pet stats menu
    private static void generateCareLevels(GuiApp ui, int width) throws IOException, FontFormatException {
        createCareLevelsPanel(ui, width - 200, 30, 18f);
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
    private static void createCareLevelsPanel(GuiApp ui, int width, int height, float fontSize)
            throws IOException, FontFormatException {
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

        careLevelsContainer = createJPanel(GAME_BAR_UI_COLOR, width + 100, (height + 8) * 4 + 15);
        careLevelsContainer.setBorder(careLevelsBorder);
        careLevelsBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: updates the pet stats ui
    public static void updatePetStats(GuiApp ui) {
        happinessBar.setText("Happiness: " + ui.getGame().getPet().getHappiness() + "/" + PixelPetGame.MAX_HAPPINESS);
        hungerBar.setText("Hunger: " + ui.getGame().getPet().getHunger() + "/" + PixelPetGame.MAX_HUNGER);
        thirstBar.setText("Thirst: " + ui.getGame().getPet().getThirst() + "/" + PixelPetGame.MAX_THIRST);
        healthBar.setText("Health: " + ui.getGame().getPet().getHealth() + "/" + PixelPetGame.MAX_HEALTH);
        ageText.setText("Age: " + ui.getGame().getPet().getAge());
    }

    // EFFECTS: generates the characteristics component for pet stats menu
    private static void generateCharacteristics(GuiApp ui, int width) throws IOException, FontFormatException {
        createCharacteristicsPanel(ui, width - 200, 40, 16f);
        characteristicsContainer.setLayout(new BoxLayout(characteristicsContainer, BoxLayout.Y_AXIS));
        characteristicsContainer.add(likesText);
        characteristicsContainer.add(dislikesText);
        characteristicsContainer.add(personalitiesText);
        characteristicsContainer.add(cannotHavesText);
    }

    // EFFECTS: creates the characteristics texts
    private static void createCharacteristicsPanel(GuiApp ui, int width, int height, float fontSize)
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

        characteristicsContainer = createJPanel(GAME_BAR_UI_COLOR, width + 100, (height + 8) * 4);
        characteristicsContainer.setBorder(characteristicsBorder);
        characteristicsBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: converts an ArrayList of String to a cleaner format
    private static String arrayToString(ArrayList<String> arrayList) {
        return arrayList.toString().replaceAll("\\[|\\]","");
    }

}
