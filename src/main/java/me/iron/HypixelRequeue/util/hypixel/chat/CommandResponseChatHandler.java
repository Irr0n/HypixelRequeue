package me.iron.HypixelRequeue.util.hypixel.chat;

import me.iron.HypixelRequeue.commands.Requeue;
import me.iron.HypixelRequeue.config.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class CommandResponseChatHandler {

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
        if (Requeue.isSendingPlay.get()) {
            try {
                String message = event.message.getUnformattedText();
                if (message.contains("You are sending")) {
                    event.setCanceled(true);
                    if (Settings.autoLobby) {
                        Minecraft.getMinecraft().thePlayer.sendChatMessage("/l");
                    }
                    new CommandResponseChatHandler().unregister();
                    Requeue.isSendingPlay.set(false);
                } else if (message.contains("Please don't spam")) {
                    event.setCanceled(true);
                    if (Settings.autoLobby) {
                        Minecraft.getMinecraft().thePlayer.sendChatMessage("/l");
                    }
                    new CommandResponseChatHandler().unregister();
                    Requeue.isSendingPlay.set(false);
                }
            } catch (Exception ignored) {}
        }
    }
}
