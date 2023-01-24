package net.okocraft.chronus.core.api.eventbus;

import com.github.siroshun09.event4j.bus.EventBus;
import net.okocraft.chronus.api.event.ChronusEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

public class EventBusWrapper implements net.okocraft.chronus.api.eventbus.EventBus {

    private final EventBus<ChronusEvent> bus;

    private EventBusWrapper(@NotNull EventBus<ChronusEvent> eventBus) {
        this.bus = eventBus;
    }

    public static @NotNull EventBusWrapper create() {
        return wrap(EventBus.create(ChronusEvent.class));
    }

    public static @NotNull EventBusWrapper wrap(@NotNull EventBus<ChronusEvent> bus) {
        return new EventBusWrapper(bus);
    }

    @Override
    public @NotNull <E extends ChronusEvent> EventSubscriberWrapper<E> getSubscriber(@NotNull Class<E> eventClass) {
        return new EventSubscriberWrapper<>(bus.getSubscriber(eventClass));
    }

    @Override
    public <T extends ChronusEvent> @NotNull T callEvent(@NotNull T event) {
        return bus.callEvent(event);
    }

    @Override
    public boolean isClosed() {
        return bus.isClosed();
    }

    public void close() {
        bus.close();
    }

    @TestOnly
    public @NotNull EventBus<ChronusEvent> bus() {
        return bus;
    }
}
