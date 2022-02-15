package ui.menus;

import static ui.configurables.Commands.*;

// menu for referencing in-game commands
public class CommandsMenu {
    // EFFECTS: prints out the controls for opening menus and playing the game
    public static void showControls() {
        String controls = "- '" + COMMANDS_KEY + "' || COMMANDS || View all the commands again\n"
                        + "- '" + CHECK_PET_KEY + "' || PET || View your pet's stats\n"
                        + "- '" + OPEN_SHOP_KEY + "' || SHOP || Open the shop menu\n"
                        + "- '" + OPEN_INVENTORY_KEY + "' || INVENTORY || View your inventory\n"
                        + "- '" + VIEW_MONEY_KEY + "' || MONETARY || Check how much money you have\n";

        System.out.println("\nTo access a service, enter in the corresponding command:");
        System.out.println("[ COMMAND || SERVICE TYPE || DESCRIPTION ]");
        System.out.println(controls);
    }
}
