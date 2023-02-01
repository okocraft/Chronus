package net.okocraft.chronus.messageclassgenerator.generator;

import net.okocraft.chronus.messageclassgenerator.context.Context;
import net.okocraft.chronus.messageclassgenerator.node.RootNode;
import net.okocraft.chronus.messageclassgenerator.util.IndentingWriter;
import net.okocraft.chronus.messageclassgenerator.util.Naming;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public final class ClassGenerator {

    public static void generate(Context context) {
        try {
            generate0(context);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generate0(Context context) throws IOException {
        var rootNode = new RootNode(context.packageName(), context.className());

        context.messageProcessor().accept(rootNode, context);

        var outputDir = Naming.resolvePackagePath(context.directory(), context.packageName());

        if (!Files.isDirectory(outputDir)) {
            Files.createDirectories(outputDir);
        }

        var outputFile = outputDir.resolve(context.className() + ".java");

        try (var writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            rootNode.write(new IndentingWriter(writer));
        }
    }

    private ClassGenerator() {
        throw new UnsupportedOperationException();
    }
}
