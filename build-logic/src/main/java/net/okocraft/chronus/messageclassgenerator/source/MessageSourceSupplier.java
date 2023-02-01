package net.okocraft.chronus.messageclassgenerator.source;

import java.util.stream.Stream;

public interface MessageSourceSupplier {

    Stream<MessageSource> stream();

}
