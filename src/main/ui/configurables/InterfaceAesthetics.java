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

    public static final Color BUTTON_COLOR = new Color(255,225,245);
    public static final Color BUTTON_COLOR_2 = new Color(120, 255, 220);
    public static final Color BUTTON_BORDER_COLOR = new Color(240, 170, 190);

    public static final Color BUTTON_TEXT_COLOR = new Color(190,140,255);
    public static final Color FIELD_TEXT_COLOR = new Color(120,240,255);
}
