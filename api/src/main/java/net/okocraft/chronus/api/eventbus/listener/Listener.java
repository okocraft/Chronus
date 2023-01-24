package net.okocraft.chronus.api.eventbus.listener;

import org.jetbrains.annotations.NotNull;

/**
 * An interface to receive and process events.
 *
 * @param <E> the event type
 */
@FunctionalInterface
public interface Listener<E> {

    /**
     * The method to receive events.
     *
     * @param event the event
     */
    void handle(@NotNull E event);

}
