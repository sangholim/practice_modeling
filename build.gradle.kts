import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("plugin.jpa") version "1.6.10"
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    implementation("io.projectreactor.rabbitmq:reactor-rabbitmq:1.5.4")
    implementation ("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-rabbit:3.2.3")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.springframework.data:spring-data-mongodb")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation ("org.testcontainers:testcontainers-bom:1.17.1")

    testImplementation ("io.kotest:kotest-runner-junit5:5.4.2")
    testImplementation ("io.kotest:kotest-assertions-core:5.4.2")
    testImplementation ("io.kotest:kotest-property:5.4.2")
    testImplementation ("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    testImplementation ("io.kotest.extensions:kotest-extensions-testcontainers:1.3.4")
    testImplementation ("io.kotest:kotest-runner-junit5-jvm:5.4.2")

    testImplementation("io.mockk:mockk:1.12.7")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("org.testcontainers:mongodb:1.17.1")
    testImplementation ("org.testcontainers:rabbitmq:1.17.1")
    testImplementation ("org.testcontainers:testcontainers:1.16.3")
    testImplementation("org.springframework.cloud:spring-cloud-stream:3.2.3") {
        artifact {
            name = "spring-cloud-stream"
            extension = "jar"
            type ="test-jar"
            classifier = "test-binder"
        }
    }

}

tasks.register("runVendor") {
    doFirst {
        sourceSets {
            this.main {
                this.java {
                    this.srcDir("src/main/kotlin")
                    this.srcDir("src/test/kotlin")
                }
            }
        }
        javaexec {
            this.classpath = sourceSets.main.get().runtimeClasspath
            this.main = "vendor1.VendorApplicationKt"
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
springBoot{
    this.mainClass.set("product1.ProductApplicationKt")
}