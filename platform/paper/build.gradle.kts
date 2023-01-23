plugins {
    id ("chronus.platform-conventions")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.platform.paper)
}
