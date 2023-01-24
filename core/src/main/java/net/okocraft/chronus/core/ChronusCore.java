package net.okocraft.chronus.core;

import net.okocraft.chronus.api.APISetter;
import net.okocraft.chronus.core.api.ChronusAPI;
import net.okocraft.chronus.core.api.eventbus.EventBusWrapper;
import net.okocraft.chronus.core.logger.ChronusLogger;
import net.okocraft.chronus.core.platform.PlatformAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * A core class for loading/enabling/disabling Chronus.
 */
public class ChronusCore {

    private final PlatformAdapter platformAdapter;
    private ChronusAPI api;

    public ChronusCore(@NotNull PlatformAdapter platformAdapter) {
        this.platformAdapter = platformAdapter;

        ChronusLogger.logger = platformAdapter.getLogger();
    }

    /**
     * Performs loading.
     */
    public void load() {
    }

    /**
     * Performs startup.
     */
    public void startup() {
        // print Chronus version and platform information
        platformAdapter.getLogger().info("Chronus - " + getClass().getPackage().getImplementationVersion());
        platformAdapter.getLogger().info("Running on " + platformAdapter.getPlatformName() + " " + platformAdapter.getPlatformVersion());

        api = new ChronusAPI(EventBusWrapper.create());

        APISetter.set(api);
    }

    /**
     * Performs shutdown.
     */
    public void shutdown() {
        APISetter.unset();

        api.getEventBus().close();
    }
}
