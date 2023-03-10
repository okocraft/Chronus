package net.okocraft.chronus.paper;

import net.okocraft.chronus.core.logger.JavaLogger;
import net.okocraft.chronus.core.logger.LoggerWrapper;
import net.okocraft.chronus.core.platform.Platform;
import net.okocraft.chronus.paper.versions.shared.PaperImplementation;
import org.bukkit.Bukkit;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

class PaperPlatform implements Platform {

    private final PaperChronusPlugin plugin;
    private final @MonotonicNonNull PaperImplementation paperImplementation;
    private final JavaLogger javaLogger;

    PaperPlatform(@NotNull PaperChronusPlugin plugin) {
        this.plugin = plugin;
        this.paperImplementation = PaperVersionMap.getCurrentVersion();
        this.javaLogger = JavaLogger.wrap(plugin.getLogger());
    }

    @Override
    public @NotNull String getName() {
        return Bukkit.getName();
    }

    @Override
    public @NotNull String getVersion() {
        return Bukkit.class.getPackage().getImplementationVersion();
    }

    @Override
    public @NotNull LoggerWrapper getLogger() {
        return javaLogger;
    }

    @Override
    public @NotNull Path getDataDirectory() {
        return plugin.getDataFolder().toPath();
    }

    @Nullable PaperImplementation getPaperImplementation() {
        return paperImplementation;
    }
}
