package ui.menus.mainmenu;

import model.exceptions.CannotFindSessionIdException;
import model.goodsandservices.AdoptionClinic;
import model.pets.Pet;
import ui.app.GuiApp;
import ui.menus.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.configurables.JComponentBuilder.*;
import static ui.configurables.JComponentBuilder.createJPanel;

// menu for adopting a pet
public class AdoptionMenu extends Menu {
    private JTextArea animalTypeText;
    private JPanel animalTypeContainer;
    private JTextArea breedText;
    private JPanel breedContainer;

    private JTextArea confirmPetTypeText;
    private JPanel confirmPetTypeButtonContainer;
    private JTextArea confirmPetNameText;
    private JPanel confirmPetNameButtonContainer;

    private JTextArea namePromptText;
    private JTextField nameTextBox;
    private JPanel continueButtonContainer;

    private JTextArea congratsHead;
    private JTextArea congratsText;

    private AdoptionClinic adoptionClinic;
    private Pet adoptedPet;

    private String selectedAnimalType;
    private String selectedBreed;

    private final String defaultName = "Pet";
    private String name;

    // EFFECTS: constructs the main menu
    public AdoptionMenu(GuiApp ui, JLayeredPane menu) throws IOException, FontFormatException {
        super(ui, menu);
        name = defaultName;
        initMenu();
    }

    // EFFECTS: initiates the adoption process
    protected void initMenu() throws IOException, FontFormatException {
        adoptionClinic = new AdoptionClinic();
        generateAnimalTypePrompt();
    }

    // EFFECTS: creates a prompt for selecting the pet's animal type
    private void generateAnimalTypePrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        createAnimalTypeText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(animalTypeText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(animalTypeContainer);
    }

    // EFFECTS: creates the prompt text components for animal type
    private void createAnimalTypeText() throws IOException, FontFormatException {
        ArrayList<String> allAnimalTypes = adoptionClinic.getAnimalTypes();

        animalTypeText = createJTextArea("Welcome to the Pixel Pet Adoption Clinic, "
                        + ui.getPlayer().getPlayerName() + "!"
                + "\n\nPlease select the type of pet you would like to adopt from the list below:",
                28f, width - 100, height / 3);

        animalTypeContainer = createJPanel(TRANSPARENT, width, height);

        createListButtons(allAnimalTypes, "AnimalTypeClicked", animalTypeContainer);

        menu.getRootPane().setDefaultButton((JButton) animalTypeContainer.getComponent(0));
    }

    // EFFECTS: creates a prompt for selecting the pet's breed
    private void generateBreedPrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        createBreedText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(breedText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(breedContainer);
    }

    // EFFECTS: creates the prompt text components for breed
    private void createBreedText() throws IOException, FontFormatException {
        ArrayList<String> allBreeds = adoptionClinic.fetchBreeds(selectedAnimalType);

        breedText = createJTextArea("Please select the breed of your "
                        + selectedAnimalType + " from the list below:",
                28f, width - 100, height / 4);

        breedContainer = createJPanel(TRANSPARENT, width, height);

        createListButtons(allBreeds, "BreedClicked", breedContainer);

        menu.getRootPane().setDefaultButton((JButton) breedContainer.getComponent(0));
    }

    // EFFECTS: creates a prompt asking for the player to confirm their pet choice
    private void generatePetTypeConfirm() throws IOException, FontFormatException {
        ui.clearMenu();
        createPetTypeConfirmText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(confirmPetTypeText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(confirmPetTypeButtonContainer);
    }

    // EFFECTS: helper for creating unknown count of buttons in a container
    private void createListButtons(ArrayList<String> allButtons, String actionCommand, JPanel container)
            throws IOException, FontFormatException {
        for (String buttonText : allButtons) {
            JButton button = createJButton(buttonText, actionCommand, this,
                    24f, width, height / 10);
            button.setName(buttonText);
            button.setBackground(BUTTON_COLOR_2);
            container.add(button);
        }
    }

    // EFFECTS: creates the pet type confirmation text components
    private void createPetTypeConfirmText() throws IOException, FontFormatException {
        confirmPetTypeText = createJTextArea("You have selected a pet " + selectedAnimalType
                        + " of the breed " + selectedBreed + "."
                        + "\n\nPlease confirm your selections...",
                28f, width - 100, height / 3);

        JButton yesButton = createJButton("Yes", "confirmYesPetTypeClicked", this,
                24f, width, height / 10);
        yesButton.setBackground(BUTTON_COLOR_2);

        JButton noButton = createJButton("No", "confirmNoPetTypeClicked", this,
                24f, width, height / 10);
        noButton.setBackground(BUTTON_COLOR_2);

        confirmPetTypeButtonContainer = createJPanel(TRANSPARENT, width, height);
        confirmPetTypeButtonContainer.add(yesButton);
        confirmPetTypeButtonContainer.add(noButton);
        menu.getRootPane().setDefaultButton(yesButton);
    }

    // EFFECTS: creates a prompt asking for the pet's name
    private void generateNamePrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        createNamePromptText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(namePromptText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(nameTextBox);
        menu.add(Box.createVerticalStrut(10));
        menu.add(continueButtonContainer);
    }

    // EFFECTS: creates the prompt text components for pet name
    private void createNamePromptText() throws IOException, FontFormatException {
        namePromptText = createJTextArea("Please give your pet " + selectedAnimalType + " a name!",
                28f, width - 100, height / 6);

        nameTextBox = createJTextField(selectedAnimalType, 24f, width / 2, height / 12,
                JTextField.CENTER, true);
        nameTextBox.setForeground(FIELD_TEXT_COLOR);

        JButton continueButton = createJButton("Continue", "continueNameClicked", this,
                24f, width, height / 10);
        continueButton.setBackground(BUTTON_COLOR_2);

        continueButtonContainer = createJPanel(TRANSPARENT, width, height);
        continueButtonContainer.add(continueButton);
        menu.getRootPane().setDefaultButton(continueButton);
    }

    // EFFECTS: creates a prompt asking for the player to confirm their pet's name
    private void petNameConfirm() throws IOException, FontFormatException {
        ui.clearMenu();
        checkValidName();
        createPetNameConfirmText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(confirmPetNameText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(confirmPetNameButtonContainer);
    }

    // MODIFIES: this
    // EFFECTS: changes the name to the defaultName if name is empty
    private void checkValidName() {
        if (name.equals("")) {
            name = defaultName;
        }
    }

    // EFFECTS: creates the confirmation text components
    private void createPetNameConfirmText() throws IOException, FontFormatException {
        confirmPetNameText = createJTextArea("You have selected your pet's name to be \"" + name + "\"."
                        + "\n\nPlease confirm if this name is correct...",
                28f, width - 100, height / 3);

        JButton yesButton = createJButton("Yes", "confirmYesPetNameClicked", this,
                24f, width, height / 10);
        yesButton.setBackground(BUTTON_COLOR_2);

        JButton noButton = createJButton("No", "confirmNoPetNameClicked", this,
                24f, width, height / 10);
        noButton.setBackground(BUTTON_COLOR_2);

        confirmPetNameButtonContainer = createJPanel(TRANSPARENT, width, height);
        confirmPetNameButtonContainer.add(yesButton);
        confirmPetNameButtonContainer.add(noButton);
        menu.getRootPane().setDefaultButton(yesButton);
    }
    
    // EFFECTS: creates a congratulations message
    private void generateCongratsMessage() throws IOException, FontFormatException {
        ui.clearMenu();
        createCongratsText();

        menu.add(Box.createVerticalStrut(height / 5));
        menu.add(congratsHead);
        menu.add(Box.createVerticalStrut(10));
        menu.add(congratsText);
        menu.add(Box.createVerticalStrut(10));
        menu.add(continueButtonContainer);

        adoptedPet = adoptionClinic.generatePet(selectedAnimalType, selectedBreed);
        adoptedPet.setName(name);
        ui.setPet(adoptedPet);
    }

    // EFFECTS: creates the congratulations message text components
    private void createCongratsText() throws IOException, FontFormatException {
        congratsHead = createJTextArea("Congratulations " + ui.getPlayer().getPlayerName() + "!",
                28f, width - 100, height / 8);

        congratsText = createJTextArea("The papers have been finalized"
                        + " and you have successfully adopted " + name + "!",
                28f, width - 100, height / 4);

        JButton continueButton = createJButton("Continue", "continueCongratsClicked", this,
                24f, width, height / 10);
        continueButton.setBackground(BUTTON_COLOR_2);

        continueButtonContainer = createJPanel(TRANSPARENT, width, height);
        continueButtonContainer.add(continueButton);
        menu.getRootPane().setDefaultButton(continueButton);
    }

    // EFFECTS: helper for actionPerformed; performs the desired action
    protected void performAction(String command, JComponent source)
            throws IOException, FontFormatException,
            InterruptedException, CannotFindSessionIdException {
        if (command.equals("AnimalTypeClicked")) {
            selectedAnimalType = source.getName();
            generateBreedPrompt();
        } else if (command.equals("BreedClicked")) {
            selectedBreed = source.getName();
            generatePetTypeConfirm();
        } else if (command.equals("confirmYesPetTypeClicked") || command.equals("confirmNoPetNameClicked")) {
            generateNamePrompt();
        } else if (command.equals("confirmNoPetTypeClicked")) {
            generateAnimalTypePrompt();
        } else if (command.equals("confirmYesPetNameClicked")) {
            name = nameTextBox.getText();
            generateCongratsMessage();
        } else if (command.equals("continueNameClicked")) {
            name = nameTextBox.getText();
            petNameConfirm();
        } else if (command.equals("continueCongratsClicked")) {
            ui.start(false, true);
        }
    }
}
