plugins {
    java
    id("org.springframework.boot")
    id("io.freefair.lombok")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "com.tdi"
version = "1.0"

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.findLibrary("spring-boot-dependencies").get()))
}