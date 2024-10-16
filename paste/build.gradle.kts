plugins {
    id("project.conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")//TODO swagger dep
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.liquibase:liquibase-core")
    implementation("io.minio:minio:8.5.12")
    runtimeOnly("org.postgresql:postgresql")
}