package net.okocraft.chronus.core.api;

import com.github.siroshun09.event4j.caller.AsyncEventCaller;
import net.okocraft.chronus.api.Chronus;
import net.okocraft.chronus.api.event.ChronusEvent;
import org.jetbrains.annotations.NotNull;

/**
 * A class for implementing {@link Chronus}.
 */
public record ChronusAPI(
        @NotNull AsyncEventCaller<ChronusEvent> eventCaller
) implements Chronus {
}
