package model;

import java.io.IOException;

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
    private Shop shop;

    // EFFECTS: constructs a new PixelPetGame
    public PixelPetGame() throws IOException {
        shop = ShopMenu.initShop("Kira Kira Pets!");

        player = NewPlayerMenu.initNewPlayer();
        pet = AdoptionMenu.initAdoption();
        TutorialMenu.showControls();
    }

    // EFFECTS: progresses the game
    public void tick() {
        ticksPassed++;
        if (ticksPassed % TICKS_PER_SECOND == 0) {
            secondsPassed++;

            System.out.println(secondsPassed);

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

    // GETTERS
    public Shop getShop() {
        return shop;
    }

    public Player getPlayer() {
        return player;
    }

    public Pet getPet() {
        return pet;
    }
}
