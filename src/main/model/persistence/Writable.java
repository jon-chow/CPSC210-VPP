package model.persistence;

import org.json.JSONObject;

// an interface for objects that write to the save file
public interface Writable {
    JSONObject toJsonObj();
}
