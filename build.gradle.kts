plugins {
    id("java")
    id("idea")
    id("eclipse")
    id("maven-publish")
}

val pluginVersion: String by project
group = "io.th0rgal"
version = pluginVersion

repositories {
    mavenCentral()
    //mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://ci.ender.zone/plugin/repository/everything/")
    maven("https://repo.glaremasters.me/repository/towny/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.1.0-SNAPSHOT")
    compileOnly("com.palmergames.bukkit.towny:towny:0.99.2.0")
    compileOnly("com.massivecraft:Factions:1.6.9.5-U0.6.11") {
        exclude("org.kitteh:paste-gg-api")
        exclude("com.darkblade12:particleeffect")
        exclude("org.spongepowered:configurate-hocon")
        exclude("com.mojang:brigadier")
    }
    compileOnly("com.github.angeschossen:LandsAPI:6.29.12")
    compileOnly("com.github.WhipDevelopment:CrashClaim:75abe3b665")
    implementation(platform("com.intellectualsites.bom:bom-1.18.x:1.20"))
    compileOnly("com.plotsquared:PlotSquared-Core")
    compileOnly("com.plotsquared:PlotSquared-Bukkit")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    compileJava.get().dependsOn(clean)
}

publishing {
    publications {
        register<MavenPublication>("maven") {
            from(components.getByName("java"))
        }
    }
}
