package net.okocraft.chronus.messageclassgenerator.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;

public class IndentingWriter {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    private final Writer writer;
    private int depth = 0;

    public IndentingWriter(@NotNull Writer writer) {
        this.writer = writer;
    }

    public void increaseIndent() {
        depth++;
    }

    public void decreaseIndent() {
        depth--;
    }

    public void writeLine(@NotNull String line) {
        writeIndent();
        write(line);
        write(LINE_SEPARATOR);
    }

    public void writeEmptyLine() {
        write(LINE_SEPARATOR);
    }

    private void writeIndent() {
        if (depth == 0) {
            return;
        }

        for (int i = 1; i <= depth; i++) {
            write("    ");
        }
    }

    private void write(@NotNull String str) {
        try {
            writer.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
