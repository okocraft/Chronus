package net.okocraft.chronus.messageclassgenerator.processor.message;

import net.okocraft.chronus.messageclassgenerator.context.Context;
import net.okocraft.chronus.messageclassgenerator.node.RootNode;
import net.okocraft.chronus.messageclassgenerator.util.Naming;

import java.util.Objects;

final class ConstantField {

    private final RootNode rootNode;
    private final Context context;

    ConstantField(RootNode rootNode, Context context) {
        this.rootNode = rootNode;
        this.context = context;
    }

    void processMessage(String fullKey, String message) {
        Objects.requireNonNull(fullKey);
        Objects.requireNonNull(message);

        var filteredKey = context.fieldNameFilter().apply(fullKey);
        var fieldName = Naming.toConstantFieldName(filteredKey);

        if (rootNode.hasNode(fieldName)) {
            throw new IllegalStateException(fieldName + " already exists. Are there any duplicate keys after filtering?");
        }

        rootNode.putFieldNode(fieldName, fullKey, message, true);
    }
}
