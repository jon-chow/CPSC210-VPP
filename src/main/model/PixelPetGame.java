package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.goodsandservices.Shop;
import model.pets.*;
import ui.menus.*;

public class PixelPetGame {
    public static final ArrayList<String> ANIMALS_IN_ADOPTION_CLINIC =
            new ArrayList<>(Arrays.asList("Example Animal", "Dog"));

    public static final int TICKS_PER_SECOND = 1;

    public static final int MAX_HAPPINESS = 100;
    public static final int MAX_HUNGER = 100;
    public static final int MAX_THIRST = 100;
    public static final int MAX_HEALTH = 100;

    private static final int HAPPINESS_LOSS_PER_SECOND = 1;
    private static final int HUNGER_LOSS_PER_SECOND = 1;
    private static final int THIRST_LOSS_PER_SECOND = 1;
    private static final int HEALTH_LOSS_PER_SECOND = 1;

    private static final int STARTING_MONEY = 10000;
    private static final int MONEY_GAINED_PER_SECOND = 100;

    private static final int SECONDS_PER_AGING = 10;

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

        player = NewPlayerMenu.initNewPlayer(STARTING_MONEY);
        pet = AdoptionMenu.initAdoption();
        ViewCommandsMenu.showControls();
    }

    // EFFECTS: progresses the game
    public void tick() {
        ticksPassed++;
        if (ticksPassed % TICKS_PER_SECOND == 0) {
            secondsPassed++;

            if ((secondsPassed & SECONDS_PER_AGING) == 0) {
                pet.setAge(pet.getAge() + 1);
            }

            pet.decrementCareLevels(HAPPINESS_LOSS_PER_SECOND,
                    HUNGER_LOSS_PER_SECOND,
                    THIRST_LOSS_PER_SECOND,
                    HEALTH_LOSS_PER_SECOND);

            player.setMoney(player.getMoney() + MONEY_GAINED_PER_SECOND);
        }

        if (pet.checkIsDead()) {
            pet.setState(State.DEAD);
            endGame = true;
        }
    }

    // EFFECTS: returns true if game has ended
    public boolean isEnded() {
        return endGame;
    }

    // REQUIRES: Shop with given shopName exists
    // EFFECTS: returns the Shop with the given shopName
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
