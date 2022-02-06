package ui.menus;

import static ui.menus.Commands.*;

public class TutorialMenu {
    // EFFECTS: prints out the controls for opening menus and playing the game
    public static void showControls() {
        String controls = "'" + CHECK_PET_KEY + "' || View your pet's stats\n"
                        + "'" + OPEN_SHOP_KEY + "' || Open the shop menu\n"
                        + "'" + OPEN_INVENTORY_KEY + "' || View your inventory\n";

        System.out.println("\nTo access a service, enter in the command:\n" + controls);
    }
}
