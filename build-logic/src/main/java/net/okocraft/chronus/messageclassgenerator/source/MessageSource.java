package net.okocraft.chronus.messageclassgenerator.source;

import net.okocraft.chronus.messageclassgenerator.option.GetterOption;

import java.util.Map;

public record MessageSource(String packageName, String className, GetterOption getterOption, Map<String, String> messageMap) {
}
