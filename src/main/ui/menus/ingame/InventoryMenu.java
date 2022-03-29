package ui.menus.ingame;

import model.Player;
import model.goodsandservices.Item;
import model.goodsandservices.Shop;
import model.pets.Pet;
import ui.app.GuiApp;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static ui.configurables.InterfaceAesthetics.*;
import static ui.configurables.JComponentBuilder.*;

// represents the shop menu
public class InventoryMenu {
    private static final TitledBorder inventoryItemsBorder = BorderFactory.createTitledBorder("Items");
    private static JPanel inventoryItemsContainer;
    private static final TitledBorder inventoryItemInfoBorder = BorderFactory.createTitledBorder("Information");
    private static JPanel inventoryItemInfoContainer;
    private static JTextArea inventoryPlayerMoneyText;
    private static JTextArea inventoryItemNameText;
    private static JTextArea inventoryItemTypeText;
    private static JTextArea inventoryItemQuantityText;
    private static JPanel inventoryItemUseButtonContainer;

    private static String inventorySelectedItemName = "";
    private static String inventorySelectedItemType = "";

    // EFFECTS: generates inventory menu
    public static void generateInventory(GuiApp ui, JLayeredPane menu, GameMenu gameMenu,
                                         JPanel promptContainer, int width, int height)
            throws IOException, FontFormatException {
        promptContainer.removeAll();
        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));
        inventorySelectedItemName = "";
        inventorySelectedItemType = "";

        JButton backButton = createJButton("Back", "backClicked", gameMenu,
                24f, width, 40);
        backButton.setBackground(BUTTON_COLOR_2);

        JPanel backButtonContainer = createJPanel(TRANSPARENT, width, 48);
        backButtonContainer.add(backButton);

        generateInventoryItemsPanel(ui, width);
        generateInventoryItemInfoPanel(ui, gameMenu, width);

        promptContainer.add(Box.createVerticalStrut(50));
        promptContainer.add(backButtonContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(inventoryItemsContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(inventoryItemInfoContainer);
        gameMenu.togglePrompts(true);
        gameMenu.setInventoryMenuLoaded(true);
    }

    // EFFECTS: generates the items list component for inventory menu
    private static void generateInventoryItemsPanel(GuiApp ui, int width) throws IOException, FontFormatException {
        inventoryItemsContainer = createJPanel(GAME_BAR_UI_COLOR, width - 200, 320);
        inventoryItemsContainer.setLayout(new BoxLayout(inventoryItemsContainer, BoxLayout.Y_AXIS));
        inventoryItemsContainer.setBorder(inventoryItemsBorder);
        inventoryItemsBorder.setTitleColor(BUTTON_TEXT_COLOR);
        createInventoryItemsPanel(ui, width, 20f);
    }

    // EFFECTS: creates the inventory item buttons
    public static void createInventoryItemsPanel(GuiApp ui, int width, float fontSize)
            throws IOException, FontFormatException {
        Player player = ui.getGame().getPlayer();
        ArrayList<Item> items = player.getInventory();
        ArrayList<Integer> quantities = player.getInventoryQuantity();

        inventoryItemsContainer.removeAll();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            JPanel itemContainer = createJPanel(GAME_BAR_UI_COLOR, width - 200, 56);
            JButton itemButton = createJButton(item.getType() + ": '" + item.getName()
                            + "' x" + quantities.get(i),
                    item.getName() + "~" + item.getType()
                            + "~" + quantities.get(i),
                    inventoryButtonClicked, fontSize, width - 200, 48);
            itemButton.setPreferredSize(new Dimension(width - 300, 48));


            item.getSpritesDir();
            itemContainer.add(itemButton);
            itemContainer.add(new JLabel(createItemIcon(item,48, 48)));
            inventoryItemsContainer.add(itemContainer);
        }

        JPanel pseudoContainer = createJPanel(TRANSPARENT, width - 200, 56);
        pseudoContainer.setPreferredSize(new Dimension(width - 200, 56));
        inventoryItemsContainer.add(pseudoContainer);
    }

    // ActionListener for inventory item buttons
    private static final ActionListener inventoryButtonClicked = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> parsedInfo =
                    new ArrayList<>(Arrays.asList(e.getActionCommand().split("~")));
            String itemName = parsedInfo.get(0);
            String itemType = parsedInfo.get(1);

            inventorySelectedItemName = itemName;
            inventorySelectedItemType = itemType;

            inventoryItemNameText.setText("Item Name: " + itemName);
            inventoryItemTypeText.setText("Item Type: " + itemType);
            inventoryItemQuantityText.setText("Quantity: " + parsedInfo.get(2));
        }
    };

    // EFFECTS: generates the item info component for inventory menu
    private static void generateInventoryItemInfoPanel(GuiApp ui, GameMenu gameMenu, int width)
            throws IOException, FontFormatException {
        createInventoryItemInfoPanel(ui, gameMenu, width, 20f);
        inventoryItemInfoContainer.add(inventoryItemNameText);
        inventoryItemInfoContainer.add(Box.createVerticalStrut(5));
        inventoryItemInfoContainer.add(inventoryItemTypeText);
        inventoryItemInfoContainer.add(Box.createVerticalStrut(5));
//        inventoryItemInfoContainer.add(inventoryItemQuantityText);
//        inventoryItemInfoContainer.add(Box.createVerticalStrut(5));
        inventoryItemInfoContainer.add(inventoryItemUseButtonContainer);
        inventoryItemInfoContainer.add(Box.createVerticalStrut(5));
        inventoryItemInfoContainer.add(inventoryPlayerMoneyText);
    }

    // EFFECTS: creates the inventory item buttons
    private static void createInventoryItemInfoPanel(GuiApp ui, GameMenu gameMenu, int width, float fontSize)
            throws IOException, FontFormatException {
        inventoryItemNameText = createJTextArea("Item Name: ",
                fontSize, width / 2, 50);
        inventoryItemTypeText = createJTextArea("Item Type: ",
                fontSize, width / 2, 50);
        inventoryItemQuantityText = createJTextArea("Quantity: ",
                fontSize, width / 2, 50);

        inventoryItemUseButtonContainer = createJPanel(TRANSPARENT, width, 58);
        JButton useButton = createJButton("Use", "inventoryItemUseClicked", gameMenu,
                fontSize, width / 2, 50);
        useButton.setPreferredSize(new Dimension(width / 2, 50));
        useButton.setBackground(BUTTON_COLOR_2);
        inventoryItemUseButtonContainer.add(useButton);

        inventoryPlayerMoneyText = createJTextArea("Money: " + ui.getGame().getPlayer().getMoney(),
                fontSize, width / 2, 50);

        inventoryItemInfoContainer = createJPanel(GAME_BAR_UI_COLOR,width - 200, (50 + 8 + 5) * 4);
        inventoryItemInfoContainer.setLayout(new BoxLayout(inventoryItemInfoContainer, BoxLayout.Y_AXIS));
        inventoryItemInfoContainer.setBorder(inventoryItemInfoBorder);
        inventoryItemInfoBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: updates the shop menu ui
    public static void updateInventoryMenu(GuiApp ui) {
        inventoryPlayerMoneyText.setText("Money: $" + ui.getPlayer().getMoney());
    }

    // EFFECTS: checks to see if giving the item is valid,
    //          then handles the player-to-pet transaction if valid
    public static void giveItemToPet(GuiApp ui) {
        Player player = ui.getGame().getPlayer();
        Pet pet = ui.getGame().getPet();
        Item itemToCheck = ShopMenu.validItem(player.getInventory(),
                inventorySelectedItemName, inventorySelectedItemType);

        if (itemToCheck != null && player.giveItemTo(itemToCheck, pet, 1)) {
            // successfully gave item
        }
    }
}
