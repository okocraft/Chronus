package net.okocraft.chronus.api.eventbus;

import net.okocraft.chronus.api.event.ChronusEvent;
import net.okocraft.chronus.api.eventbus.subscriber.EventSubscriber;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.NonExtendable
public interface EventBus {

    /**
     * Gets the {@link EventSubscriber} of the specified event.
     *
     * @param eventClass the event class to get the {@link EventSubscriber}
     * @return the {@link EventSubscriber} of the specified event
     * @throws IllegalStateException if this event bus is already closed
     */
    <E extends ChronusEvent> @NotNull EventSubscriber<E> getSubscriber(@NotNull Class<E> eventClass);

    // TODO: unsubscribe methods (unsubscribe, unsubscribeAll)

    /**
     * Dispatches the event to subscribed listeners.
     * <p>
     * This method also dispatches to listeners of the higher class of the given event.
     *
     * @param event the event instance
     * @return the given event instance
     * @throws IllegalStateException if this event bus is already closed
     */
    <E extends ChronusEvent> @NotNull E callEvent(@NotNull E event);

    /**
     * Checks if this event bus is closed.
     *
     * @return if this event bus is closed, returns {@code true}, otherwise {@code false}
     */
    boolean isClosed();
}
