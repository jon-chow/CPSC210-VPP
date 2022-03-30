package ui.app;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.Player;
import model.configurables.FileLocations;
import model.exceptions.CannotFindSessionIdException;
import model.logger.Event;
import model.logger.EventLog;
import model.pets.Pet;

import ui.menus.ingame.*;
import ui.menus.mainmenu.MainMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import static java.lang.System.exit;
import static model.configurables.FileLocations.backgroundsDir;

import static ui.configurables.InterfaceAesthetics.*;

// class for handling game time and user input
public class GuiApp extends JFrame {
    private EventLog eventLog = EventLog.getInstance();

    private static final String gameTitle = "Pixel Pet";
//    private final File audioFile = new File("data/");
//    AudioInputStream bgm;

    private Timer timer;
    private PixelPetGame game;
    private Player player;
    private Pet pet;

    private int secondsPassed;

    private JPanel window;
    private JLayeredPane content;
    private JLayeredPane menu;
    private JLabel background;

    private GameMenu gameMenu;

    private final int width;
    private final int height;

    private final WindowListener windowListener = new WindowListener() {

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            logAllEvents();
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    };

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
        addWindowListener(windowListener);

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

    // EFFECTS: clears out any ongoing session and returns to the main menu
    public void returnToMainMenu() throws IOException, FontFormatException {
        gameMenu = null;
        super.setTitle(gameTitle);
        new MainMenu(this, menu);
    }

    // MODIFIES: this
    // EFFECTS: changes the game background image
    public void changeBackground(String fileName) throws IOException {
        BufferedImage bufferedImg = ImageIO.read(new File(backgroundsDir + fileName));
        Image img = new ImageIcon(bufferedImg).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
    }

    // MODIFIES: this
    // EFFECTS: creates a new instance of PixelPetGame
    public void start(boolean isForTest, boolean isNewSession) throws IOException,
            CannotFindSessionIdException, FontFormatException {
        if (isNewSession) {
            game = new PixelPetGame(isForTest, this);
        }

        super.setTitle(gameTitle + " - Session ID: " + game.getSessionId());
        gameMenu = new GameMenu(this, menu);
    }

    // EFFECTS: begins the ticking process; game is running
    private void startTimer() {
        secondsPassed = 0;
        timer = new Timer(1000 / PixelPetGame.TICKS_PER_SECOND, ae -> {
            if (!(game == null || gameMenu == null)) {
                tick();

                if (secondsPassed < game.getSecondsPassed()) {
                    //
                }
                secondsPassed = game.getSecondsPassed();

                if (game.isEnded() && game.getPet().checkIsDead()) {
                    System.out.println("Oh no! " + pet.getName() + " has died!");
                    System.out.println("How unfortunate...");
                    try {
                        Thread.sleep(2500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logAllEvents();
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
    }

    // MODIFIES: gameMenu, petEnvironment
    // EFFECTS: renders the in-game graphics
    private void render() {
        if (game.isEnded()) {
            return;
        }

        gameMenu.renderGraphics(game);
        getContentPane().repaint();
    }

    // TODO
    // EFFECTS: prints out all the events that have occurred since the beginning
    //          of the game to the console
    private void logAllEvents() {
        for (Event event : eventLog) {
            System.out.println(event.toString() + "\n");
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
