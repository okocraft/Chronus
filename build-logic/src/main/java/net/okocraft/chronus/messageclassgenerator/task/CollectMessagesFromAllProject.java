package net.okocraft.chronus.messageclassgenerator.task;

import kotlin.io.FilesKt;
import net.okocraft.chronus.messageclassgenerator.source.MessageSource;
import net.okocraft.chronus.messageclassgenerator.util.CacheDir;
import net.okocraft.chronus.messageclassgenerator.util.IndentingWriter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class CollectMessagesFromAllProject extends DefaultTask {

    @SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
    private String outputMessageFilename = "messages_en.properties";

    @TaskAction
    public void execute() throws IOException {
        var resourceDir = CacheDir.create(getProject()).resolve("generated-resources");

        if (Files.isDirectory(resourceDir)) {
            FilesKt.deleteRecursively(resourceDir.toFile());
        }

        var collectedMessages = new HashMap<String, String>(100, 1.0f);

        for (var project : getProject().getRootProject().getAllprojects()) {
            if (!(project.getTasks().findByName("generateMessageClass") instanceof GenerateMessageClass task) || task.messageSourceSupplier == null) {
                continue;
            }

            try (var stream = task.messageSourceSupplier.stream()) {
                stream.map(MessageSource::messageMap).forEach(collectedMessages::putAll);
            }
        }

        Files.createDirectories(resourceDir);

        var outputFile = resourceDir.resolve(outputMessageFilename);

        try (var writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            var indentingWriter = new IndentingWriter(writer);
            collectedMessages.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(entry -> indentingWriter.writeLine(entry.getKey() + "=" + entry.getValue().replace("\\", "\\\\")));
        }
    }
}
