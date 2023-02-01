plugins {
    id("chronus.common-conventions")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.platform.paper)

    implementation(project(":chronus-api"))
    implementation(project(":chronus-core"))
}
