package net.okocraft.chronus.core.logger;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaLogger implements LoggerWrapper {

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull JavaLogger wrap(@NotNull Logger logger) {
        return new JavaLogger(logger);
    }

    private final Logger logger;

    private JavaLogger(@NotNull Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(@NotNull String log) {
        logger.info(log);
    }

    @Override
    public void warn(@NotNull String log) {
        logger.warning(log);
    }

    @Override
    public void severe(@NotNull String log) {
        logger.severe(log);
    }

    @Override
    public void severe(@NotNull String log, @NotNull Throwable t) {
        logger.log(Level.SEVERE, log, t);
    }
}
