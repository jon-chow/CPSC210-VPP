package ui;

import model.*;

import java.io.IOException;
import java.util.Scanner;

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
//    private Screen screen;

    public void start() throws IOException, InterruptedException {
//        screen = new DefaultTerminalFactory().createScreen();
//        screen.startScreen();
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
        game.tick();
    }

//    private void handleUserInput() throws IOException {
//        KeyStroke stroke = screen.pollInput();
//
//        if (stroke == null) {
//            return;
//        }
//
//        if (stroke.getCharacter() != null) {
//            return;
//        }
//    }
//
//    private void render() {
//        if (game.isEnded()) {
//            return;
//        }
//    }
}
