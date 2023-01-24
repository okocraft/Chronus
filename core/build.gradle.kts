plugins {
    id ("chronus.common-conventions")
}

dependencies {
    val apiProject = project(":chronus-api")
    implementation(apiProject)
    implementation(libs.event4j)
}
