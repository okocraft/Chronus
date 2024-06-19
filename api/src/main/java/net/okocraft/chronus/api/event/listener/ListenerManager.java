package net.okocraft.chronus.api.event.listener;

import com.github.siroshun09.event4j.listener.SubscribedListener;
import com.github.siroshun09.event4j.priority.Priority;
import net.okocraft.chronus.api.event.ChronusEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface ListenerManager {

    <E extends ChronusEvent> @NotNull SubscribedListener<?, E, Priority> subscribe(@NotNull Consumer<? super E> consumer);

    <E extends ChronusEvent> @NotNull SubscribedListener<?, E, Priority> subscribe(@NotNull Consumer<? super E> consumer, @NotNull Priority priority);

    void unsubscribe(@NotNull SubscribedListener<?, ? extends ChronusEvent, Priority> listener);

    void unsubscribeAll();

}
