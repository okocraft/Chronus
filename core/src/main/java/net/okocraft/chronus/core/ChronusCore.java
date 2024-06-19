package net.okocraft.chronus.core;

import net.okocraft.chronus.api.APISetter;
import net.okocraft.chronus.core.api.ChronusAPI;
import net.okocraft.chronus.core.api.eventbus.EventBusWrapper;
import net.okocraft.chronus.core.logger.ChronusLogger;
import net.okocraft.chronus.core.platform.Platform;
import org.jetbrains.annotations.NotNull;
import org.slf4j.helpers.SubstituteLogger;

/**
 * A core class for loading/enabling/disabling Chronus.
 */
public class ChronusCore {

    public static ChronusCore initialize(@NotNull Platform platform) {
        ((SubstituteLogger) ChronusLogger.log()).setDelegate(platform.getLogger());
        return new ChronusCore(platform);
    }

    private final Platform platform;
    private ChronusAPI api;

    private ChronusCore(@NotNull Platform platform) {
        this.platform = platform;
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
        platform.getLogger().info("Chronus - " + getClass().getPackage().getImplementationVersion());
        platform.getLogger().info("Running on " + platform.getName() + " " + platform.getVersion());

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
