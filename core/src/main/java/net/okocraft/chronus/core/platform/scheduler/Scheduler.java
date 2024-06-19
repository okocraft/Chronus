package net.okocraft.chronus.core.platform.scheduler;

import org.jetbrains.annotations.NotNull;

public interface Scheduler {

    void runAsync(@NotNull Runnable task);

}
