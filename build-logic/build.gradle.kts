plugins {
    `kotlin-dsl`
    kotlin("jvm") version embeddedKotlinVersion
}

repositories {
    mavenCentral()
    gradlePluginPortal()

    maven {
        name = "PaperMC"
        url = uri("https://repo.papermc.io/repository/maven-public/")
        content {
            includeGroupByRegex("io\\.papermc\\..*")
        }
    }
}

dependencies {
    implementation(libs.build.shadow)
    implementation(libs.build.paperweight.userdev)
    compileOnly(files(libs::class.java.protectionDomain.codeSource.location))
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
