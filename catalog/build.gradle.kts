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

extra["springCloudVersion"] = "2023.0.1"

dependencies {

	//Actuator
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	//Cloud bus
	implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp")

	//Config and bootstrap
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")

	//distribute tracing with Micrometer and Zipkin
	implementation("io.micrometer:micrometer-observation:1.12.5")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
	implementation("io.zipkin.reporter2:zipkin-reporter-brave")
	testImplementation("io.github.openfeign:feign-micrometer:13.2.1")

	//postgres and data jpa and model mapper
//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	runtimeOnly("org.postgresql:postgresql")
	implementation("org.modelmapper:modelmapper:3.2.0")
	
	//actuator
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	//Eureka client
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

	//devtools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	//web starter
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//kotlin
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
