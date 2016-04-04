package com.cardosoedgar.beerapp;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgarcardoso on 4/4/16.
 */
public class Utils {

    public static List<Beer> readBeerJson(Context context) {
        try {
            String json = loadJSONFromAsset(context);
            Type listType = new TypeToken<ArrayList<Beer>>(){}.getType();
            return new GsonBuilder().create().fromJson(json, listType);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("beers.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
