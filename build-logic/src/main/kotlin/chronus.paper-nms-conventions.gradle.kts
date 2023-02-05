plugins {
    id("chronus.common-conventions")
    id("io.papermc.paperweight.userdev")
}

dependencies {
    implementation(project(":chronus-api"))
    implementation(project(":chronus-core"))

    implementation(project(":chronus-platform-paper-shared"))
}
