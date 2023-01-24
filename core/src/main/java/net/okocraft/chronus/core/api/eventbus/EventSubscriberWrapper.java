package net.okocraft.chronus.core.api.eventbus;

import com.github.siroshun09.event4j.bus.EventSubscriber;
import com.github.siroshun09.event4j.key.Key;
import net.okocraft.chronus.api.event.ChronusEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.annotations.VisibleForTesting;

@VisibleForTesting
public class EventSubscriberWrapper<E extends ChronusEvent> implements net.okocraft.chronus.api.eventbus.subscriber.EventSubscriber<E> {

    private final EventSubscriber<E> subscriber;

    public EventSubscriberWrapper(@NotNull EventSubscriber<E> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public @NotNull Class<E> getEventClass() {
        return subscriber.getEventClass();
    }

    @Override
    public boolean isClosed() {
        return subscriber.isClosed();
    }

    @TestOnly
    public void subscribe(@NotNull Key key, @NotNull com.github.siroshun09.event4j.listener.Listener<E> listener) {
        this.subscribe(key, listener, com.github.siroshun09.event4j.priority.Priority.NORMAL);
    }

    @TestOnly
    public void subscribe(@NotNull Key key,
                          @NotNull com.github.siroshun09.event4j.listener.Listener<E> listener,
                          @NotNull com.github.siroshun09.event4j.priority.Priority priority) {
        subscriber.subscribe(key, listener, priority);
    }
}
