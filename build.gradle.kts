plugins {
    id("java")
    id("application")
}

group = "com.github.orions29.ekspedisi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Biar Bisa Jalan di Semua Mesin
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

//    Buat .env files yang nanti dibutuhkan
    // Source: https://mvnrepository.com/artifact/io.github.cdimascio/dotenv-java
    implementation("io.github.cdimascio:dotenv-java:3.2.0")

// Logging sama SLF4J
    // Source: https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.18")
    // Source: https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.32")

// Database
    // Source: https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.8")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Main-Class" to "com.github.orions29.ekspedisi.Main"
        )
    }

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

application {
    mainClass.set("com.github.orions29.ekspedisi.Main")
}


tasks.test {
    useJUnitPlatform()
}