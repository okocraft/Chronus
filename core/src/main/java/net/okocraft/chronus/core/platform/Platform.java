package net.okocraft.chronus.core.platform;

import net.okocraft.chronus.core.platform.scheduler.Scheduler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * An interface to get platform-dependent implementations.
 */
public interface Platform {

    /**
     * Gets the name of the platform.
     *
     * @return the name of the platform
     */
    @NotNull String getName();

    /**
     * Gets the version of the platform.
     *
     * @return the version of the platform
     */
    @NotNull String getVersion();

    /**
     * Gets the {@link Logger}.
     *
     * @return the {@link Logger}
     */
    @NotNull Logger getLogger();

    /**
     * Gets the directory to save data.
     *
     * @return the directory to save data
     */
    @NotNull Path getDataDirectory();

    /**
     * Gets the {@link Scheduler}.
     *
     * @return the {@link Scheduler}
     */
    @NotNull
    Scheduler scheduler();
}
