package net.okocraft.chronus.paper;

import net.okocraft.chronus.core.ChronusCore;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperChronusPlugin extends JavaPlugin {

    private final ChronusCore core = new ChronusCore(new PaperPlatform(this));

    @Override
    public void onLoad() {
        core.load();
    }

    @Override
    public void onEnable() {
        core.startup();
    }

    @Override
    public void onDisable() {
        core.shutdown();
    }
}
