pluginManagement {
    includeBuild("build-logic")

    repositories {
        mavenCentral()
        gradlePluginPortal()

        maven {
            url = uri("https://repo.papermc.io/repository/maven-public/")
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
