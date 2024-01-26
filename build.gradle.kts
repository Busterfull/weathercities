plugins {
	java
	id("org.springframework.boot") version "2.7.18"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.example"
version = "0.0.1-SNAPSHOT"

springBoot {
	mainClass = "ru.weathercities.WeatherCitiesApplication"
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-dependencies:2.5.3")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("jakarta.persistence:jakarta.persistence-api:2.2.3")
	implementation("org.hibernate:hibernate-core:5.6.15.Final")
	implementation("org.hibernate.validator:hibernate-validator:6.2.1.Final")
	implementation("org.hibernate.common:hibernate-commons-annotations:6.0.6.Final")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("org.apache.commons:commons-lang3:3.14.0")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
}

