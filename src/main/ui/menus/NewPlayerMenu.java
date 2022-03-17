package ui.menus;

import model.Player;
import ui.app.GuiApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import static ui.menus.JComponentBuilder.*;
import static ui.configurables.InterfaceAesthetics.*;

// menu for creating a new player
public class NewPlayerMenu extends Menu {
    private JTextArea promptText;
    private JTextField nameTextBox;
    private JPanel continueButtonContainer;

    private JTextArea confirmText;
    private JPanel confirmButtonContainer;

    private String name;
    private Player player;

    // EFFECTS: constructs the main menu
    public NewPlayerMenu(GuiApp ui, JLayeredPane menu, int startingMoney) throws IOException, FontFormatException {
        super(ui, menu);
        name = "Player";
        initMenu(startingMoney);
    }

    // EFFECTS: initiates the new player process
    public void initMenu(int startingMoney) throws IOException, FontFormatException {
        player = new Player();
        player.setMoney(startingMoney);
        generateNamePrompt();
    }

    // EFFECTS: creates a prompt asking for the player's name
    private void generateNamePrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        JPanel nameBox = createJPanel(TRANSPARENT, width, height);
        nameBox.setLayout(new BoxLayout(nameBox, BoxLayout.Y_AXIS));

        createPromptText();

        menu.add(Box.createVerticalStrut(height / 5));
        nameBox.add(promptText);
        nameBox.add(Box.createVerticalStrut(10));
        nameBox.add(nameTextBox);
        nameBox.add(Box.createVerticalStrut(10));
        nameBox.add(continueButtonContainer);

        menu.add(nameBox);
    }

    // EFFECTS: creates the prompt text components
    private void createPromptText() throws IOException, FontFormatException {
        promptText = createJTextArea("Hello player, and welcome to Pixel Pet!\n"
                        + "Please enter your player name in the box below...",
                28f, width - 100, height / 6);

        nameTextBox = createJTextField("Player", 24f, width / 2, height / 12,
                        JTextField.CENTER, true);
        nameTextBox.setForeground(FIELD_TEXT_COLOR);

        JButton continueButton = createJButton("Continue", "continueNameClicked", this,
                        24f, width, height / 10);
        continueButton.setBackground(BUTTON_COLOR_2);

        continueButtonContainer = createJPanel(TRANSPARENT, width, height);
        continueButtonContainer.add(continueButton);
    }

    // EFFECTS: creates a prompt asking for the player to confirm their name
    private void newPlayerConfirmName() throws IOException, FontFormatException {
        ui.clearMenu();
        JPanel confirmBox = createJPanel(TRANSPARENT, width, height);
        confirmBox.setLayout(new BoxLayout(confirmBox, BoxLayout.Y_AXIS));

        createConfirmText();

        menu.add(Box.createVerticalStrut(height / 5));
        confirmBox.add(confirmText);
        confirmBox.add(Box.createVerticalStrut(10));
        confirmBox.add(confirmButtonContainer);

        menu.add(confirmBox);
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
    }

    // EFFECTS: creates a hello message
    private void generateHelloMessage() throws IOException, FontFormatException, InterruptedException {
        ui.clearMenu();
        JPanel helloBox = createJPanel(TRANSPARENT, width, height);
        helloBox.setLayout(new BoxLayout(helloBox, BoxLayout.Y_AXIS));

        JTextArea helloText = createJTextArea("Hello " + name + "!"
                + "\nLet's move onto adopting a new pet!",
                28f, width - 100, height / 5);

        JButton continueButton = createJButton("Continue", "continueHelloClicked", this,
                24f, width, height / 10);
        continueButton.setBackground(BUTTON_COLOR_2);

        JPanel continueButtonContainer = createJPanel(TRANSPARENT, width, height);
        continueButtonContainer.add(continueButton);

        menu.add(Box.createVerticalStrut(height / 5));
        helloBox.add(helloText);
        helloBox.add(Box.createVerticalStrut(10));
        helloBox.add(continueButtonContainer);
        menu.add(helloBox);

        player.setPlayerName(name);
        ui.setPlayer(player);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch (command) {
                case "continueNameClicked": {
                    name = nameTextBox.getText();
                    newPlayerConfirmName();
                }
                break;
                case "confirmYesClicked": generateHelloMessage();
                    break;
                case "confirmNoClicked": generateNamePrompt();
                    break;
                case "continueHelloClicked": new AdoptionMenu(ui, menu);
                    break;
                default: break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // GETTERS:
    public Player getPlayer() {
        return player;
    }
}
