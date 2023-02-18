import net.okocraft.chronus.messageclassgenerator.task.CollectMessagesFromAllProject
import java.io.ByteArrayOutputStream
import java.nio.file.Files

val ci = findProperty("chronus.ci")?.toString()?.toBoolean() ?: false

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

        if (ci) {
            dependsOn("copyToUploadingDirectory")
        }
    }

    jar {
        var version = project.version.toString()

        if (ci && version.endsWith("-SNAPSHOT")) { // for development build in CI
            version = "${project.version}-git-${getLatestCommitHash()}"
        }

        manifest {
            attributes(
                "Implementation-Version" to version
            )
        }
    }

    shadowJar {
        val projectName = project.name
        val index = projectName.lastIndexOf('-')

        if (index == -1) {
            return@shadowJar
        }

        val rawPlatformName = projectName.substring(index + 1)
        val platformName = rawPlatformName[0].uppercase() + rawPlatformName.substring(1)

        archiveFileName.set("Chronus-$platformName-${project.version}.jar")

        minimize {
        }

        val groupId = project.group
        relocate("com.github.siroshun09.event4j", "$groupId.libs.event4j")
    }

    create("copyToUploadingDirectory") {
        dependsOn(shadowJar)

        doFirst {
            val jarFilepath = shadowJar.get().archiveFile.get().asFile.toPath()
            val targetDir = rootProject.buildDir.toPath().resolve("ci-upload")

            if (!Files.isDirectory(targetDir)) {
                Files.createDirectories(targetDir)
            }

            val targetFilepath = targetDir.resolve(jarFilepath.fileName)

            Files.deleteIfExists(targetFilepath)
            Files.copy(jarFilepath, targetFilepath)
        }
    }
}

fun getLatestCommitHash(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--short=7", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}
