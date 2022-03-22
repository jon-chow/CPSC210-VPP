package ui.menus.ingame;

import ui.app.GuiApp;
//import ui.app.PixelPetGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static model.configurables.FileLocations.*;
import static ui.configurables.InterfaceAesthetics.*;
import static ui.configurables.JComponentBuilder.*;

// represents the habitable environment, containing the scene and the pet
public class PetEnvironment extends JPanel {
    private GuiApp ui;
    private int width;
    private int height;

    private JLabel scene;

    private JPanel pet;
    private JLabel petSprite;

//    private Polygon boundary;

    // EFFECTS: constructs the pet environment
    public PetEnvironment(GuiApp ui, int width, int height) throws IOException, FontFormatException {
        this.ui = ui;
        this.width = width;
        this.height = height;
//        makeBoundary();

        super.setBackground(TRANSPARENT);
        super.setPreferredSize(new Dimension(width, height));
        super.setMaximumSize(new Dimension(width, height));
        super.setLayout(new GridBagLayout());

        scene = new JLabel();
        scene.setMaximumSize(new Dimension(width, height));
        scene.setLayout(null);
        super.add(scene);

        pet = createJPanel(TRANSPARENT, 100, 100);
        pet.setPreferredSize(new Dimension(100, 100));
        scene.add(pet);

        translatePet(0,55);
        changeScenery("PixelPetRoom.png");
        drawPetSprite();
    }

    // MODIFIES: this
    // EFFECTS: changes the scene image (the room)
    public void changeScenery(String fileName) throws IOException {
        BufferedImage bufferedImg = ImageIO.read(new File(sceneryDir + fileName));
        Image img = new ImageIcon(bufferedImg).getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        scene.setIcon(new ImageIcon(img));
    }

    // MODIFIES: this
    // EFFECTS: translates the pet's location starting at the center of the room
    private void translatePet(int x, int y) {
        Insets insets = scene.getInsets();
        pet.setBounds((width - pet.getPreferredSize().width) / 2 + insets.left + x,
                (height - pet.getPreferredSize().height) / 2 + insets.top + y,
                pet.getPreferredSize().width, pet.getPreferredSize().height);
    }

    // EFFECTS: draws the pet sprite
    private void drawPetSprite() throws IOException {
        BufferedImage bufferedImage;
        Image petImg;

        bufferedImage = ImageIO.read(new File(backupSpriteDir));
        petImg = new ImageIcon(bufferedImage).getImage()
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        try {
            bufferedImage = ImageIO.read(new File(ui.getGame().getPet().getSpritesDir() + "Idle.png"));
            petImg = new ImageIcon(bufferedImage).getImage()
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("Unable to load default pet sprite");
        }

        petSprite = new JLabel(new ImageIcon(petImg));
        pet.add(petSprite);
    }

//    // EFFECTS: updates the drawing of the pet sprite
//    private void updatePetSprite(String spriteFile) {
//        try {
//            BufferedImage bufferedImage =
//                    ImageIO.read(new File(ui.getGame().getPet().getSpritesDir() + spriteFile));
//            Image petImg = new ImageIcon(bufferedImage).getImage()
//                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//            petSprite = new JLabel(new ImageIcon(petImg));
//        } catch (IOException e) {
//            System.err.println("Unable to load pet sprite: " + spriteFile);
//        }
//    }

//    // TODO:
//    // MODIFIES: this
//    // EFFECTS: updates the environment's graphics
//    public void renderGraphics(PixelPetGame game) {
//        updatePetSprite("");
//    }

//    // EFFECTS: creates the environment boundary
//    public void makeBoundary() {
//        boundary = new Polygon();
//        boundary.addPoint(-100, -100);
//        boundary.addPoint(-100, 100);
//        boundary.addPoint(100, 100);
//        boundary.addPoint(100, -100);
//    }

//    @Override
//    public void paintComponent(Graphics graphics) {
//        super.paintComponent(graphics);
//        graphics.setColor(Color.WHITE);
//        graphics.drawPolygon(boundary);
//    }
}
