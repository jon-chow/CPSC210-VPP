package ui.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.Player;
import model.goodsandservices.Shop;
import model.pets.*;
import ui.menus.*;

// class for handling the main game functionalities and menus
public class PixelPetGame {
    public static final ArrayList<String> ANIMALS_IN_ADOPTION_CLINIC =
            new ArrayList<>(Arrays.asList("ExampleAnimal", "Dog"));

    public static final int TICKS_PER_SECOND = 10;

    public static final int MAX_HAPPINESS = 100;
    public static final int MAX_HUNGER = 100;
    public static final int MAX_THIRST = 100;
    public static final int MAX_HEALTH = 100;

    private static final int HAPPINESS_LOSS_PER_SECOND = 5;
    private static final int HUNGER_LOSS_PER_SECOND = 5;
    private static final int THIRST_LOSS_PER_SECOND = 5;
    private static final int HEALTH_LOSS_PER_SECOND = 5;

    public static final int STARTING_MONEY = 10000;
    private static final int MONEY_GAINED_PER_SECOND = 100;

    private static final int SECONDS_PER_AGING = 10;

    private int sessionId;
    private int ticksPassed = 0;
    private int secondsPassed = 0;

    private boolean endGame = false;
    private Player player;
    private Pet pet;
    private ArrayList<Shop> shops;

    // EFFECTS: constructs a new PixelPetGame and pre-configures
    //          the PixelPetGame if isForTest is true
    public PixelPetGame(boolean isForTest, GuiApp ui) throws IOException {
        Shop shop = ShopMenu.initShop("Kira Kira Pets");
        shops = new ArrayList<>();
        shops.add(shop);

        if (!isForTest) {
            player = ui.getPlayer();
            pet = ui.getPet();
            sessionId = generateSessionId(player.getPlayerName(), pet.getName(), shop.getShopName());
            System.out.println("Your personal ID for saving and loading the game is " + sessionId + ".\n"
                    + "Be sure to write it down somewhere or remember it!");
            CommandsMenu.showControls();
        } else {
            pet = new ExampleAnimal("Test Animal", "Aleph");
            player = new Player();
            player.setPlayerName("Test Player");
            player.setMoney(9999999);
        }
    }

    // MODIFIES: this
    // EFFECTS: generates a new session id for the current session and returns it
    private int generateSessionId(String playerName, String petName, String shopName) {
        String rawKey = (playerName + petName + shopName).replaceAll("\\s","");
        int encodedKey = 0;

        for (int i = 0; i < rawKey.length(); i++) {
            encodedKey += rawKey.charAt(i);
        }

        encodedKey = (int) (encodedKey * System.currentTimeMillis() / 1000L);

        return Math.abs(encodedKey);
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
            if (shop.getShopName().equals(shopName)) {
                theShop = shop;
                break;
            }
        }

        return theShop;
    }

    // GETTERS
    public int getSessionId() {
        return sessionId;
    }

    public Player getPlayer() {
        return player;
    }

    public Pet getPet() {
        return pet;
    }

    public int getTicksPassed() {
        return ticksPassed;
    }

    public int getSecondsPassed() {
        return secondsPassed;
    }

    // SETTERS
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }

    public void setTicksPassed(int ticksPassed) {
        this.ticksPassed = ticksPassed;
    }

    public void setSecondsPassed(int secondsPassed) {
        this.secondsPassed = secondsPassed;
    }
}
