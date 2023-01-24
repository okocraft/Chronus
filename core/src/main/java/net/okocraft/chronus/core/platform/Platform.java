package net.okocraft.chronus.core.platform;

import net.okocraft.chronus.core.logger.LoggerWrapper;
import org.jetbrains.annotations.NotNull;

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
     * Gets the {@link LoggerWrapper}.
     *
     * @return the {@link LoggerWrapper}
     */
    @NotNull LoggerWrapper getLogger();

    /**
     * Gets the directory to save data.
     *
     * @return the directory to save data
     */
    @NotNull Path getDataDirectory();
}
