package net.okocraft.chronus.paper;

import net.okocraft.chronus.core.ChronusCore;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperChronusPlugin extends JavaPlugin {

    private final PaperPlatform paperPlatform = new PaperPlatform(this);
    private final ChronusCore core = new ChronusCore(paperPlatform);

    @Override
    public void onLoad() {
        // If no implementation of the current version is found, it will not be loaded.
        if (paperPlatform.getPaperImplementation() != null) {
            core.load();
        }
    }

    @Override
    public void onEnable() {
        // If no implementation of the current version is found, it will not be enabled.
        if (paperPlatform.getPaperImplementation() != null) {
            core.startup();
        } else {
            getLogger().severe("The running version (" + getServer().getMinecraftVersion() + ") is not supported!");
            getLogger().severe("Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // If no implementation of the current version is found, it will not be shut down.
        if (paperPlatform.getPaperImplementation() != null) {
            core.shutdown();
        }
    }
}
