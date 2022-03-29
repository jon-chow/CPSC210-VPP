package ui.menus.ingame;

import model.Player;
import model.goodsandservices.Item;
import model.goodsandservices.Shop;
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
import static ui.configurables.JComponentBuilder.createJPanel;

// represents the shop menu
public class ShopMenu {
    private static final String shopName = "Kira Kira Pets";
    private static final TitledBorder shopItemsBorder = BorderFactory.createTitledBorder("Items");
    private static JPanel shopItemsContainer;

    private static final TitledBorder shopItemInfoBorder = BorderFactory.createTitledBorder("Information");
    private static JPanel shopItemInfoContainer;
    private static JTextArea shopPlayerMoneyText;
    private static JTextArea shopItemNameText;
    private static JTextArea shopItemTypeText;
    private static JTextArea shopItemPriceText;
    private static JTextArea shopItemQuantityText;
    private static JPanel shopItemBuyButtonContainer;

    private static String shopSelectedItemName = "";
    private static String shopSelectedItemType = "";

    // EFFECTS: generates shop menu
    public static void generateShop(GuiApp ui, JLayeredPane menu, GameMenu gameMenu,
                              JPanel promptContainer, int width, int height)
            throws IOException, FontFormatException {
        promptContainer.removeAll();
        promptContainer.setLayout(new BoxLayout(promptContainer, BoxLayout.Y_AXIS));
        shopSelectedItemName = "";
        shopSelectedItemType = "";

        JButton backButton = createJButton("Back", "backClicked", gameMenu,
                24f, width, 40);
        backButton.setBackground(BUTTON_COLOR_2);

        JPanel backButtonContainer = createJPanel(TRANSPARENT, width, 48);
        backButtonContainer.add(backButton);

        generateShopItemsPanel(ui, width);
        generateShopItemInfoPanel(ui, gameMenu, width);

        promptContainer.add(Box.createVerticalStrut(50));
        promptContainer.add(backButtonContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(shopItemsContainer);
        promptContainer.add(Box.createVerticalStrut(5));
        promptContainer.add(shopItemInfoContainer);
        gameMenu.togglePrompts(true);
        gameMenu.setShopMenuLoaded(true);
    }

    // EFFECTS: generates the items list component for shop menu
    private static void generateShopItemsPanel(GuiApp ui, int width) throws IOException, FontFormatException {
        shopItemsContainer = createJPanel(GAME_BAR_UI_COLOR, width - 200, 320);
        shopItemsContainer.setLayout(new BoxLayout(shopItemsContainer, BoxLayout.Y_AXIS));
        shopItemsContainer.setBorder(shopItemsBorder);
        shopItemsBorder.setTitleColor(BUTTON_TEXT_COLOR);
        createShopItemsPanel(ui, width, 20f);
    }

    // EFFECTS: creates the shop item buttons
    public static void createShopItemsPanel(GuiApp ui, int width, float fontSize)
            throws IOException, FontFormatException {
        Shop shop = ui.getGame().getShopByName(shopName);
        ArrayList<Item> items = shop.getShopItems();
        ArrayList<Integer> quantities = shop.getQuantityInStock();

        shopItemsContainer.removeAll();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            JPanel itemContainer = createJPanel(GAME_BAR_UI_COLOR, width - 200, 56);
            JButton itemButton = createJButton(item.getType() + ": '" + item.getName()
                            + "' x" + quantities.get(i) + " ($" + item.getPrice() + ")",
                    item.getName() + "~" + item.getType()
                            + "~$" + item.getPrice() + "~" + quantities.get(i),
                    shopButtonClicked, fontSize, width - 200, 48);
            itemButton.setPreferredSize(new Dimension(width - 300, 48));


            item.getSpritesDir();
            itemContainer.add(itemButton);
            itemContainer.add(new JLabel(createItemIcon(item, 48, 48)));
            shopItemsContainer.add(itemContainer);
        }

        JPanel pseudoContainer = createJPanel(TRANSPARENT, width - 200, 56);
        pseudoContainer.setPreferredSize(new Dimension(width - 200, 56));
        shopItemsContainer.add(pseudoContainer);
    }

    // ActionListener for shop item buttons
    private static final ActionListener shopButtonClicked = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> parsedInfo =
                    new ArrayList<>(Arrays.asList(e.getActionCommand().split("~")));
            String itemName = parsedInfo.get(0);
            String itemType = parsedInfo.get(1);

            shopSelectedItemName = itemName;
            shopSelectedItemType = itemType;

            shopItemNameText.setText("Item Name: " + itemName);
            shopItemTypeText.setText("Item Type: " + itemType);
            shopItemPriceText.setText("Price: " + parsedInfo.get(2));
            shopItemQuantityText.setText("Quantity: " + parsedInfo.get(3));
        }
    };

    // EFFECTS: generates the item info component for shop menu
    private static void generateShopItemInfoPanel(GuiApp ui, GameMenu gameMenu, int width)
            throws IOException, FontFormatException {
        createShopItemInfoPanel(ui, gameMenu, width, 20f);
        shopItemInfoContainer.add(shopItemNameText);
        shopItemInfoContainer.add(Box.createVerticalStrut(5));
        shopItemInfoContainer.add(shopItemTypeText);
        shopItemInfoContainer.add(Box.createVerticalStrut(5));
//        shopItemInfoContainer.add(shopItemPriceText);
//        shopItemInfoContainer.add(Box.createVerticalStrut(5));
//        shopItemInfoContainer.add(shopItemQuantityText);
//        shopItemInfoContainer.add(Box.createVerticalStrut(5));
        shopItemInfoContainer.add(shopItemBuyButtonContainer);
        shopItemInfoContainer.add(Box.createVerticalStrut(5));
        shopItemInfoContainer.add(shopPlayerMoneyText);
    }

    // EFFECTS: creates the shop item buttons
    private static void createShopItemInfoPanel(GuiApp ui, GameMenu gameMenu, int width, float fontSize)
            throws IOException, FontFormatException {
        shopItemNameText = createJTextArea("Item Name: ",
                fontSize, width / 2, 50);
        shopItemTypeText = createJTextArea("Item Type: ",
                fontSize, width / 2, 50);
        shopItemPriceText = createJTextArea("Price: ",
                fontSize, width / 2, 50);
        shopItemQuantityText = createJTextArea("Quantity: ",
                fontSize, width / 2, 50);

        shopItemBuyButtonContainer = createJPanel(TRANSPARENT, width, 58);
        JButton buyButton = createJButton("Buy", "shopItemBuyClicked", gameMenu,
                fontSize, width / 2, 50);
        buyButton.setPreferredSize(new Dimension(width / 2, 50));
        buyButton.setBackground(BUTTON_COLOR_2);
        shopItemBuyButtonContainer.add(buyButton);

        shopPlayerMoneyText = createJTextArea("Money: " + ui.getGame().getPlayer().getMoney(),
                fontSize, width / 2, 50);

        shopItemInfoContainer = createJPanel(GAME_BAR_UI_COLOR,width - 200, (50 + 8 + 5) * 4);
        shopItemInfoContainer.setLayout(new BoxLayout(shopItemInfoContainer, BoxLayout.Y_AXIS));
        shopItemInfoContainer.setBorder(shopItemInfoBorder);
        shopItemInfoBorder.setTitleColor(BUTTON_TEXT_COLOR);
    }

    // EFFECTS: updates the shop menu ui
    public static void updateShopMenu(GuiApp ui) {
        shopPlayerMoneyText.setText("Money: $" + ui.getPlayer().getMoney());
    }

    // EFFECTS: checks to see if buying the item is valid,
    //          then handles the player transaction if valid
    public static void buyItem(GuiApp ui) {
        Player player = ui.getGame().getPlayer();
        Shop shop = ui.getGame().getShopByName(shopName);
        Item itemToCheck = validItem(shop.getShopItems(), shopSelectedItemName, shopSelectedItemType);

        if (itemToCheck != null && player.buyItemFrom(itemToCheck, 1, shop)) {
            shopPlayerMoneyText.setText("Money: $" + player.getMoney());
        }
    }

    // EFFECTS: returns the item if there exists an item of the name itemName and type itemType
    public static Item validItem(ArrayList<Item> itemsList, String itemName, String itemType) {
        for (Item item : itemsList) {
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
