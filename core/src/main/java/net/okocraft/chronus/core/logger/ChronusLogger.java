package net.okocraft.chronus.core.logger;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public final class ChronusLogger {

    public static LoggerWrapper logger;
    public static boolean debug;

    public static @NotNull LoggerWrapper get() {
        return logger != null ? logger : NonLoggingLogger.INSTANCE;
    }

    public static boolean isSet() {
        return logger != null;
    }

    public static void debug(@NotNull String log) {
        if (debug) {
            get().info("DEBUG: " + log);
        }
    }

    public static void debug(@NotNull Supplier<String> logSupplier) {
        if (debug) {
            get().info("DEBUG: " + logSupplier.get());
        }
    }

    public static void info(@NotNull String log) {
        get().info(log);
    }

    public static void warn(@NotNull String log) {
        get().warn(log);
    }

    public static void severe(@NotNull String log) {
        get().severe(log);
    }

    public static void severe(@NotNull String log, @NotNull Throwable t) {
        get().severe(log, t);
    }

}
