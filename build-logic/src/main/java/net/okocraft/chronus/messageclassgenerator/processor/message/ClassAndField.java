package net.okocraft.chronus.messageclassgenerator.processor.message;

import net.okocraft.chronus.messageclassgenerator.context.Context;
import net.okocraft.chronus.messageclassgenerator.node.ClassNode;
import net.okocraft.chronus.messageclassgenerator.node.InstanceGetterNode;
import net.okocraft.chronus.messageclassgenerator.node.Node;
import net.okocraft.chronus.messageclassgenerator.node.RootNode;
import net.okocraft.chronus.messageclassgenerator.option.GetterOption;
import net.okocraft.chronus.messageclassgenerator.util.Naming;

import java.util.Objects;

final class ClassAndField {

    private final RootNode rootNode;
    private final Context context;

    ClassAndField(RootNode rootNode, Context context) {
        this.rootNode = rootNode;
        this.context = context;

        addGetter();
    }

    private void addGetter() {
        var getterType = context.getterOption().getterType;

        if (getterType == GetterOption.Type.GETTER_METHOD) {
            rootNode.addInstanceGetter(InstanceGetterNode.getter(context.className(), context.getterOption().methodName));
        } else if (getterType == GetterOption.Type.STATIC_CONSTANT) {
            rootNode.addInstanceGetter(InstanceGetterNode.staticConstant(context.className(), context.getterOption().fieldName));
        }
    }

    void processMessage(String fullKey, String message) {
        Objects.requireNonNull(fullKey);
        Objects.requireNonNull(message);

        String[] elements = MessageProcessor.KEY_SEPARATOR.split(fullKey);
        int i = 0;
        int last = elements.length - 1;

        ClassNode node = null;
        var parentKey = new StringBuilder();

        while (i < last) {
            if (i != 0) {
                parentKey.append(".");
            }

            parentKey.append(elements[i]);

            Node nextNode;
            var fieldName = Naming.toFieldName(elements[i]);

            if (node == null) {
                nextNode = rootNode.getOrCreateClassNode(fieldName, parentKey.toString());
            } else {
                nextNode = node.getOrCreateClassNode(fieldName, parentKey.toString());
            }

            if (nextNode.getClass() != ClassNode.class) {
                throw new IllegalStateException(fieldName + " is not ClassNode. Does the key already have a message?");
            }

            node = (ClassNode) nextNode;
            i++;
        }

        var fieldName = Naming.toFieldName(elements[last]);

        if (node == null) {
            if (rootNode.hasNode(fieldName)) {
                throw new IllegalStateException("The '" + fieldName + "' field already exists. Are there duplicate keys?");
            }

            rootNode.putFieldNode(fieldName, fullKey, message, false);
        } else {
            if (node.hasNode(fieldName)) {
                throw new IllegalStateException("The '" + fieldName + "' field already exists. Are there duplicate keys?");
            }

            node.putFieldNode(fieldName, fullKey, message);
        }
    }
}
