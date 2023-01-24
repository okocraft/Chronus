package net.okocraft.chronus.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A class for holding {@link Chronus} instance.
 */
final class APIHolder {

    private static Chronus API_INSTANCE = null;

    /**
     * Gets the {@link Chronus} instance.
     *
     * @return {@link Chronus} instance
     * @throws IllegalStateException if {@link Chronus} instance is not set
     */
    static @NotNull Chronus get() {
        if (API_INSTANCE == null) {
            throw new IllegalStateException("Chronus is not initialized.");
        }
        return API_INSTANCE;
    }

    /**
     * Checks if the {@link Chronus} is set.
     *
      * @return {@code true} if the {@link Chronus} is set, otherwise {@code false}
     */
    static boolean isAPISet() {
        return API_INSTANCE != null;
    }

    /**
     * Sets the {@link Chronus} instance.
     *
     * @param apiInstance {@link Chronus} instance
     */
    static void set(@Nullable Chronus apiInstance) {
        API_INSTANCE = apiInstance;
    }
}
