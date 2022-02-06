package model;

import java.io.IOException;
import java.util.ArrayList;

import model.goodsandservices.Shop;
import model.pets.*;
import ui.menus.*;

public class PixelPetGame {
    public static final int TICKS_PER_SECOND = 1;

    private static final int HAPPINESS_LOSS_PER_SECOND = 10;
    private static final int HUNGER_LOSS_PER_SECOND = 10;
    private static final int THIRST_LOSS_PER_SECOND = 10;
    private static final int HEALTH_LOSS_PER_SECOND = 10;
    private static final int MONEY_GAINED_PER_SECOND = 100;

    private int ticksPassed = 0;
    private int secondsPassed = 0;

    private boolean endGame = false;
    private Player player;
    private Pet pet;

    private ArrayList<Shop> shops;

    // EFFECTS: constructs a new PixelPetGame
    public PixelPetGame() throws IOException {
        Shop shop = ShopMenu.initShop("Kira Kira Pets");
        shops = new ArrayList<>();
        shops.add(shop);

        player = NewPlayerMenu.initNewPlayer();
        pet = AdoptionMenu.initAdoption();
        ViewCommandsMenu.showControls();
    }

    // EFFECTS: progresses the game
    public void tick() {
        ticksPassed++;
        if (ticksPassed % TICKS_PER_SECOND == 0) {
            secondsPassed++;

            pet.decrementCareLevels(HAPPINESS_LOSS_PER_SECOND,
                    HUNGER_LOSS_PER_SECOND,
                    THIRST_LOSS_PER_SECOND,
                    HEALTH_LOSS_PER_SECOND);

            player.setMoney(player.getMoney() + MONEY_GAINED_PER_SECOND);
        }

        endGame = pet.checkIsDead();
    }

    // EFFECTS: returns true if game has ended
    public boolean isEnded() {
        return endGame;
    }

    // EFFECTS: returns the Shop of the given shopName
    public Shop getShopByName(String shopName) {
        Shop theShop = null;

        for (Shop shop : shops) {
            if (shop.getShopName() == shopName) {
                theShop = shop;
                break;
            }
        }

        return theShop;
    }

    // GETTERS
    public Player getPlayer() {
        return player;
    }

    public Pet getPet() {
        return pet;
    }
}
