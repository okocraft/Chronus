package net.okocraft.chronus.api;

import net.okocraft.chronus.api.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

/**
 * Chronus API.
 */
public interface Chronus {

    /**
     * Gets the {@link Chronus} instance.
     *
     * @return the {@link Chronus} instance
     * @throws IllegalStateException if Chronus is not initialized.
     */
    static @NotNull Chronus api() throws IllegalStateException {
        return APIHolder.get();
    }

    /**
     * Checks if Chronus is already initialized.
     *
     * @return {@code true} if the Chronus is initialized, otherwise {@code false}
     */
    static boolean isInitialized() {
        return APIHolder.isAPISet();
    }

    /**
     * Gets the {@link EventBus}.
     *
     * @return the {@link EventBus}
     */
    @NotNull EventBus getEventBus();
}
