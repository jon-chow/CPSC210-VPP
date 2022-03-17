package ui.app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import model.Player;
import model.configurables.FileLocations;
import model.pets.Pet;
import ui.menus.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import static java.lang.System.exit;
import static model.configurables.FileLocations.backgroundsDir;
import static ui.app.PixelPetGame.STARTING_MONEY;

import static ui.configurables.Commands.*;
import static ui.configurables.InterfaceAesthetics.*;

// class for handling game time and user input
public class GuiApp extends JFrame implements ActionListener {
    public static final Scanner scanner = new Scanner(System.in);
//    private final File audioFile = new File("data/");
//    AudioInputStream bgm;

    private PixelPetGame game;
    private Player player;
    private Pet pet;

    private JPanel window;
    private JLayeredPane content;
    private JLayeredPane menu;
    private JLabel background;

    private int width;
    private int height;

    // EFFECTS: constructs a window app
    public GuiApp(int width, int height) throws IOException, InterruptedException, FontFormatException {
        super("Pixel Pet");
        ImageIcon gameIcon = new ImageIcon(FileLocations.gameIconDir);
        super.setIconImage(gameIcon.getImage());

        this.width = width;
        this.height = height;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setResizable(false);
        setLayout(null);

        setUpRequiredInterfaces();

        new MainMenu(this, menu);
        setLocationRelativeTo(null);
    }

    // EFFECTS: creates/instantiates the window, content, and menu panels
    private void setUpRequiredInterfaces() throws IOException {
        window = ((JPanel) getContentPane());
        window.setName("PixelPetWindow");
        window.setBackground(WINDOW_BACKGROUND_COLOR);
        window.setBorder(new LineBorder(WINDOW_BORDER_COLOR, 4));

        content = getLayeredPane();
        content.setName("Content");

        background = new JLabel();
        background.setName("Background");
        background.setBounds(-5,-20, width, height);
        content.add(background, new Integer(0));
        changeBackground("PixelPetRoom.png");

        menu = new JLayeredPane();
        menu.setName("Menu");
        menu.setOpaque(false);
        menu.setBackground(new Color(0,0,0,0));
        content.add(menu, new Integer(1));
    }

    // EFFECTS: clears out the menu
    public void clearMenu() {
        menu.removeAll();
        menu.revalidate();
        menu.repaint();
    }

    // MODIFIES: this
    // EFFECTS: changes the background image (the room)
    public void changeBackground(String fileName) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File(backgroundsDir + fileName));
        Image img = new ImageIcon(myPicture).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
    }

    // EFFECTS: creates a new instance of PixelPetGame
    public void start() throws IOException, InterruptedException, FontFormatException {
        game = new PixelPetGame(false, this);
        beginTicks();

        PetStatsMenu.showPetDiedMenu(game.getPet());
        exit(0);
    }

    // EFFECTS: begins the ticking process; game is running
    private void beginTicks() throws InterruptedException {
        while (!game.isEnded()) {
            tick();
            Thread.sleep(1000L / PixelPetGame.TICKS_PER_SECOND);
        }
    }

    // EFFECTS: adds one tick to the game; proceeds the game
    private void tick() {
        game.tick();
        handleUserInput();
    }

    // EFFECTS: handles user commands and inputs
    private void handleUserInput() {
        String command = scanner.nextLine();

        switch (command) {
            case COMMANDS_KEY: CommandsMenu.showControls();
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
            case SAVE_KEY: PersistenceMenu.displaySaveMenu(game);
                break;
            case LOAD_KEY: PersistenceMenu.displayLoadMenu(game);
                break;
            case QUIT_GAME_KEY: exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "": break;
            default: break;
        }
    }

//    // EFFECTS: renders the ui graphics for the game
//    private void render() {
//        if (game.isEnded()) {
//            return;
//        }
//    }

    // GETTERS:
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public PixelPetGame getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Pet getPet() {
        return pet;
    }

    // SETTERS:
    public void setGame(PixelPetGame game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
