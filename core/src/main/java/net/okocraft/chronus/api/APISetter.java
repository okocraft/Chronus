package net.okocraft.chronus.api;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A setter class for {@link APIHolder}.
 * <p>
 * This class is used to set the Chronus instance to {@link APIHolder}.
 */
public final class APISetter {

    private APISetter() {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the api instance.
     *
     * @param api the api instance to set
     */
    public static void set(@NotNull Chronus api) {
        APIHolder.set(Objects.requireNonNull(api));
    }

    /**
     * Unsets the api.
     */
    public static void unset() {
        APIHolder.set(null);
    }
}
