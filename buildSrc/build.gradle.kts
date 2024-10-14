plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.gradle.plugin)
    implementation(libs.lombok.plugin)
}