plugins {
    id("java")
    id("com.diffplug.spotless") version "7.2.1"
}

group = "org.viniciuspinatti"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

spotless {
    java {
        googleJavaFormat()
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.viniciuspinatti.CSV"
    }
}

tasks.test {
    useJUnitPlatform()
}