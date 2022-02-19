package ui.menus;

import model.exceptions.CannotFindSessionIdException;
import model.persistence.*;
import ui.app.PixelPetGame;

import java.io.IOException;

import static ui.app.TerminalApp.scanner;
import static ui.configurables.Commands.*;

// menu for loading or saving game data
public class PersistenceMenu {
    // EFFECTS: displays the menu for saving data
    public static void displaySaveMenu(PixelPetGame game) {
        System.out.println("Are you sure you'd like to save the current session?");
        if (confirmationMenu()) {
            saveGameData(game);
        } else {
            System.out.println("Your current session was not saved.");
        }
    }

    // EFFECTS: returns true if user confirms saving the game
    private static boolean confirmationMenu() {
        System.out.println("Please confirm by entering '"
                + CONFIRMATION_KEY
                + "' or enter any other key to cancel.");
        String choice = scanner.nextLine();

        return (choice.equals(CONFIRMATION_KEY));
    }

    // EFFECTS: saves the current session's game data
    public static void saveGameData(PixelPetGame game) {
        try {
            new PersistenceWriter(game);
            System.out.println("Data was saved successfully!");
        } catch (IOException e) {
            System.err.println("Failed to save data!");
        }
    }

    // EFFECTS: displays the menu for loading data
    public static void displayLoadMenu(PixelPetGame game) {
        System.out.println("Please enter your personal ID to continue,\n"
                            + "or enter any other key to exit the load menu.");
        int sessionId = 0;
        String command = scanner.nextLine();

        try {
            sessionId = Integer.parseInt(command);

            System.out.println("Are you sure you'd like to load this session?");
            if (confirmationMenu()) {
                loadGameData(game, sessionId);
            } else {
                System.out.println("Your selected session was not loaded.");
            }
        } catch (NumberFormatException e) {
            System.out.println("You have left the load menu.");
        }
    }

    // EFFECTS: loads game data from a previous session
    public static void loadGameData(PixelPetGame game, int sessionId) {
        try {
            new PersistenceReader(game, sessionId);
            System.out.println("Data was loaded successfully!");
        } catch (CannotFindSessionIdException e) {
            System.err.println("Failed to load session!");
        } catch (IOException e) {
            System.err.println("Failed to read data!");
        }
    }
}
