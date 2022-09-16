import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.12"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.10"
	kotlin("plugin.spring") version "1.5.10"
	kotlin("plugin.jpa") version "1.5.10"
	id("maven-publish")
	id("idea")
	id("jacoco")
//	id("com.gorylenko.gradle-git-properties") version "2.4.0-rc2"
	// gradle release and version management plugin
	id("pl.allegro.tech.build.axion-release") version "1.13.6"
}

version = scmVersion.version // will get new version for each release
java.sourceCompatibility = JavaVersion.VERSION_11

//group = "com.example"
//version = "0.0.1-SNAPSHOT"
//java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()

}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// swagger dependencies
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	implementation("io.springfox:springfox-boot-starter:3.0.0")

	// web client
	implementation("org.springframework.boot:spring-boot-starter-webflux:2.5.0")

	// testcases
	testImplementation("io.mockk:mockk:1.11.0")

	// webpages
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-devtools")

	// Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")
	// fixing log4j security vulnerability
	implementation("org.apache.logging.log4j:log4j-api:2.16.0")
	implementation("org.apache.logging.log4j:log4j-core:2.16.0")
	implementation("org.apache.logging.log4j:log4j-to-slf4j:2.16.0")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "16"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
