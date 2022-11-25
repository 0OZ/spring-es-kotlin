import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
   id("org.springframework.boot") version "3.0.0"
   id("io.spring.dependency-management") version "1.1.0"
   kotlin("jvm") version "1.7.21"
   kotlin("plugin.spring") version "1.7.21"
   `maven-publish`
}

group = "so.arctic"
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


publishing {
   repositories {
      maven {
         name = "GitHubPackages"
         url = uri("https://maven.pkg.github.com/0oz/spring-es-kotlin")
         credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
         }
      }
   }
   publications {
      register<MavenPublication>("gpr") {
         groupId = "so.arctic"
         version = System.getenv("TAG") ?: "0.0.43"
         from(components["java"])
      }
   }
}
