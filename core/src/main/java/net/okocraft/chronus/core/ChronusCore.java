package net.okocraft.chronus.core;

import net.okocraft.chronus.api.APISetter;
import net.okocraft.chronus.core.api.ChronusAPI;

/**
 * A core class for loading/enabling/disabling Chronus.
 */
public class ChronusCore {

    private ChronusAPI api;

    /**
     * Performs loading.
     */
    public void load() {
    }

    /**
     * Performs startup.
     */
    public void startup() {
        api = new ChronusAPI();

        APISetter.set(api);
    }

    /**
     * Performs shutdown.
     */
    public void shutdown() {
        APISetter.unset();
    }
}
