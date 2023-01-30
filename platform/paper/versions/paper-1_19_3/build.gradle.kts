plugins {
    id("chronus.platform-conventions")
    // This will show as an error on the IDE, but can be compiled successfully.
    // See https://github.com/gradle/gradle/issues/22797
    alias(libs.plugins.paperweight.userdev)
}

dependencies {
    paperDevBundle(libs.versions.paper.get())
}
