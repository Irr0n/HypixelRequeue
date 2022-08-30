package me.iron.HypixelRequeue.util.hypixel;

import com.google.gson.JsonObject;
import me.iron.HypixelRequeue.util.Json;
import me.iron.HypixelRequeue.util.hypixel.chat.LocrawChatHandler;
import net.minecraft.client.Minecraft;
import java.util.concurrent.atomic.AtomicBoolean;

public class LocrawUtil {

    public static AtomicBoolean isFetchingLocraw = new AtomicBoolean(false);

    public static JsonObject locrawData;

    public void setLocrawData(String locrawStringData) {
        locrawData = Json.getStringAsJsonObject(locrawStringData);
    }

    public void getLocrawData() {

        isFetchingLocraw.set(true);
        new LocrawChatHandler().register();
        Minecraft.getMinecraft().thePlayer.sendChatMessage("/locraw");
    }

    public static String getGameType() {
        try {
            return String.valueOf(locrawData.get("gametype").getAsString());
        } catch (Exception fallback) {
            return "";
        }
    }

    public static String getServer() {
        try {
            return String.valueOf(locrawData.get("server").getAsString());
        } catch (Exception fallback) {
            return "";
        }
    }

    public static String getMap() {
        try {
            return String.valueOf(locrawData.get("map").getAsString());
        } catch (Exception fallback) {
            return "";
        }
    }

    public static String getMode() {
        try {
            return String.valueOf(locrawData.get("mode").getAsString());
        } catch (Exception fallback) {
            return "";
        }
    }

}
