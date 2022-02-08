package ui.menus;

import model.Player;

import static ui.app.TerminalApp.scanner;
import static ui.configurables.Commands.*;

public class NewPlayerMenu {
    // EFFECTS: initiates the new player process
    public static Player initNewPlayer(int startingMoney) {
        Player player = new Player();
        player.setMoney(startingMoney);
        String name = "";

        boolean confirmedName = false;
        while (!confirmedName) {
            System.out.println("Hello player! What is your name?");
            name = scanner.nextLine();
            confirmedName = newPlayerConfirmName(name);
        }
        player.setPlayerName(name);

        System.out.println("\nHello " + player.getPlayerName() + "!");

        return player;
    }

    // EFFECTS: returns true if user confirms player name
    private static boolean newPlayerConfirmName(String name) {
        System.out.println("\nYou have entered your name as \"" + name + "\".");
        System.out.println("Confirm this name by entering '"
                            + CONFIRMATION_KEY
                            + "' or enter any other key to redo your choice.");
        String choice = scanner.nextLine();

        return (choice.equals(CONFIRMATION_KEY));
    }
}
