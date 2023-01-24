package net.okocraft.chronus.api.eventbus.listener;

import net.okocraft.chronus.api.event.ChronusEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.NonExtendable
public interface SubscribedListener<E extends ChronusEvent> {

    @NotNull Class<E> eventClass();

    @NotNull Listener<E> listener();

    @NotNull Priority priority();

}
