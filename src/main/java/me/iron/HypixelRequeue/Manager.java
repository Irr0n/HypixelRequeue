package me.iron.HypixelRequeue;

import me.iron.HypixelRequeue.commands.Requeue;

import me.iron.HypixelRequeue.config.Config;
import me.iron.HypixelRequeue.util.hypixel.LocrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Manager.MOD_ID,
        name = Manager.MOD_NAME,
        version = Manager.VERSION
)
public class Manager {

    //todo: push info to gradle
    public static final String MOD_ID = "hypixel_requeue";
    public static final String VERSION = "0.0.2";
    public static final String MOD_NAME = "Hypixel Requeue";

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        Config.initConfig(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {

        loadCommands();
        //loadKeyBinds();


        MinecraftForge.EVENT_BUS.register(this);

    }

    private void loadCommands() {
        //todo: add reflection to command classes
        ClientCommandHandler.instance.registerCommand(new Requeue());
    }

}
