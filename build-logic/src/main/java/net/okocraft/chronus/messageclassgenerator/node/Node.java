package net.okocraft.chronus.messageclassgenerator.node;

import net.okocraft.chronus.messageclassgenerator.util.IndentingWriter;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public sealed interface Node permits RootNode, ClassNode, FieldNode, InstanceGetterNode.GetterNode, InstanceGetterNode.StaticConstantNode {

    void write(IndentingWriter writer);

    static void writeNodes(@NotNull Collection<Node> nodes, @NotNull IndentingWriter writer) {
        int count = 0;
        int size = nodes.size();

        for (var node : nodes) {
            writer.writeEmptyLine();
            node.write(writer);

            if (++count == size) {
                writer.writeEmptyLine();
            }
        }
    }
}
