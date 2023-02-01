plugins {
    id ("chronus.common-conventions")
    id ("chronus.message-class-generator")
}

dependencies {
    val apiProject = project(":chronus-api")
    implementation(apiProject)
    implementation(libs.event4j)
}
