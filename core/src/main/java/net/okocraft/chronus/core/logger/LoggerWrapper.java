package net.okocraft.chronus.core.logger;

import org.jetbrains.annotations.NotNull;

public interface LoggerWrapper {

    void info(@NotNull String log);

    void warn(@NotNull String log);

    void severe(@NotNull String log);

    void severe(@NotNull String log, @NotNull Throwable t);

}
