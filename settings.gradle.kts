pluginManagement {
    includeBuild("build-logic")
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
