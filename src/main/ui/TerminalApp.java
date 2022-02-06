package ui;

import java.io.IOException;
import java.util.Scanner;

import model.*;
import static ui.menus.Commands.*;

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

    public void start() throws IOException, InterruptedException {

        game = new PixelPetGame();
        beginTicks();
    }

    private void beginTicks() throws InterruptedException {
        while (!game.isEnded()) {
            tick();
            Thread.sleep(1000L / PixelPetGame.TICKS_PER_SECOND);
        }

        System.exit(0);
    }

    private void tick() {
        handleUserInput();
        game.tick();
    }

    private void handleUserInput() {
        String command = scanner.nextLine();

        if (command != null) {
            switch (command) {
                case CHECK_PET_KEY:
                    System.out.println("Checking current status of pet");
                    break;
                case OPEN_SHOP_KEY:
                    System.out.println("Opening shop menu");
                    break;
                case OPEN_INVENTORY_KEY:
                    System.out.println("Opening inventory");
                    break;
                default:
                    break;
            }
        }
    }

//    private void render() {
//        if (game.isEnded()) {
//            return;
//        }
//    }
}
