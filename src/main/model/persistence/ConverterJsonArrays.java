package model.persistence;

import model.goodsandservices.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

// a converter between ArrayLists and JSONArrays
public class ConverterJsonArrays {
//    // EFFECTS: converts an ArrayList<String> to a JSONArray and returns it
//    public static JSONArray arrayListStringToJson(ArrayList<String> arrayList) {
//        JSONArray jsonArray = new JSONArray();
//
//        for (String string : arrayList) {
//            jsonArray.put(string);
//        }
//
//        return jsonArray;
//    }

    // EFFECTS: converts an ArrayList<Integer> to a JSONArray and returns it
    public static JSONArray arrayListIntToJson(ArrayList<Integer> arrayList) {
        JSONArray jsonArray = new JSONArray();

        for (int integer : arrayList) {
            jsonArray.put(integer);
        }

        return jsonArray;
    }

    // EFFECTS: converts an ArrayList<Item> to a JSONArray and returns it
    public static JSONArray arrayListItemToJson(ArrayList<Item> arrayList) {
        JSONArray jsonArray = new JSONArray();

        for (Item item : arrayList) {
            JSONObject itemObj = item.toJsonObj();
            jsonArray.put(itemObj);
        }

        return jsonArray;
    }

//    // EFFECTS: converts a JSONArray to an ArrayList<String> and returns it
//    public static ArrayList<String> jsonToArrayListString(JSONArray arrayList) {
//        ArrayList<String> stringArray = new ArrayList<>();
//
//        for (int i = 0; i < arrayList.length(); i++) {
//            stringArray.add(arrayList.getString(i));
//        }
//
//        return stringArray;
//    }

    // EFFECTS: converts a JSONArray to an ArrayList<Integer> and returns it
    public static ArrayList<Integer> jsonToArrayListInt(JSONArray arrayList) {
        ArrayList<Integer> intArray = new ArrayList<>();

        for (int i = 0; i < arrayList.length(); i++) {
            intArray.add(arrayList.getInt(i));
        }

        return intArray;
    }

    // EFFECTS: converts a JSONArray to an ArrayList<Item> and returns it
    public static ArrayList<Item> jsonToArrayListItem(JSONArray arrayList) throws IOException {
        ArrayList<Item> itemArray = new ArrayList<>();

        for (int i = 0; i < arrayList.length(); i++) {
            JSONObject jsonItem = arrayList.getJSONObject(i);
            String itemName = jsonItem.getString("name");
            String itemType = jsonItem.getString("type");

            Item item = new Item(itemName, itemType);
            itemArray.add(item);
        }

        return itemArray;
    }
}
