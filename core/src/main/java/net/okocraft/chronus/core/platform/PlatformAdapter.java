package net.okocraft.chronus.core.platform;

import net.okocraft.chronus.core.logger.LoggerWrapper;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public interface PlatformAdapter {

    @NotNull String getPlatformName();

    @NotNull String getPlatformVersion();

    @NotNull LoggerWrapper getLogger();

    @NotNull Path getDataDirectory();
}
