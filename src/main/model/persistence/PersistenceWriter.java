package model.persistence;

import java.io.File;
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

    private FileWriter fileWriter;
    private JSONObject playerObject;
    private JSONObject petObject;
    private JSONObject shopObject;

    // EFFECTS: constructs a new persistence
    //          that saves game data to persistenceFile
    public PersistenceWriter(File persistenceFile, PixelPetGame game) throws IOException {
        fileWriter = new FileWriter(persistenceFile);
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
        slotObject.put("saveTime", LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        JSONArray saveSlotsArray = new JSONArray();
        saveSlotsArray.put(slotObject);
        JSONObject savesData = new JSONObject();
        savesData.put("sessions", saveSlotsArray);

        fileWriter.write(savesData.toString(INDENT_FCT));
        fileWriter.flush();
        fileWriter.close();
    }
}