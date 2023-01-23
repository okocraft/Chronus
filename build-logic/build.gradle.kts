repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.build.shadow)
    compileOnly(files(libs::class.java.protectionDomain.codeSource.location))
}

plugins {
    `kotlin-dsl` apply true
}
