import net.okocraft.chronus.messageclassgenerator.task.GenerateMessageClass

plugins {
    `java-library`
    idea
    id("chronus.common-conventions")
}

java {
    sourceSets.main {
        java {
            srcDirs(file(".gradle/caches/message-class-generator/generated-classes"))
        }

        resources {
            exclude {
                return@exclude it.isDirectory && it.path.startsWith("messages")
            }
        }
    }
}

idea {
    module {
        val path = file(".gradle/caches/message-class-generator/generated-classes")
        sourceDirs.add(path)
        generatedSourceDirs.add(path)
    }
}

tasks {
    build {
        dependsOn("generateMessageClass")
    }

    create<GenerateMessageClass>("generateMessageClass") {
        messageSourceSupplier =
            fromDirectoryInResourceDir { path -> path.resolve("messages") }.apply {
                rootPackageName = "net.okocraft.chronus.generated.messages"
            }
        removingKeyFilter = "^chronus\\..*?\\."
    }
}
