package model.persistence;

import model.configurables.FileLocations;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import ui.app.PixelPetGame;

// writes data from the game to Persistence.json
public class PersistenceWriter {
    private static final int INDENT_FCT = 4;

    private final FileLocations fileLoc = new FileLocations();
    private final FileWriter fileWriter = new FileWriter(fileLoc.persistenceDir);

    private final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private JSONObject playerObject;
    private JSONObject petObject;
    private JSONObject shopObject;

    // EFFECTS: constructs a new persistence writer
    public PersistenceWriter(PixelPetGame game) throws IOException {
        save(game);
    }

    // EFFECTS: saves the game's current session
    private void save(PixelPetGame game) throws IOException {
        playerObject = game.getPlayer().toJsonObj();
        petObject = game.getPet().toJsonObj();
        shopObject = game.getShopByName("Kira Kira Pets").toJsonObj();

        JSONObject dataObject = new JSONObject();
        dataObject.put("player", playerObject);
        dataObject.put("pet", petObject);
        dataObject.put("shop", shopObject);
        dataObject.put("ticksPassed", game.getTicksPassed());
        dataObject.put("secondsPassed", game.getSecondsPassed());

        JSONObject slotObject = new JSONObject();
        slotObject.put("data", dataObject);
        slotObject.put("id", game.getSessionId());
        slotObject.put("saveTime", LocalDateTime.now().format(dateTimeFormat));

        JSONArray saveSlotsArray = new JSONArray();
        saveSlotsArray.put(slotObject);

        JSONObject savesData = new JSONObject();
        savesData.put("sessions", saveSlotsArray);

        writeToJson(savesData);
    }

    // EFFECTS: writes the data to the data persistence json file
    private void writeToJson(JSONObject gameData) throws IOException {
        fileWriter.write(gameData.toString(INDENT_FCT));
        fileWriter.flush();
        fileWriter.close();
    }
}
