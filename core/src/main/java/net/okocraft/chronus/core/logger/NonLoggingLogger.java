package net.okocraft.chronus.core.logger;

import org.jetbrains.annotations.NotNull;

final class NonLoggingLogger implements LoggerWrapper{

    static final NonLoggingLogger INSTANCE = new NonLoggingLogger();

    private NonLoggingLogger() {
    }

    @Override
    public void info(@NotNull String log) {
    }

    @Override
    public void warn(@NotNull String log) {
    }

    @Override
    public void severe(@NotNull String log) {
    }

    @Override
    public void severe(@NotNull String log, @NotNull Throwable t) {
    }
}
