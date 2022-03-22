package ui.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.Player;
import model.exceptions.CannotFindSessionIdException;
import model.goodsandservices.Shop;
import model.pets.*;

// class for handling the main game functionalities and menus
public class PixelPetGame {
    public static final ArrayList<String> ANIMALS_IN_ADOPTION_CLINIC =
            new ArrayList<>(Arrays.asList("ExampleAnimal", "Dog"));

    public static final int TICKS_PER_SECOND = 100;

    public static final int MAX_HAPPINESS = 100;
    public static final int MAX_HUNGER = 100;
    public static final int MAX_THIRST = 100;
    public static final int MAX_HEALTH = 100;

    private static final int HAPPINESS_LOSS = 1;
    private static final int HUNGER_LOSS = 1;
    private static final int THIRST_LOSS = 1;
    private static final int HEALTH_LOSS = 1;

    public static final int STARTING_MONEY = 1000;
    private static final int MONEY_GAINED_PER_SECOND = 20;

    private static final int SECONDS_PER_AGING = 60;
    private static final int SECONDS_PER_CARE_LEVELS_DECREMENT = 5;

    private int sessionId;
    private int ticksPassed = 0;
    private int secondsPassed = 0;

    private boolean endGame = false;
    private Player player;
    private Pet pet;
    private ArrayList<Shop> shops;

    // EFFECTS: constructs a new PixelPetGame and pre-configures
    //          the PixelPetGame if isForTest is true
    public PixelPetGame(boolean isForTest, GuiApp ui) throws IOException, CannotFindSessionIdException {
        Shop shop = new Shop("Kira Kira Pets");
        shop.stockWithRandomItems(2, 15);
        shop.stockWithRandomItems(3, 10);

        shops = new ArrayList<>();
        shops.add(shop);
        sessionId = generateSessionId();

        if (!isForTest) {
            player = ui.getPlayer();
            pet = ui.getPet();
            ui.setGame(this);
        } else {
            player = new Player();
            pet = new ExampleAnimal("Animal", "Aleph");
            player.setPlayerName("Player");
            player.setMoney(9999999);
        }
    }

    // MODIFIES: this
    // EFFECTS: generates a new session id for the current session and returns it
    private int generateSessionId() {
        return Math.abs((int) (System.currentTimeMillis() / 1000L));
    }

    // EFFECTS: progresses the game
    public void tick() {
        ticksPassed++;
        if (ticksPassed % TICKS_PER_SECOND == 0) {
            secondsPassed++;

            if ((secondsPassed % SECONDS_PER_AGING) == 0) {
                pet.setAge(pet.getAge() + 1);
            }

            if ((secondsPassed % SECONDS_PER_CARE_LEVELS_DECREMENT) == 0) {
                pet.decrementCareLevels(HAPPINESS_LOSS,
                        HUNGER_LOSS,
                        THIRST_LOSS,
                        HEALTH_LOSS);
            }

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
