package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            JSONObject root = new JSONObject(json);
            JSONArray nameOccurences = root.getJSONArray("name");
            String sandwichName = "";
            List<String> akaList = new ArrayList<>();
            for (int i = 0; i < nameOccurences.length(); i++) {
                JSONObject nameObject = nameOccurences.getJSONObject(i);
                sandwichName = nameObject.getString("mainName");
                JSONArray akaArray = nameObject.getJSONArray("alsoKnownAs");
                for (int j = 0; j < akaArray.length(); j++) {
                    akaList.add(akaArray.getString(i));
                }
            }
            String placeOfOrigin = root.getString("placeOfOrigin");
            String description = root.getString("description");
            String image = root.getString("image");
            JSONArray ingredientsArray = root.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }
            return new Sandwich(sandwichName, akaList, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich();
    }
}
