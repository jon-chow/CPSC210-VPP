package ui;

import model.*;

import com.googlecode.lanterna.TerminalPosition;
//import com.googlecode.lanterna.TerminalSize;
//import com.googlecode.lanterna.TextColor;
//import com.googlecode.lanterna.graphics.TextGraphics;
//import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
//import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
//import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
//import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
//import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class TerminalApp {
    private PixelPetGame game;
    private Screen screen;

    public void start() throws IOException {
//        screen = new DefaultTerminalFactory().createScreen();
//        screen.startScreen();
        game = new PixelPetGame();
//        beginTicks();
    }

//    private void beginTicks() throws InterruptedException, IOException {
//        while (!game.isEnded()) {
//            tick();
//            Thread.sleep(1000L / PixelPetGame.TICKS_PER_SECOND);
//        }
//
//        System.exit(0);
//    }
//
//    private void tick() throws IOException {
//        handleUserInput();
//
//        game.tick();
//
//        screen.setCursorPosition(new TerminalPosition(0, 0));
//        screen.clear();
//        render();
//        screen.refresh();
//
//        screen.setCursorPosition(new TerminalPosition(screen.getTerminalSize().getColumns() - 1, 0));
//    }
//
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
