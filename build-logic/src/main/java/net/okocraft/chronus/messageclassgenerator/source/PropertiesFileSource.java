package net.okocraft.chronus.messageclassgenerator.source;

import net.okocraft.chronus.messageclassgenerator.option.GetterOption;
import net.okocraft.chronus.messageclassgenerator.processor.PropertiesProcessor;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class PropertiesFileSource implements MessageSourceSupplier, Watchable {

    public final Path filepath;
    public final GetterOption getterOption = new GetterOption();

    public PropertiesFileSource(Path filepath) {
        this.filepath = filepath;
    }

    public String packageName;
    public String className;

    @Override
    public Stream<MessageSource> stream() {
        if (!Files.isRegularFile(filepath)) {
            return Stream.of(createMessageSource(Collections.emptyMap()));
        }

        return Stream.of(createMessageSource());
    }

    @Override
    public Path directory() {
        return filepath.normalize().getParent();
    }

    @Override
    public @Nullable MessageSource createSource(Path changedPath) {
        return changedPath.getFileName().equals(filepath.getFileName()) ? createMessageSource() : null;
    }

    private MessageSource createMessageSource() {
        return createMessageSource(PropertiesProcessor.load(filepath));
    }

    private MessageSource createMessageSource(Map<String, String> messageMap) {
        return new MessageSource(packageName, className, getterOption, messageMap);
    }
}
