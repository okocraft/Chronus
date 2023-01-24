package net.okocraft.chronus.core.api.eventbus;

import com.github.siroshun09.event4j.bus.SubscribedListener;
import net.okocraft.chronus.api.event.ChronusEvent;
import net.okocraft.chronus.api.eventbus.listener.Listener;
import net.okocraft.chronus.api.eventbus.listener.Priority;
import org.jetbrains.annotations.NotNull;

class SubscribedListenerWrapper<E extends ChronusEvent> implements net.okocraft.chronus.api.eventbus.listener.SubscribedListener<E> {

    public static <E extends ChronusEvent> @NotNull SubscribedListener<E> unwrap(@NotNull net.okocraft.chronus.api.eventbus.listener.SubscribedListener<E> subscribedListener) {
        if (subscribedListener instanceof SubscribedListenerWrapper<E> subscribedListenerWrapper) {
            return subscribedListenerWrapper.subscribedListener;
        } else {
            throw new IllegalArgumentException(subscribedListener + " is not SubscribedListenerWrapper");
        }
    }

    private final SubscribedListener<E> subscribedListener;
    private final Listener<E> listener;
    private final Priority priority;

    SubscribedListenerWrapper(@NotNull SubscribedListener<E> wrappedSubscribedListener,
                              @NotNull Listener<E> listener, @NotNull Priority priority) {
        this.subscribedListener = wrappedSubscribedListener;
        this.listener = listener;
        this.priority = priority;
    }

    @Override
    public @NotNull Class<E> eventClass() {
        return subscribedListener.eventClass();
    }

    @Override
    public @NotNull Listener<E> listener() {
        return listener;
    }

    @Override
    public @NotNull Priority priority() {
        return priority;
    }
}
