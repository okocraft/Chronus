plugins {
    id("chronus.platform-conventions")
    alias(libs.plugins.paperweight.userdev)
}

dependencies {
    paperDevBundle(libs.versions.paper.get())
}
