package ui.menus;

import model.Player;

import static ui.TerminalApp.scanner;

public class NewPlayerMenu {
    private static Player player;

    // MODIFIES: Player
    // EFFECTS: initiates the new player process
    public static Player initNewPlayer() {
        player = new Player();
        String name = "";


        boolean confirmedName = false;
        while (!confirmedName) {
            System.out.println("What is your name?");
            name = scanner.nextLine();
            System.out.println("");
            confirmedName = newPlayerConfirmName(name);
        }
        player.setPlayerName(name);

        System.out.println(String.format("Hello %s!", player.getPlayerName()));

        return player;
    }

    // EFFECTS: returns true if user confirms player name
    private static boolean newPlayerConfirmName(String name) {
        System.out.println("You have entered your name as " + name);
        System.out.println("Enter 'y' to confirm your name "
                + "or enter any other key to go back.");
        String choice = scanner.nextLine();
        System.out.println("");

        return (choice.equals("y"));
    }
}
