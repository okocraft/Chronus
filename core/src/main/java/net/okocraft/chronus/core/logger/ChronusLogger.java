package net.okocraft.chronus.core.logger;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.SubstituteLogger;

public final class ChronusLogger {

    private static final SubstituteLogger LOGGER = new SubstituteLogger("Chronus", null, true);
    private static final SubstituteLogger DEBUG = new SubstituteLogger("Chronus Debug", null, true);

    static {
        try {
            Class.forName("org.junit.jupiter.api.Assertions");
            LOGGER.setDelegate(LoggerFactory.getLogger(ChronusLogger.class));
        } catch (ClassNotFoundException ignored) {
        }
    }

    /**
     * Gets the normal {@link Logger}.
     *
     * @return the normal {@link Logger}.
     */
    public static @NotNull Logger log() {
        return LOGGER;
    }

    /**
     * Gets the debug {@link Logger}.
     *
     * @return the debug {@link Logger}.
     */
    public static @NotNull Logger debug() {
        return DEBUG;
    }

    /**
     * Enables or disabled debug logs.
     *
     * @param newState {@code true} to enable debug logs, {@code false} to disable them
     */
    public static void logDebug(boolean newState) {
        DEBUG.setDelegate(newState ? LOGGER : null);
    }

    private ChronusLogger() {
        throw new UnsupportedOperationException();
    }
}
