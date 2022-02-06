package ui.menus;

import model.Player;

public class InventoryMenu {
    // EFFECTS: displays the amount of money the player has
    public static void checkMoney(Player player) {
        System.out.println("You have $" + player.getMoney() + ".");
    }
}
