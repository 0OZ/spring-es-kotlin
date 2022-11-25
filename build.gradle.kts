import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
   id("org.springframework.boot") version "3.0.0"
   id("io.spring.dependency-management") version "1.1.0"
   kotlin("jvm") version "1.7.21"
   kotlin("plugin.spring") version "1.7.21"
}

group = "dev.zwei"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
   mavenCentral()
}

dependencies {
   implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
   implementation("org.jetbrains.kotlin:kotlin-reflect")
   implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
   testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
   kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "17"
   }
}

tasks.withType<Test> {
   useJUnitPlatform()
}
apply(plugin = "maven-publish")
apply(plugin = "org.jetbrains.dokka")
configure<PublishingExtension> {
   repositories {
      maven {
         name = "GitHubPackages"
         url = uri("https://maven.pkg.github.com/0oz/estools")
         credentials {
                    username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                    password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
         }
      }
   }
   publications {
      create<MavenPublication>("gpr") {
         groupId = "so.arctic"
         version = System.getenv("TAG") ?: "0.0.43"
         from(components["java"])
      }
   }
}