package net.okocraft.chronus.api.event;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * An abstract event class for Chronus events.
 */
public abstract class ChronusEvent {

    private final String eventName;

    /**
     * Creates an event without specifying a name.
     */
    protected ChronusEvent() {
        eventName = getClass().getSimpleName();
    }

    /**
     * Creates a named event.
     *
     * @param eventName the name of this event.
     */
    protected ChronusEvent(@NotNull String eventName) {
        Objects.requireNonNull(eventName);
        this.eventName = eventName;
    }

    /**
     * Gets a named event.
     *
     * @return the name of this event.
     */
    public @NotNull String getEventName() {
        return eventName;
    }
}
