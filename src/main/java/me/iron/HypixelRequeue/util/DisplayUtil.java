package me.iron.HypixelRequeue.util;

import net.minecraft.util.EnumChatFormatting;

public class DisplayUtil {

    public static EnumChatFormatting colorBoolean(boolean state) {
        return state ? EnumChatFormatting.GREEN : EnumChatFormatting.RED;
    }

    public static String formatBooleanState(boolean state) {
        return state ? EnumChatFormatting.GREEN + "true" : EnumChatFormatting.RED + "false";
    }

}
