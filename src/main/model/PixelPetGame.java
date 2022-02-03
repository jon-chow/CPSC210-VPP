package model;

import java.io.IOException;

import model.pets.*;
import ui.menus.*;

public class PixelPetGame {
    public static final int TICKS_PER_SECOND = 10;

    private static final int TICKS_PER_TIME_FRAME = TICKS_PER_SECOND * 2;
    private static final int HAPPINESS_LOSS_PER_TIME_FRAME = 1;
    private static final int HUNGER_LOSS_PER_TIME_FRAME = 1;
    private static final int THIRST_LOSS_PER_TIME_FRAME = 1;
    private static final int HEALTH_LOSS_PER_TIME_FRAME = 1;

    private int tickCount = 0;
    private boolean endGame = false;
    private Player player;
    private Pet pet;

    private Shop toyShop = new Shop("Toys 4 Pets");

    // EFFECTS: constructs a new PixelPetGame
    public PixelPetGame() throws IOException {
        player = NewPlayerMenu.initNewPlayer();
        pet = AdoptionMenu.initAdoption();
    }

    // EFFECTS: progresses the game
    public void tick() {
        tickCount++;
        if (tickCount % TICKS_PER_TIME_FRAME == 0) {
            pet.decrementCareLevels(HAPPINESS_LOSS_PER_TIME_FRAME,
                                    HUNGER_LOSS_PER_TIME_FRAME,
                                    THIRST_LOSS_PER_TIME_FRAME,
                                    HEALTH_LOSS_PER_TIME_FRAME);
        }

        if (pet.checkIsDead()) {
            endGame = true;
            return;
        }
    }

    // EFFECTS: returns true if game has ended
    public boolean isEnded() {
        return endGame;
    }

    // SETTERS
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    // GETTERS
    public Shop getToyShop() {
        return toyShop;
    }

    public Player getPlayer() {
        return player;
    }

    public Pet getPet() {
        return pet;
    }
}
