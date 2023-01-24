package net.okocraft.chronus.api.eventbus.subscriber;

import net.okocraft.chronus.api.event.ChronusEvent;
import net.okocraft.chronus.api.eventbus.listener.Listener;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * A subscriber to manage {@link Listener}s.
 *
 * @param <E> the type of event
 */
@SuppressWarnings("UnusedReturnValue")
@ApiStatus.NonExtendable
public interface EventSubscriber<E extends ChronusEvent> {

    /**
     * Gets the event {@link Class} of this subscriber.
     *
     * @return the event {@link Class} of this subscriber
     */
    @NotNull Class<E> getEventClass();

    // TODO: subscribe/unsubscribe methods

    /**
     * Checks if this subscriber is closed.
     *
     * @return if this subscriber is closed, returns {@code true}, otherwise {@code false}
     */
    boolean isClosed();
}
