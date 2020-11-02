plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("kapt") version "1.4.10"
    id("net.mamoe.mirai-console") version "1.0-RC-1"
}

group = "tech.logex"
version = "1.0"

repositories {
    jcenter()
}

dependencies {
    val autoService = "1.0-rc7"
    kapt("com.google.auto.service", "auto-service", autoService)
    compileOnly("com.google.auto.service", "auto-service-annotations", autoService)

    compileOnly("net.mamoe:mirai-core:1.3.2")
    compileOnly("net.mamoe:mirai-console:1.0-RC-1")

    implementation("redis.clients", "jedis", "3.3.0")

    testImplementation("net.mamoe:mirai-console-terminal:1.0-RC-1")
}
