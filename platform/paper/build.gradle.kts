plugins {
    id("chronus.platform-conventions")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.platform.paper)

    rootProject.allprojects
        .filter { project -> project.name != name }
        .filter { project -> project.name.contains("platform-paper") }
        .forEach { implementation(it) }
}
