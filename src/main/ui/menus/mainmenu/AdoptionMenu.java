package ui.menus.mainmenu;

import model.exceptions.CannotFindSessionIdException;
import model.goodsandservices.AdoptionClinic;
import model.pets.Pet;
import ui.app.GuiApp;
import ui.menus.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.menus.JComponentBuilder.*;
import static ui.menus.JComponentBuilder.createJPanel;

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
    public void initMenu() throws IOException, FontFormatException {
        adoptionClinic = new AdoptionClinic();
        generateAnimalTypePrompt();
    }

    // EFFECTS: creates a prompt for selecting the pet's animal type
    private void generateAnimalTypePrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        JPanel petBox = createJPanel(TRANSPARENT, width, height);
        petBox.setLayout(new BoxLayout(petBox, BoxLayout.Y_AXIS));

        createAnimalTypeText();

        menu.add(Box.createVerticalStrut(height / 5));
        petBox.add(animalTypeText);
        petBox.add(Box.createVerticalStrut(10));
        petBox.add(animalTypeContainer);

        menu.add(petBox);
    }

    // EFFECTS: creates the prompt text components for animal type
    private void createAnimalTypeText() throws IOException, FontFormatException {
        ArrayList<String> allAnimalTypes = adoptionClinic.getAnimalTypes();

        animalTypeText = createJTextArea("Welcome to the Pixel Pet Adoption Clinic, "
                        + ui.getPlayer().getPlayerName() + "!"
                + "\nPlease select the type of pet you would like to adopt from the list below:",
                28f, width - 100, height / 4);

        animalTypeContainer = createJPanel(TRANSPARENT, width, height);

        for (String animalType : allAnimalTypes) {
            JButton animalButton = createJButton(animalType, "AnimalTypeClicked", this,
                    24f, width, height / 10);
            animalButton.setName(animalType);
            animalButton.setBackground(BUTTON_COLOR_2);
            animalTypeContainer.add(animalButton);
        }
    }

    // EFFECTS: creates a prompt for selecting the pet's breed
    private void generateBreedPrompt() throws IOException, FontFormatException {
        ui.clearMenu();
        JPanel petBox = createJPanel(TRANSPARENT, width, height);
        petBox.setLayout(new BoxLayout(petBox, BoxLayout.Y_AXIS));

        createBreedText();

        menu.add(Box.createVerticalStrut(height / 5));
        petBox.add(breedText);
        petBox.add(Box.createVerticalStrut(10));
        petBox.add(breedContainer);

        menu.add(petBox);
    }

    // EFFECTS: creates the prompt text components for breed
    private void createBreedText() throws IOException, FontFormatException {
        ArrayList<String> allBreeds = adoptionClinic.fetchBreeds(selectedAnimalType);

        breedText = createJTextArea("Please select the breed of your "
                        + selectedAnimalType + " from the list below:",
                28f, width - 100, height / 4);

        breedContainer = createJPanel(TRANSPARENT, width, height);

        for (String breed : allBreeds) {
            JButton breedButton = createJButton(breed, "BreedClicked", this,
                    24f, width, height / 10);
            breedButton.setName(breed);
            breedButton.setBackground(BUTTON_COLOR_2);
            breedContainer.add(breedButton);
        }
    }

    // EFFECTS: creates a prompt asking for the player to confirm their pet choice
    private void generatePetTypeConfirm() throws IOException, FontFormatException {
        ui.clearMenu();
        JPanel confirmBox = createJPanel(TRANSPARENT, width, height);
        confirmBox.setLayout(new BoxLayout(confirmBox, BoxLayout.Y_AXIS));

        createPetTypeConfirmText();

        menu.add(Box.createVerticalStrut(height / 5));
        confirmBox.add(confirmPetTypeText);
        confirmBox.add(Box.createVerticalStrut(10));
        confirmBox.add(confirmPetTypeButtonContainer);

        menu.add(confirmBox);
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
        JPanel nameBox = createJPanel(TRANSPARENT, width, height);
        nameBox.setLayout(new BoxLayout(nameBox, BoxLayout.Y_AXIS));

        createNamePromptText();

        menu.add(Box.createVerticalStrut(height / 5));
        nameBox.add(namePromptText);
        nameBox.add(Box.createVerticalStrut(10));
        nameBox.add(nameTextBox);
        nameBox.add(Box.createVerticalStrut(10));
        nameBox.add(continueButtonContainer);

        menu.add(nameBox);
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
        JPanel confirmBox = createJPanel(TRANSPARENT, width, height);
        confirmBox.setLayout(new BoxLayout(confirmBox, BoxLayout.Y_AXIS));

        createPetNameConfirmText();

        menu.add(Box.createVerticalStrut(height / 5));
        confirmBox.add(confirmPetNameText);
        confirmBox.add(Box.createVerticalStrut(10));
        confirmBox.add(confirmPetNameButtonContainer);

        menu.add(confirmBox);
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
        JPanel congratsBox = createJPanel(TRANSPARENT, width, height);
        congratsBox.setLayout(new BoxLayout(congratsBox, BoxLayout.Y_AXIS));

        JTextArea congratsText = createJTextArea("Congratulations " + ui.getPlayer().getPlayerName() + "!"
                + "\nThe papers have been finalized and you have successfully adopted " + name + "!",
                28f, width - 100, height / 5);

        JButton continueButton = createJButton("Continue", "continueCongratsClicked", this,
                24f, width, height / 10);
        continueButton.setBackground(BUTTON_COLOR_2);

        JPanel continueButtonContainer = createJPanel(TRANSPARENT, width, height);
        continueButtonContainer.add(continueButton);
        menu.getRootPane().setDefaultButton(continueButton);

        menu.add(Box.createVerticalStrut(height / 5));
        congratsBox.add(congratsText);
        congratsBox.add(Box.createVerticalStrut(10));
        congratsBox.add(continueButtonContainer);
        menu.add(congratsBox);

        adoptedPet = adoptionClinic.generatePet(selectedAnimalType, selectedBreed);
        adoptedPet.setName(name);
        ui.setPet(adoptedPet);
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
            ui.start(false, false);
        }
    }
}
