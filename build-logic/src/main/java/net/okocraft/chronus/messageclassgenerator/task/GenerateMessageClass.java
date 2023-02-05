package net.okocraft.chronus.messageclassgenerator.task;

import kotlin.io.FilesKt;
import net.okocraft.chronus.messageclassgenerator.context.Context;
import net.okocraft.chronus.messageclassgenerator.generator.ClassGenerator;
import net.okocraft.chronus.messageclassgenerator.processor.message.MessageProcessor;
import net.okocraft.chronus.messageclassgenerator.source.DirectorySource;
import net.okocraft.chronus.messageclassgenerator.source.MessageSource;
import net.okocraft.chronus.messageclassgenerator.source.MessageSourceSupplier;
import net.okocraft.chronus.messageclassgenerator.source.PropertiesFileSource;
import net.okocraft.chronus.messageclassgenerator.util.CacheDir;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.tasks.TaskAction;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class GenerateMessageClass extends DefaultTask {

    public static final Logger LOGGER = Logging.getLogger(GenerateMessageClass.class);

    public MessageSourceSupplier messageSourceSupplier;
    public String removingKeyFilter;

    public GenerateMessageClass() {
        setGroup("message generator");
    }

    @TaskAction
    public void execute() {
        if (this.messageSourceSupplier == null) {
            return;
        }

        var classDir = CacheDir.create(getProject()).resolve("generated-classes");

        if (Files.isDirectory(classDir)) {
            FilesKt.deleteRecursively(classDir.toFile());
        }

        try (var stream = this.messageSourceSupplier.stream()) {
            stream.forEach(source -> execute(source, true));
        }
    }

    public void execute(MessageSource messageSource, boolean throwError) {
        var classDir = CacheDir.create(getProject()).resolve("generated-classes");
        ClassGenerator.generate(createContext(messageSource, classDir, throwError));
    }

    private Context createContext(MessageSource source, Path classDir, boolean throwError) {
        return new Context(
                classDir,
                source.packageName(),
                source.className(),
                source.getterOption(),
                removingKeyFilter != null ? new RegexFilter(Pattern.compile(removingKeyFilter)) : null,
                source.messageMap(),
                ((rootNode, context1) -> MessageProcessor.constantField(rootNode, context1, throwError))
        );
    }

    private record RegexFilter(Pattern pattern) implements UnaryOperator<String> {
        @Override
        public String apply(String text) {
            return pattern.matcher(text).replaceAll("");
        }
    }

    public PropertiesFileSource fromPropertiesFile(Path file) {
        return new PropertiesFileSource(file);
    }

    public DirectorySource fromDirectory(Path directory) {
        return new DirectorySource(directory);
    }

    public PropertiesFileSource fromPropertiesFileInResourceDir(String filename) {
        return fromPropertiesFile(getResourceDir().resolve(filename));
    }

    public DirectorySource fromDirectoryInResourceDir(UnaryOperator<Path> resourceDirOperator) {
        return fromDirectory(resourceDirOperator.apply(getResourceDir()));
    }

    private Path getResourceDir() {
        return getProject().getLayout().getProjectDirectory().dir("src/main/resources").getAsFile().toPath();
    }
}
