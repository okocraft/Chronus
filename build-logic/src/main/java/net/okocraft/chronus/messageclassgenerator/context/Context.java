package net.okocraft.chronus.messageclassgenerator.context;

import net.okocraft.chronus.messageclassgenerator.node.RootNode;
import net.okocraft.chronus.messageclassgenerator.option.GetterOption;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

public record Context(Path directory, String packageName, String className,
                      GetterOption getterOption,
                      UnaryOperator<String> fieldNameFilter,
                      Map<String, String> messageMap,
                      BiConsumer<RootNode, Context> messageProcessor) {

    public Context(Path directory, String packageName, String className,
                   GetterOption getterOption,
                   UnaryOperator<String> fieldNameFilter,
                   Map<String, String> messageMap,
                   BiConsumer<RootNode, Context> messageProcessor) {
        this.directory = Objects.requireNonNull(directory);
        this.packageName = Objects.requireNonNull(packageName);
        this.className = Objects.requireNonNull(className);
        this.getterOption = copy(getterOption); // Copying makes it effectively immutable.
        this.fieldNameFilter = Objects.requireNonNullElse(fieldNameFilter, UnaryOperator.identity());
        this.messageMap = Objects.requireNonNull(messageMap); // This is not copied here, as it is the result of reading the file.
        this.messageProcessor = Objects.requireNonNull(messageProcessor);
    }

    private static GetterOption copy(GetterOption option) {
        var copied = new GetterOption();
        copied.fieldName = Objects.requireNonNullElse(option.fieldName, "MESSAGES");
        copied.methodName = Objects.requireNonNullElse(option.methodName, "get");
        copied.getterType = Objects.requireNonNullElse(option.getterType, GetterOption.Type.GETTER_METHOD);
        return copied;
    }
}
