package ui.menus.ingame;

import model.Player;
import model.pets.Pet;
import model.goodsandservices.Item;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static ui.app.GuiApp.scanner;
import static ui.configurables.Commands.*;

// menu for interacting with the player inventory
public class InventoryMenu {
    private static final String regex = "^[^\\s][a-zA-Z'\\d\\s]{1,}[^\\s]" + INVENTORY_SEPARATOR_KEY
            + "[^\\s][a-zA-Z\\d\\s]{1,}[^\\s]" + INVENTORY_SEPARATOR_KEY
            + "[1-9]\\d{0,}$";
    private static final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    // EFFECTS: displays the amount of money the player has
    public static void checkMoney(Player player) {
        System.out.println("You currently have $" + player.getMoney() + ".");
    }

    // EFFECTS: displays a list of items in the player's inventory
    public static void viewInventory(Player player, Pet pet) {
        ArrayList<Item> inventory = player.getInventory();
        int inventorySize = inventory.size();

        if (inventorySize != 0) {
            System.out.println("Here's a list of items in your inventory:");
            System.out.println("[ NAME (TYPE) [# IN INVENTORY] || "
                    + "{HAPPINESS, HUNGER, THIRST, HEALTH GAINS} ]");

            for (int i = 0; i < inventorySize; i++) {
                Item item = inventory.get(i);

                System.out.println("- " + item.getName() + " ("
                        + item.getType() + ") [x"
                        + player.getInventoryQuantity().get(i) + "] || {"
                        + item.getHappinessPoints() + ", "
                        + item.getHungerPoints() + ", "
                        + item.getThirstPoints() + ", "
                        + item.getHealthPoints() + "}");
            }

            displayCommands();
            awaitCommands(player, pet);
        } else {
            System.out.println("[ Your inventory is currently empty... ]\n");
        }
    }

    // EFFECTS: displays all possible commands in the current menu
    private static void displayCommands() {
        System.out.println("\nTo give your pet an item, enter in '" + GIVE_TO_KEY + "' followed by the item name,"
                + "\nitem type, and the quantity you would like to give to your pet using '"
                + INVENTORY_SEPARATOR_KEY + "' as a separator."
                + "\nYour command should follow the format 'give item name" + INVENTORY_SEPARATOR_KEY
                + "item type" + INVENTORY_SEPARATOR_KEY + "quantity'."
                + "\nEx. '" + GIVE_TO_KEY + " Ball of Yarn" + INVENTORY_SEPARATOR_KEY + "Toy" + INVENTORY_SEPARATOR_KEY
                + "1', '" + GIVE_TO_KEY + " chicken" + INVENTORY_SEPARATOR_KEY + "food"
                + INVENTORY_SEPARATOR_KEY + "99'");
        System.out.println("Otherwise, enter in '" + EXIT_MENU_KEY + "' to leave the inventory menu.");
    }

    // EFFECTS: awaits and handles user input for commands
    private static void awaitCommands(Player player, Pet pet) {
        boolean hasExited = false;

        while (!hasExited) {
            String command = scanner.nextLine();

            if (command.startsWith(GIVE_TO_KEY + " ")) {
                command = command.replaceAll(GIVE_TO_KEY + " ", "");

                if (pattern.matcher(command).find()) {
                    giveItemsTo(command, pet, player);
                }
            } else if (command.equals(EXIT_MENU_KEY)) {
                hasExited = true;
                System.out.println("You have left the inventory menu.");
            }
        }
    }

    // REQUIRES: command has three clauses, separated by SEPARATOR_KEY,
    //           and of the format 'String''SEPARATOR_KEY''String''SEPARATOR_KEY''Integer'
    // EFFECTS: checks to see if buying the item is valid,
    //          then handles the player transaction if valid
    private static void giveItemsTo(String command, Pet pet, Player player) {
        String[] extractedCommand = command.split(INVENTORY_SEPARATOR_KEY);
        String itemName = extractedCommand[0];
        String itemType = extractedCommand[1];
        int quantity = Integer.parseInt(extractedCommand[2]);
        Item itemToCheck = validItem(itemName, itemType, player);

        if (itemToCheck != null) {
            if (player.giveItemTo(itemToCheck, pet, quantity)) {
                System.out.println("You have successfully gave "
                        + itemToCheck.getName()
                        + " (x" + quantity + ") to " + pet.getName() + "!");
            } else {
                System.out.println("Failed to give item. You don't have that quantity to give!");
            }
        } else {
            System.out.println("Error! Cannot find item \"" + itemName + "\" of the type \""
                                + itemType + "\" in inventory!");
            System.out.println("Be sure to enter the item name and type correctly!\n");
        }
    }

    // EFFECTS: returns the item if there exists an item of the name itemName and type itemType
    private static Item validItem(String itemName, String itemType, Player player) {
        ArrayList<Item> inventory = player.getInventory();

        for (Item item : inventory) {
            String comparingName = item.getName().toLowerCase();
            String comparingType = item.getType().toLowerCase();
            boolean foundItemName = (comparingName.equals(itemName.toLowerCase()));
            boolean foundItemType = (comparingType.equals(itemType.toLowerCase()));

            if (foundItemName && foundItemType) {
                return item;
            }
        }
        return null;
    }
}
