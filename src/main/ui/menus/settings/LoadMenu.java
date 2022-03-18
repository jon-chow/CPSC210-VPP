package ui.menus.settings;

import model.configurables.FileLocations;
import model.exceptions.CannotFindSessionIdException;
import model.persistence.PersistenceReader;
import ui.app.GuiApp;
import ui.menus.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.menus.JComponentBuilder.*;

// menu for creating a new player
public class LoadMenu extends Menu {
    private static final File persistenceFile = new File(FileLocations.persistenceDir);

    private JTextArea promptText;
    private JTextField sessionIdTextBox;
    private JPanel continueButtonContainer;

    // EFFECTS: constructs the main menu
    public LoadMenu(GuiApp ui, JLayeredPane menu) throws IOException, FontFormatException {
        super(ui, menu);
        initMenu();
    }

    // TODO:
    // EFFECTS: initiates the new player process
    public void initMenu() throws IOException, FontFormatException {
        generateLoadSessionIdPrompt();
    }

    // EFFECTS: creates a prompt asking for the session id to load
    private void generateLoadSessionIdPrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        JPanel loadSessionBox = createJPanel(TRANSPARENT, width, height);
        loadSessionBox.setLayout(new BoxLayout(loadSessionBox, BoxLayout.Y_AXIS));

        createSessionIdPromptText();

        menu.add(Box.createVerticalStrut(height / 5));
        loadSessionBox.add(promptText);
        loadSessionBox.add(Box.createVerticalStrut(10));
        loadSessionBox.add(sessionIdTextBox);
        loadSessionBox.add(Box.createVerticalStrut(10));
        loadSessionBox.add(continueButtonContainer);

        menu.add(loadSessionBox);
    }

    // EFFECTS: creates the prompt text components for the session id
    private void createSessionIdPromptText() throws IOException, FontFormatException {
        promptText = createJTextArea("Enter the session id for the game you'd like to load:",
                28f, width - 100, height / 8);

        sessionIdTextBox = createJTextField("0", 24f, width / 2, height / 12,
                JTextField.CENTER, true);
        sessionIdTextBox.setForeground(FIELD_TEXT_COLOR);

        JButton continueButton = createJButton("Continue", "continueSessionLoadClicked", this,
                24f, width, height / 10);
        continueButton.setBackground(BUTTON_COLOR_2);

        continueButtonContainer = createJPanel(TRANSPARENT, width, height);
        continueButtonContainer.add(continueButton);
        menu.getRootPane().setDefaultButton(continueButton);
    }

    // MODIFIES: PixelPetGame
    // EFFECTS: returns true if session id is valid
    private boolean isSessionIdValid() {
        try {
            int sessionId = Integer.parseInt(sessionIdTextBox.getText());
            loadGameData(sessionId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    // EFFECTS: displays the menu for loading data
//    public static void displayLoadMenu(PixelPetGame game) {
//        System.out.println("Please enter your personal ID to continue,\n"
//                + "or enter any other key to exit the load menu.");
//        int sessionId = 0;
//        String command = scanner.nextLine();
//
//        try {
//            sessionId = Integer.parseInt(command);
//            System.out.println("Are you sure you'd like to load this session?");
//            if (confirmationMenu()) {
//                loadGameData(game, sessionId);
//            } else {
//                System.out.println("Your selected session was not loaded.");
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("You have left the load menu.");
//        }
//    }

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

    // TODO:
    // EFFECTS: helper for actionPerformed; performs the desired action
    protected void performAction(String command, JComponent source)
            throws IOException, InterruptedException, CannotFindSessionIdException {
        if (command.equals("continueSessionLoadClicked")) {
            if (!isSessionIdValid()) {
                System.out.println("Invalid");
            } else {
                System.out.println("Valid");
                ui.start(false, false);
            }

        }
    }
}
