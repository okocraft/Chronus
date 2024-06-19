package net.okocraft.chronus.api;

import com.github.siroshun09.event4j.caller.AsyncEventCaller;
import net.okocraft.chronus.api.event.ChronusEvent;
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
     * Gets an {@link AsyncEventCaller} to call {@link ChronusEvent}s.
     *
     * @return an {@link AsyncEventCaller}
     */
    @NotNull AsyncEventCaller<ChronusEvent> eventCaller();

    // TODO: add an interface for subscribing event listeners
}
