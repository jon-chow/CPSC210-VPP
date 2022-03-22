package ui.menus.settings;

import model.configurables.FileLocations;
import model.exceptions.CannotFindSessionIdException;
import model.persistence.PersistenceReader;
import ui.app.GuiApp;
import ui.menus.Menu;
import ui.menus.mainmenu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.configurables.JComponentBuilder.*;

// menu for creating a new player
public class LoadMenu extends Menu {
    private static final File persistenceFile = new File(FileLocations.persistenceDir);

    private JTextArea promptText;
    private JTextField sessionIdTextBox;
    private JPanel continueButtonContainer;

    private JTextArea confirmText;
    private JPanel confirmButtonContainer;

    private int sessionId;

    // EFFECTS: constructs the main menu
    public LoadMenu(GuiApp ui, JLayeredPane menu) throws IOException, FontFormatException {
        super(ui, menu);
        initMenu();
    }

    // EFFECTS: initiates the new player process
    public void initMenu() throws IOException, FontFormatException {
        generateLoadSessionIdPrompt();
    }

    // EFFECTS: creates a prompt asking for the session id to load
    private void generateLoadSessionIdPrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        createSessionIdPromptText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(promptText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(sessionIdTextBox);
        menu.add(Box.createVerticalStrut(10));
        menu.add(continueButtonContainer);
    }

    // EFFECTS: creates the prompt text components for the session id
    private void createSessionIdPromptText() throws IOException, FontFormatException {
        promptText = createJTextArea("Enter the session id for the game you'd like to load:",
                28f, width - 100, height / 8);

        sessionIdTextBox = createJTextField("0", 24f, width / 2, height / 12,
                JTextField.CENTER, true);
        sessionIdTextBox.setForeground(FIELD_TEXT_COLOR);

        JButton returnButton = createJButton("Main Menu", "returnSessionLoadClicked", this,
                24f, width, height / 10);
        returnButton.setBackground(BUTTON_COLOR_2);

        JButton continueButton = createJButton("Continue", "continueSessionLoadClicked", this,
                24f, width, height / 10);
        continueButton.setBackground(BUTTON_COLOR_2);

        continueButtonContainer = createJPanel(TRANSPARENT, width, height);
        continueButtonContainer.add(returnButton);
        continueButtonContainer.add(continueButton);
        menu.getRootPane().setDefaultButton(continueButton);
    }

    // MODIFIES: PixelPetGame
    // EFFECTS: returns true if session id is valid
    private boolean isSessionIdValid() {
        try {
            sessionId = Integer.parseInt(sessionIdTextBox.getText());
            loadGameData(sessionId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // EFFECTS: creates a prompt asking whether to load the session
    private void loadSessionConfirm() throws IOException, FontFormatException {
        ui.clearMenu();
        createConfirmText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(confirmText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(confirmButtonContainer);
    }

    // EFFECTS: creates the confirmation text components
    private void createConfirmText() throws IOException, FontFormatException {
        confirmText = createJTextArea("You are loading session ID " + sessionId + "."
                        + "\n\nPlayer name: " + ui.getGame().getPlayer().getPlayerName()
                        + "\nPet name: " + ui.getGame().getPet().getName()
                        + "\n\nAre you sure you want to load this session?",
                28f, width - 100, height / 2);

        JButton yesButton = createJButton("Yes", "confirmYesClicked", this,
                24f, width, height / 10);
        yesButton.setBackground(BUTTON_COLOR_2);

        JButton noButton = createJButton("No", "confirmNoClicked", this,
                24f, width, height / 10);
        noButton.setBackground(BUTTON_COLOR_2);

        confirmButtonContainer = createJPanel(TRANSPARENT, width, height);
        confirmButtonContainer.add(yesButton);
        confirmButtonContainer.add(noButton);
        menu.getRootPane().setDefaultButton(yesButton);
    }

    // EFFECTS: creates a prompt asking whether to load the session
    private void invalidSessionIdPrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        createInvalidSessionIdText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(confirmText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(confirmButtonContainer);
    }

    // EFFECTS: creates the confirmation text components
    private void createInvalidSessionIdText() throws IOException, FontFormatException {
        confirmText = createJTextArea("The session ID you have entered is invalid or does not exist!",
                28f, width - 100, height / 6);

        JButton backButton = createJButton("Back", "backInvalidSessionIdClicked", this,
                24f, width, height / 10);
        backButton.setBackground(BUTTON_COLOR_2);

        confirmButtonContainer = createJPanel(TRANSPARENT, width, height);
        confirmButtonContainer.add(backButton);
        menu.getRootPane().setDefaultButton(backButton);
    }

    // EFFECTS: loads game data from a previous session
    private void loadGameData(int sessionId)
            throws CannotFindSessionIdException, IOException {
        new PersistenceReader(persistenceFile, ui.getGame(), sessionId);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        JComponent source = (JComponent) e.getSource();

        try {
            performAction(command, source);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // EFFECTS: helper for actionPerformed; performs the desired action
    protected void performAction(String command, JComponent source)
            throws IOException, InterruptedException, CannotFindSessionIdException, FontFormatException {
        if (command.equals("returnSessionLoadClicked")) {
            new MainMenu(ui, menu);
        } else if (command.equals("continueSessionLoadClicked")) {
            if (!isSessionIdValid()) {
                invalidSessionIdPrompt();
            } else {
                loadSessionConfirm();
            }
        } else if (command.equals("confirmYesClicked")) {
            ui.start(false, false);
        } else if (command.equals("confirmNoClicked") || command.equals("backInvalidSessionIdClicked")) {
            generateLoadSessionIdPrompt();
        }
    }
}
