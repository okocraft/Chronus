package net.okocraft.chronus.core;

import com.github.siroshun09.event4j.priority.Priority;
import com.github.siroshun09.event4j.simple.EventServiceProvider;
import net.okocraft.chronus.api.APISetter;
import net.okocraft.chronus.api.event.ChronusEvent;
import net.okocraft.chronus.core.api.ChronusAPI;
import net.okocraft.chronus.core.logger.ChronusLogger;
import net.okocraft.chronus.core.platform.Platform;
import net.okocraft.chronus.core.platform.scheduler.Scheduler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.helpers.SubstituteLogger;

/**
 * A core class for loading/enabling/disabling Chronus.
 */
public class ChronusCore {

    public static ChronusCore initialize(@NotNull Platform platform) {
        ((SubstituteLogger) ChronusLogger.log()).setDelegate(platform.getLogger());
        return new ChronusCore(platform);
    }

    private final Platform platform;
    private final EventServiceProvider<Void, ChronusEvent, Priority> eventServiceProvider;

    private ChronusCore(@NotNull Platform platform) {
        this.platform = platform;
        this.eventServiceProvider = createEventServiceProvider(platform.scheduler());
    }

    /**
     * Performs loading.
     */
    public void load() {
    }

    /**
     * Performs startup.
     */
    public void startup() {
        // print Chronus version and platform information
        platform.getLogger().info("Chronus - " + getClass().getPackage().getImplementationVersion());
        platform.getLogger().info("Running on " + platform.getName() + " " + platform.getVersion());

        APISetter.set(new ChronusAPI(
                this.eventServiceProvider.asyncCaller()
        ));
    }

    /**
     * Performs shutdown.
     */
    public void shutdown() {
        APISetter.unset();
    }

    private static @NotNull EventServiceProvider<Void, ChronusEvent, Priority> createEventServiceProvider(@NotNull Scheduler scheduler) {
        return EventServiceProvider.factory()
                .keyClass(Void.class) // Change later
                .eventClass(ChronusEvent.class)
                .orderComparator(Priority.COMPARATOR, Priority.NORMAL)
                .executor(scheduler::runAsync)
                .create();
    }
}
