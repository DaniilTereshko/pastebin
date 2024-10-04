plugins {
    id("java")
    id("org.springframework.boot")
}


val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.findLibrary("spring-boot-dependencies").get()))
}