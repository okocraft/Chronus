import net.okocraft.chronus.messageclassgenerator.task.CollectMessagesFromAllProject

plugins {
    `java-library`
    id("com.github.johnrengelman.shadow")
    id("chronus.common-conventions")
}

dependencies {
    implementation(project(":chronus-api"))
    implementation(project(":chronus-core"))
}

java {
    sourceSets.main {
        resources {
            srcDirs(file(".gradle/caches/message-class-generator/generated-resources"))
        }
    }
}

tasks {
    val collectMessageTask = create<CollectMessagesFromAllProject>("collectMessagesFromAllProject")

    processResources {
        dependsOn(collectMessageTask)

        filesMatching(listOf("plugin.yml")) {
            expand("projectVersion" to project.version)
        }
    }

    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        val projectName = project.name
        val index = projectName.lastIndexOf('-')

        if (index == -1) {
            return@shadowJar
        }

        val rawPlatformName = projectName.substring(index + 1)
        val platformName = rawPlatformName[0].toUpperCase() + rawPlatformName.substring(1)

        archiveFileName.set("Chronus-$platformName-${project.version}.jar")

        minimize {
        }

        val groupId = project.group
        relocate("org.jetbrains", "$groupId.libs.jetbrains")
        relocate("org.intellij", "$groupId.libs.intellij")
        relocate("com.github.siroshun09.event4j", "$groupId.libs.event4j")
    }
}
