import java.io.ByteArrayOutputStream

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)
val ci = findProperty("chronus.ci")?.toString()?.toBoolean() ?: false

dependencies {
    compileOnlyApi(libs.annotations)
    compileOnlyApi(libs.adventure)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
}

val javaVersion = JavaVersion.VERSION_17
val charset = Charsets.UTF_8

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks {
    build {
        if (ci) {
            buildDir = rootProject.buildDir.resolve(project.name)
        }
    }

    compileJava {
        options.encoding = charset.name()
        options.release.set(javaVersion.ordinal + 1)
    }

    processResources {
        filteringCharset = charset.name()
    }

    test {
        useJUnitPlatform()

        testLogging {
            events("passed", "skipped", "failed")
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
}

fun getLatestCommitHash(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--short=7", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}
