package ui.menus.ingame;

import model.persistence.PersistenceWriter;
import ui.app.GuiApp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.configurables.JComponentBuilder.*;

// represents the save menu
public class SaveMenu {
    private static JTextArea savePromptText;
    private static JPanel saveButtonContainer;
    private static JTextArea saveSuccessfulText;
    private static JPanel saveSuccessfulButtonContainer;

    // EFFECTS: creates a prompt asking if the player wants to save the game
    public static void generateSavePrompt(JLayeredPane menu, GameMenu gameMenu,
                                           JPanel promptContainer, int width, int height)
            throws IOException, FontFormatException {
        promptContainer.removeAll();
        createSavePromptText(menu, gameMenu, width, height);

        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));
        promptContainer.add(Box.createVerticalStrut(height / 3));
        promptContainer.add(savePromptText);
        promptContainer.add(Box.createVerticalStrut(10));
        promptContainer.add(saveButtonContainer);
        gameMenu.togglePrompts(true);
    }

    // EFFECTS: creates the save prompt text components
    private static void createSavePromptText(JLayeredPane menu, GameMenu gameMenu, int width, int height)
            throws IOException, FontFormatException {
        savePromptText = createJTextArea("Are you sure you want to save the game?",
                28f, width - 100, 100);

        JButton cancelButton = createJButton("Cancel", "cancelSaveClicked", gameMenu,
                24f, 100, 50);
        cancelButton.setBackground(BUTTON_COLOR_2);

        JButton confirmButton = createJButton("Confirm", "confirmSaveClicked", gameMenu,
                24f, 100, 50);
        confirmButton.setBackground(BUTTON_COLOR_2);

        saveButtonContainer = createJPanel(TRANSPARENT, width, height);
        saveButtonContainer.add(cancelButton);
        saveButtonContainer.add(confirmButton);
        menu.getRootPane().setDefaultButton(confirmButton);
    }

    // EFFECTS: displays the save successful
    public static void displaySaveSuccessful(GuiApp ui, JLayeredPane menu, GameMenu gameMenu,
                                              JPanel promptContainer, int width, int height)
            throws IOException, FontFormatException {
        new PersistenceWriter(gameMenu.persistenceFile, ui.getGame());

        promptContainer.removeAll();
        createSaveSuccessfulText(menu, gameMenu, width, height);

        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));
        promptContainer.add(Box.createVerticalStrut(height / 4));
        promptContainer.add(saveSuccessfulText);
        promptContainer.add(Box.createVerticalStrut(10));
        promptContainer.add(saveSuccessfulButtonContainer);
        gameMenu.togglePrompts(true);
    }

    // EFFECTS: creates the save successful prompt text components
    private static void createSaveSuccessfulText(JLayeredPane menu, GameMenu gameMenu, int width, int height)
            throws IOException, FontFormatException {
        saveSuccessfulText = createJTextArea("Your session has been saved successfully!",
                28f, width - 100, 100);

        JButton continueButton = createJButton("Continue", "continueSaveSuccessfulClicked", gameMenu,
                24f, 100, 50);
        continueButton.setBackground(BUTTON_COLOR_2);

        saveSuccessfulButtonContainer = createJPanel(TRANSPARENT, width, height);
        saveSuccessfulButtonContainer.add(continueButton);
        menu.getRootPane().setDefaultButton(continueButton);
    }
}
