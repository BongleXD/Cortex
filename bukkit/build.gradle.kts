plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "6.1.0"
    java
}

val spigotVer by extra("1.18-R0.1-SNAPSHOT")
val exposedVer by extra("0.37.3")

repositories {
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.exposed", "exposed-core", exposedVer)
    implementation("org.jetbrains.exposed", "exposed-dao", exposedVer)
    implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVer)
    compileOnly("org.spigotmc:spigot:$spigotVer")
    compileOnly("org.spigotmc:spigot-api:$spigotVer")
}

tasks.apply {
    named<Jar>("jar") {
        manifest {
            attributes(mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            ))
        }
    }
    shadowJar {
        archiveBaseName.set("${project.name}-${project.version}.jar")

    }
}