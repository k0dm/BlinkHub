import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
}

group = "com.bugbender.ecommerce"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

tasks.getByName<Jar>("jar") {
	enabled = false
}

extra["springCloudVersion"] = "2023.0.1"

dependencies {

	//distribute tracing with Micrometer and Zipkin
	implementation("io.micrometer:micrometer-observation:1.12.5")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
	implementation("io.zipkin.reporter2:zipkin-reporter-brave")
	testImplementation("io.github.openfeign:feign-micrometer:13.2.1")

	//Resilience4j
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("io.github.resilience4j:resilience4j-reactor")
	implementation("io.github.resilience4j:resilience4j-spring-boot3")
//	implementation("io.github.resilience4j:resilience4j-all") // Optional, only required when you want to use the Decorators class
//	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")

	//OpenFeign
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

	//Actuator
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	//Cloud bus
	implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp")

	//Config and bootstrap
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")

	//Security
	implementation("org.springframework.boot:spring-boot-starter-security")

	//JWT
	implementation("io.jsonwebtoken:jjwt-api:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

	//postgres and data jpa and model mapper
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.modelmapper:modelmapper:3.2.0")

	//Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	//Starter web
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//Eureka client
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

	//Devtools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	//Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
