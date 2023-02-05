package net.okocraft.chronus.messageclassgenerator.util;

import org.gradle.api.Project;

import java.nio.file.Path;

public final class CacheDir {

    public static Path create(Project project) {
        return project.getLayout().getProjectDirectory().dir(".gradle/caches").dir("message-class-generator").getAsFile().toPath();
    }

}
