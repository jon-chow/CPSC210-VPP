package model.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.logger.Event;
import model.logger.EventLog;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import ui.app.PixelPetGame;

// writes data from the game to Persistence.json
public class PersistenceWriter {
    private static final int INDENT_FCT = 4;
    private final EventLog eventLog = EventLog.getInstance();

    private final String content;
    private final JSONObject oldSavesData;
    private final JSONArray oldSessionsArray;

    private final FileWriter fileWriter;
    private final JSONObject playerObject;
    private final JSONObject petObject;
    private final JSONObject shopObject;

    private final PixelPetGame game;

    // EFFECTS: constructs a new persistence
    //          that saves game data to persistenceFile
    public PersistenceWriter(File persistenceFile, PixelPetGame game) throws IOException {
        this.game = game;

        content = FileUtils.readFileToString(persistenceFile, "utf-8");
        oldSavesData = new JSONObject(content);
        oldSessionsArray = oldSavesData.getJSONArray("sessions");

        fileWriter = new FileWriter(persistenceFile);
        playerObject = game.getPlayer().toJsonObj();
        petObject = game.getPet().toJsonObj();
        shopObject = game.getShopByName("Kira Kira Pets").toJsonObj();

        createObjects();

        JSONObject savesData = new JSONObject();
        savesData.put("sessions", oldSessionsArray);

        fileWriter.write(savesData.toString(INDENT_FCT));
        fileWriter.flush();
        fileWriter.close();

        eventLog.logEvent(new Event("Saved game session ID " + game.getSessionId() + "."));
    }


    // EFFECTS: helper for PersistenceWriter;
    //          creates the JSONObjects to be written
    private void createObjects() {
        JSONObject dataObject = new JSONObject();
        dataObject.put("player", playerObject);
        dataObject.put("pet", petObject);
        dataObject.put("shop", shopObject);
        dataObject.put("ticksPassed", game.getTicksPassed());
        dataObject.put("secondsPassed", game.getSecondsPassed());

        createSessionObject(dataObject);
    }

    // EFFECTS: helper for createObjects;
    //          creates the session JSONObject
    private void createSessionObject(JSONObject dataObject) {
        boolean isExisting = false;

        for (int i = 0; i < oldSessionsArray.length(); i++) {
            JSONObject session = oldSessionsArray.getJSONObject(i);

            if (game.getSessionId() == session.getInt("id")) {
                isExisting = true;
                session.put("data", dataObject);
                session.put("saveTime", LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            }
        }

        if (!isExisting) {
            JSONObject slotObject = new JSONObject();
            slotObject.put("data", dataObject);
            slotObject.put("id", game.getSessionId());
            slotObject.put("saveTime", LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

            oldSessionsArray.put(slotObject);
        }
    }
}