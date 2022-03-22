package ui.app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import model.Player;
import model.configurables.FileLocations;
import model.exceptions.CannotFindSessionIdException;
import model.pets.Pet;

import ui.menus.*;
import ui.menus.ingame.*;
import ui.menus.mainmenu.MainMenu;
import ui.menus.settings.PersistenceMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import static java.lang.System.exit;
import static model.configurables.FileLocations.backgroundsDir;

import static ui.configurables.Commands.*;
import static ui.configurables.InterfaceAesthetics.*;

// class for handling game time and user input
public class GuiApp extends JFrame implements ActionListener {
    public static final Scanner scanner = new Scanner(System.in);

    private static final String gameTitle = "Pixel Pet";
//    private final File audioFile = new File("data/");
//    AudioInputStream bgm;

    private Timer timer;
    private PixelPetGame game;
    private Player player;
    private Pet pet;

    private JPanel window;
    private JLayeredPane content;
    private JLayeredPane menu;
    private JLabel background;

    private GameMenu gameMenu;

    private int width;
    private int height;

    // EFFECTS: constructs a window app
    public GuiApp(int width, int height) throws IOException, InterruptedException,
            FontFormatException, CannotFindSessionIdException {
        super(gameTitle);
        ImageIcon gameIcon = new ImageIcon(FileLocations.gameIconDir);
        super.setIconImage(gameIcon.getImage());

        this.width = width;
        this.height = height;
        this.game = new PixelPetGame(true, this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setResizable(false);
        setLayout(null);
        setVisible(true);

        setUpRequiredInterfaces();
        startTimer();

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
        background.setBounds(0,-20, width, height);
        content.add(background, new Integer(0));
        changeBackground("PixelPetBackground.png");

        menu = new JLayeredPane();
        menu.setName("Menu");
        menu.setOpaque(false);
        menu.setBackground(new Color(0,0,0,0));
        menu.setBounds(0, 0, width, height);
        content.add(menu, new Integer(1));
    }

    // EFFECTS: clears out the menu
    public void clearMenu() {
        menu.removeAll();
        menu.revalidate();
        menu.repaint();
    }

    // MODIFIES: this
    // EFFECTS: changes the game background image
    public void changeBackground(String fileName) throws IOException {
        BufferedImage bufferedImg = ImageIO.read(new File(backgroundsDir + fileName));
        Image img = new ImageIcon(bufferedImg).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
    }

    // TODO:
    // MODIFIES: this
    // EFFECTS: creates a new instance of PixelPetGame
    public void start(boolean isForTest, boolean isNewSession) throws IOException,
            CannotFindSessionIdException, FontFormatException, InterruptedException {
        if (isNewSession) {
            game = new PixelPetGame(isForTest, this);
        }

        super.setTitle(gameTitle + " - Session ID: " + game.getSessionId());
        gameMenu = new GameMenu(this, menu);
//        CommandsMenu.showControls();
    }

    // EFFECTS: begins the ticking process; game is running
    private void startTimer() {
        timer = new Timer(1000 / game.TICKS_PER_SECOND, ae -> {
            if (!(gameMenu == null)) {
                tick();
                if (game.isEnded() && game.getPet().checkIsDead()) {
                    PetStatsMenu.showPetDiedMenu(game.getPet());
                    try {
                        Thread.sleep(2500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    exit(0);
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    // EFFECTS: adds one tick to the game; proceeds the game
    private void tick() {
        game.tick();
        render();
//        handleUserInput();
    }

    // MODIFIES: gameMenu
    // EFFECTS: renders the ui graphics for the gameMenu
    private void render() {
        if (game.isEnded()) {
            return;
        }

        gameMenu.getPetEnvironment().renderGraphics();
        getContentPane().repaint();
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

    // EFFECTS: triggers when an event from a JComponent is called
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        performAction(command);
    }

    // EFFECTS: helper for actionPerformed; performs the desired action
    private void performAction(String command) {
        if (command.equals("")) {
            return;
        }
    }

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
