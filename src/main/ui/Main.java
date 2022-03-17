package ui;

import ui.app.GuiApp;

// main runs the app
public class Main {
    public static void main(String[] args) {
        try {
            new GuiApp(800, 800);
        } catch (Exception e) {
            System.err.println("Failed to start game! " + e);
        }
    }
}
