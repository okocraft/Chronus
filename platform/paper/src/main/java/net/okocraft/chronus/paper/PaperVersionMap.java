package net.okocraft.chronus.paper;

import net.okocraft.chronus.paper.versions.shared.PaperImplementation;
import net.okocraft.chronus.paper.versions.v1_19_R2.Paper_1_19_R2;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;

final class PaperVersionMap {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    static @Nullable PaperImplementation getCurrentVersion() {
        return switch (Bukkit.getMinecraftVersion()) {
            case "1.19.3" -> new Paper_1_19_R2();
            default -> null;
        };
    }

    private PaperVersionMap() {
        throw new UnsupportedOperationException();
    }
}
