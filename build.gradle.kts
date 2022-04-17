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
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.data:spring-data-mongodb")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation ("org.testcontainers:testcontainers-bom:1.16.3")

    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation ("org.testcontainers:mongodb:1.16.3")
    testImplementation ("org.testcontainers:testcontainers:1.16.3")


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

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    this.main = "product1.ProductApplicationKt"
}