package net.okocraft.chronus.core.api;

import net.okocraft.chronus.api.Chronus;
import net.okocraft.chronus.core.api.eventbus.EventBusWrapper;
import org.jetbrains.annotations.NotNull;

/**
 * A class for implementing {@link Chronus}.
 */
public class ChronusAPI implements Chronus {

    private final EventBusWrapper eventBusWrapper;

    public ChronusAPI(@NotNull EventBusWrapper eventBusWrapper) {
        this.eventBusWrapper = eventBusWrapper;
    }

    @Override
    public @NotNull EventBusWrapper getEventBus() {
        return eventBusWrapper;
    }
}
