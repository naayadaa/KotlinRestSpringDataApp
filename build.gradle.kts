import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "9.3.0"
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.spring") version "1.3.71"
    kotlin("plugin.jpa") version "1.3.71"
    kotlin("kapt") version "1.3.61"
    id("io.freefair.aspectj.post-compile-weaving") version "5.3.0"
}

apply(plugin = "idea")
apply(plugin = "kotlin")
apply(plugin = "io.freefair.aspectj.post-compile-weaving")

group = "com.naayadaa"
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenLocal()
    jcenter()
}

val kotlinVersion = "1.3.71"
val springBootVersion = "2.3.4.RELEASE"
val mapstructVersion = "1.4.1.Final"
val jacksonVersion = "2.11.2"
val coroutinesReactorVersion = "1.3.8"

dependencies {

    implementation("org.aspectj:aspectjtools:1.9.2")

    compileOnly("org.aspectj:aspectjrt:1.9.4")
    runtimeOnly("org.aspectj:aspectjweaver:1.9.4")

    implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("com.h2database:h2:1.4.200")

    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutinesReactorVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("commons-io:commons-io:2.8.0")

    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    dependsOn("ktlintCheck")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        kotlinOptions.jvmTarget = "1.8"
    }
}

allOpen {
    annotation("javax.persistence.Entity")
}

ktlint {
    verbose.set(true)
    ignoreFailures.set(false)
    enableExperimentalRules.set(true)
    disabledRules.addAll("no-consecutive-blank-lines")
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}
