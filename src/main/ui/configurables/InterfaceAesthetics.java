package ui.configurables;

import java.awt.*;
import java.io.File;

import static model.configurables.FileLocations.*;

// class for storing command keywords
public class InterfaceAesthetics {
    public static final File fontFile = new File(assetsDir + "pixel-font.ttf");

    public static final Color TRANSPARENT = new Color(0,0,0,0);
    public static final Color WINDOW_BACKGROUND_COLOR = new Color(70, 60, 80);
    public static final Color WINDOW_BORDER_COLOR = new Color(240, 170, 190);

    public static final Color GAME_BAR_UI_COLOR = new Color(0,0,0, 0.2f);

    public static final Color BUTTON_COLOR = new Color(255,225,245);
    public static final Color BUTTON_COLOR_2 = new Color(120, 255, 220);
    public static final Color BUTTON_BORDER_COLOR = new Color(240, 170, 190);

    public static final Color BUTTON_TEXT_COLOR = new Color(190,140,255);
    public static final Color FIELD_TEXT_COLOR = new Color(120,240,255);

    public static final Color HAPPINESS_BAR_COLOR = new Color(220, 200, 80);
    public static final Color HUNGER_BAR_COLOR = new Color(240, 140, 80);
    public static final Color THIRST_BAR_COLOR = new Color(70, 200, 240);
    public static final Color HEALTH_BAR_COLOR = new Color(50, 240, 70);
}
