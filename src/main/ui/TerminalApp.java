package ui;

import java.io.IOException;
import java.util.Scanner;

import model.*;
import ui.menus.InventoryMenu;
import ui.menus.PetStatsMenu;
import ui.menus.ShopMenu;
import ui.menus.ViewCommandsMenu;

import static ui.configurables.Commands.*;

//import com.googlecode.lanterna.TerminalPosition;
//import com.googlecode.lanterna.TerminalSize;
//import com.googlecode.lanterna.TextColor;
//import com.googlecode.lanterna.graphics.TextGraphics;
//import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
//import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
//import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
//import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
//import com.googlecode.lanterna.input.KeyType;
//import com.googlecode.lanterna.input.KeyStroke;
//import com.googlecode.lanterna.screen.Screen;
//import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class TerminalApp {
    public static final Scanner scanner = new Scanner(System.in);

    private PixelPetGame game;

    // EFFECTS: creates a new instance of PixelPetGame
    public void start() throws IOException, InterruptedException {
        game = new PixelPetGame();
        beginTicks();
    }

    // EFFECTS: begins the ticking process; game is running
    private void beginTicks() throws InterruptedException {
        while (!game.isEnded()) {
            tick();
            Thread.sleep(1000L / PixelPetGame.TICKS_PER_SECOND);
        }

        PetStatsMenu.showPetDiedMenu(game.getPet());
        System.exit(0);
    }

    // EFFECTS: adds one tick to the game; proceeds the game
    private void tick() {
        game.tick();
        handleUserInput();
    }

    // EFFECTS: handles user commands and inputs
    private void handleUserInput() {
        String command = scanner.nextLine();

        if (command != null && command != "") {
            switch (command) {
                case COMMANDS_KEY: ViewCommandsMenu.showControls();
                    break;
                case CHECK_PET_KEY: PetStatsMenu.checkPetStats(game.getPet(), game.getPlayer());
                    break;
                case OPEN_SHOP_KEY:
                    ShopMenu.openShopMenu(game.getShopByName("Kira Kira Pets"), game.getPlayer());
                    break;
                case OPEN_INVENTORY_KEY: InventoryMenu.viewInventory(game.getPlayer(), game.getPet());
                    break;
                case VIEW_MONEY_KEY: InventoryMenu.checkMoney(game.getPlayer());
                    break;
                default:
                    break;
            }
        }
    }

//    // EFFECTS: renders the ui graphics for the game
//    private void render() {
//        if (game.isEnded()) {
//            return;
//        }
//    }
}
