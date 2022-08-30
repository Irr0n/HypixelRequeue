package me.iron.HypixelRequeue.commands;

import me.iron.HypixelRequeue.config.Settings;
import me.iron.HypixelRequeue.util.Multithreading;
import me.iron.HypixelRequeue.util.hypixel.LocrawUtil;

import me.iron.HypixelRequeue.util.hypixel.chat.CommandResponseChatHandler;
import me.iron.HypixelRequeue.util.DisplayUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Requeue extends CommandBase {

    private final List<String> aliases;

    private final String command = "requeue";

    public Requeue() {
        this.aliases = Collections.singletonList("rq");
    }

    public String getCommandName() {
        return this.command;
    }

    public String getCommandUsage(ICommandSender sender) { return "run /rq help for more info"; }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public List<String> getCommandAliases() {
        return this.aliases;
    }

    public static AtomicBoolean isSendingPlay = new AtomicBoolean(false);

    public void processCommand(ICommandSender sender, String[] args) {
        // todo: create method, used in keybind

        if (args.length == 0) {
            Multithreading.runAsync(() -> {

                if (!LocrawUtil.isFetchingLocraw.get()) { new LocrawUtil().getLocrawData(); }
                while (LocrawUtil.isFetchingLocraw.get()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {}
                }
                String mode =  LocrawUtil.getMode().toLowerCase();

                CommandResponseChatHandler CommandResponseChatHandler = new CommandResponseChatHandler();

                if (!mode.equals("")) {
                    isSendingPlay.set(true);
                    CommandResponseChatHandler.register();
                    if (Settings.gameTo) {
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + mode));
                    }
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/play " + mode);
                }
            });
        } else if (args.length == 1) {
            if (args[0].equals("help")) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Requeue Options:"));
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("help " +
                        EnumChatFormatting.DARK_GRAY + "-\n " +
                        EnumChatFormatting.GRAY + "shows this help information"));
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                    DisplayUtil.colorBoolean(Settings.autoLobby) + "autolobby " +
                        EnumChatFormatting.DARK_GRAY + "-\n " +
                        EnumChatFormatting.GRAY + "return to lobby if you're sending commands too fast"));
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                    DisplayUtil.colorBoolean(Settings.gameTo) + "gameto " +
                        EnumChatFormatting.DARK_GRAY + "-\n " +
                        EnumChatFormatting.GRAY + "send the name of the game you are trying to be sent to"));
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(""));
            }
            else if (args[0].equals("autolobby")) {
                Settings.autoLobby = !Settings.autoLobby;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                        EnumChatFormatting.GRAY + "autolobby" +
                        EnumChatFormatting.DARK_GRAY + " - " +
                        DisplayUtil.formatBooleanState(Settings.autoLobby)
                ));
            }
            else if (args[0].equals("gameto")) {
                Settings.gameTo = !Settings.gameTo;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                        EnumChatFormatting.GRAY + "gameto" +
                                EnumChatFormatting.DARK_GRAY + " - " +
                                DisplayUtil.formatBooleanState(Settings.gameTo)
                ));
            }
        }

    }
}
