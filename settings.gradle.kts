pluginManagement {
    includeBuild("build-logic")

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
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "chronus"

val chronusPrefix = rootProject.name

sequenceOf(
    "api",
    "core"
).forEach {
    include("$chronusPrefix-$it")
    project(":$chronusPrefix-$it").projectDir = file(it)
}

sequenceOf(
    "paper"
).forEach {
    include("$chronusPrefix-platform-$it")
    project(":$chronusPrefix-platform-$it").projectDir = file("./platform/$it")
}

sequenceOf(
    "1_19_3"
).forEach {
    include("$chronusPrefix-platform-paper-$it")
    project(":$chronusPrefix-platform-paper-$it").projectDir = file("./platform/paper/versions/paper-$it")
}
