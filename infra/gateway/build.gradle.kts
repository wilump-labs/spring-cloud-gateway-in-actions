tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("io.r2dbc:r2dbc-h2")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}