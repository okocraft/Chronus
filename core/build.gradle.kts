plugins {
    id ("chronus.common-conventions")
}

dependencies {
    implementation(projects.chronusApi)
    implementation(libs.event4j)
}
