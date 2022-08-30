package me.iron.HypixelRequeue.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Json {

    public static JsonObject getStringAsJsonObject(String string) {
        try {
            return new JsonParser().parse(string).getAsJsonObject();
        } catch (Exception ignored) {}
        return null;
    }

}
