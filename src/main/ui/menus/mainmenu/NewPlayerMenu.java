package ui.menus.mainmenu;

import model.Player;
import ui.app.GuiApp;
import ui.menus.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static ui.configurables.JComponentBuilder.*;
import static ui.configurables.InterfaceAesthetics.*;

// menu for creating a new player
public class NewPlayerMenu extends Menu {
    private JTextArea promptText;
    private JTextField nameTextBox;
    private JPanel continueButtonContainer;

    private JTextArea confirmText;
    private JPanel confirmButtonContainer;
    private JTextArea helloText;

    private final String defaultName = "Player";
    private int startingMoney;
    private String name;
    private Player player;

    // EFFECTS: constructs the main menu
    public NewPlayerMenu(GuiApp ui, JLayeredPane menu, int startingMoney) throws IOException, FontFormatException {
        super(ui, menu);
        name = defaultName;
        this.startingMoney = startingMoney;
        initMenu();
    }

    // EFFECTS: initiates the new player process
    protected void initMenu() throws IOException, FontFormatException {
        player = new Player();
        player.setMoney(startingMoney);
        generateNamePrompt();
    }

    // EFFECTS: creates a prompt asking for the player's name
    private void generateNamePrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        createPromptText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(promptText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(nameTextBox);
        menu.add(Box.createVerticalStrut(10));
        menu.add(continueButtonContainer);
    }

    // EFFECTS: creates the prompt text components
    private void createPromptText() throws IOException, FontFormatException {
        promptText = createJTextArea("Hello player, and welcome to Pixel Pet!\n"
                        + "Please enter your player name in the box below...",
                28f, width - 100, 120);

        nameTextBox = createJTextField(defaultName, 24f, width / 2, height / 12,
                        JTextField.CENTER, true);
        nameTextBox.setForeground(FIELD_TEXT_COLOR);

        JButton returnButton = createJButton("Main Menu", "returnSessionLoadClicked", this,
                24f, 100, 50);
        returnButton.setBackground(BUTTON_COLOR_2);

        JButton continueButton = createJButton("Continue", "continueNameClicked", this,
                        24f, 100, 50);
        continueButton.setBackground(BUTTON_COLOR_2);

        continueButtonContainer = createJPanel(TRANSPARENT, width, height);
        continueButtonContainer.add(returnButton);
        continueButtonContainer.add(continueButton);
        menu.getRootPane().setDefaultButton(continueButton);
    }

    // EFFECTS: creates a prompt asking for the player to confirm their name
    private void newPlayerConfirmName() throws IOException, FontFormatException {
        ui.clearMenu();
        checkValidName();
        createConfirmText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(confirmText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(confirmButtonContainer);
    }

    // MODIFIES: this
    // EFFECTS: changes the name to the defaultName if name is empty
    private void checkValidName() {
        if (name.equals("")) {
            name = defaultName;
        }
    }

    // EFFECTS: creates the confirmation text components
    private void createConfirmText() throws IOException, FontFormatException {
        confirmText = createJTextArea("You have entered your name as \"" + name + "\"."
                        + "\n\nPlease confirm if this name is correct...",
                        28f, width - 100, height / 3);

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

    // EFFECTS: creates a hello message
    private void generateHelloMessage() throws IOException, FontFormatException, InterruptedException {
        ui.clearMenu();
        createHelloText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(helloText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(continueButtonContainer);

        player.setPlayerName(name);
        ui.setPlayer(player);
    }

    // EFFECTS: creates the hello message text components
    private void createHelloText() throws IOException, FontFormatException {
        helloText = createJTextArea("Hello " + name + "!"
                        + "\nLet's move onto adopting a new pet!",
                28f, width - 100, height / 5);

        JButton continueButton = createJButton("Continue", "continueHelloClicked", this,
                24f, width, height / 10);
        continueButton.setBackground(BUTTON_COLOR_2);

        continueButtonContainer = createJPanel(TRANSPARENT, width, height);
        continueButtonContainer.add(continueButton);
        menu.getRootPane().setDefaultButton(continueButton);
    }

    // EFFECTS: helper for actionPerformed; performs the desired action
    protected void performAction(String command, JComponent source)
            throws IOException, FontFormatException, InterruptedException {
        if (command.equals("returnSessionLoadClicked")) {
            new MainMenu(ui, menu);
        } else if (command.equals("continueNameClicked")) {
            name = nameTextBox.getText();
            newPlayerConfirmName();
        } else if (command.equals("confirmYesClicked")) {
            generateHelloMessage();
        } else if (command.equals("confirmNoClicked")) {
            generateNamePrompt();
        } else if (command.equals("continueHelloClicked")) {
            new AdoptionMenu(ui, menu);
        }
    }

    // GETTERS:
    public Player getPlayer() {
        return player;
    }
}
