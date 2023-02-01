package net.okocraft.chronus.messageclassgenerator.processor.message;

import net.okocraft.chronus.messageclassgenerator.context.Context;
import net.okocraft.chronus.messageclassgenerator.node.RootNode;
import net.okocraft.chronus.messageclassgenerator.task.GenerateMessageClass;

import java.util.function.BiConsumer;
import java.util.regex.Pattern;

public interface MessageProcessor {

    Pattern KEY_SEPARATOR = Pattern.compile(".", Pattern.LITERAL);

    static void classAndFields(RootNode rootNode, Context context, boolean throwError) {
        processMessage(rootNode, context, new ClassAndField(rootNode, context)::processMessage, throwError);
    }

    static void constantField(RootNode rootNode, Context context, boolean throwError) {
        processMessage(rootNode, context, new ConstantField(rootNode, context)::processMessage, throwError);
    }

    static void processMessage(RootNode rootNode, Context context, BiConsumer<String, String> processor, boolean throwError) {
        try {
            context.messageMap().forEach(processor);
        } catch (IllegalStateException e) {
            if (throwError) {
                throw e;
            } else {
                GenerateMessageClass.LOGGER.error(e.getMessage());
                rootNode.clear();
            }
        }
    }
}
