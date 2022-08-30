package me.iron.HypixelRequeue.util.hypixel.chat;

import me.iron.HypixelRequeue.util.hypixel.LocrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LocrawChatHandler {

    public void register() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    public void unregister() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent
    public void onMessageReceived(ClientChatReceivedEvent event) {
        // not defining message variable as to decrease number of actions
        // if SubscribeEvent is registered but not processing relevant information
        if (LocrawUtil.isFetchingLocraw.get()) {
            try {
                String message = event.message.getUnformattedText();
                if (message.startsWith("{")) {

                    event.setCanceled(true);
                    new LocrawUtil().setLocrawData(message);
                    new LocrawChatHandler().unregister();
                    LocrawUtil.isFetchingLocraw.set(false);

                }
            } catch (Exception ignored) {}
        }
    }
}
