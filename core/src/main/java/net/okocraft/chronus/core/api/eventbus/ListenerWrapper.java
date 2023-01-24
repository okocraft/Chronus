package net.okocraft.chronus.core.api.eventbus;

import net.okocraft.chronus.api.event.ChronusEvent;
import net.okocraft.chronus.api.eventbus.listener.Listener;
import net.okocraft.chronus.core.logger.ChronusLogger;
import org.jetbrains.annotations.NotNull;

public record ListenerWrapper<E extends ChronusEvent>(@NotNull Listener<E> listener) implements com.github.siroshun09.event4j.listener.Listener<E> {

    @Override
    public void handle(@NotNull E event) {
        listener.handle(event);
    }

    @Override
    public void handleException(@NotNull E event, @NotNull Throwable throwable) {
        if (ChronusLogger.isSet()) {
            ChronusLogger.severe("An exception occurred while handling " + event.getEventName(), throwable);
        } else {
            throw new RuntimeException(throwable);
        }
    }
}
