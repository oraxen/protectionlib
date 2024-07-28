plugins {
    id("java")
    id("idea")
    id("eclipse")
    id("maven-publish")
    id("org.ajoberstar.grgit.service") version "5.2.0"
}

val pluginVersion: String by project
group = "io.th0rgal"
version = pluginVersion

repositories {
    mavenCentral()
    //mavenLocal()
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://ci.ender.zone/plugin/repository/everything/")
    maven("https://repo.glaremasters.me/repository/towny/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.william278.net/releases")
}

dependencies {
    compileOnly("world.bentobox:bentobox:2.0.0-SNAPSHOT")
    compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.1.0-SNAPSHOT")
    compileOnly("com.palmergames.bukkit.towny:towny:0.100.1.0")
    compileOnly("com.massivecraft:Factions:1.6.9.5-U0.6.11") {
        exclude("org.kitteh:paste-gg-api")
        exclude("com.darkblade12:particleeffect")
        exclude("org.spongepowered:configurate-hocon")
        exclude("com.mojang:brigadier")
    }
    compileOnly("com.github.angeschossen:LandsAPI:7.0.2")
    compileOnly("com.github.WhipDevelopment:CrashClaim:75abe3b665")
    implementation(platform("com.intellectualsites.bom:bom-1.18.x:1.20"))
    compileOnly("com.plotsquared:PlotSquared-Core")
    compileOnly("com.plotsquared:PlotSquared-Bukkit")
    //compileOnly("com.github.TechFortress:GriefPrevention:16.18")
    implementation(files("libs/GriefPrevention-16.18.jar"))
    compileOnly("net.william278.huskclaims:huskclaims-bukkit:1.1.2")
    compileOnly("net.william278.husktowns:husktowns-bukkit:3.0.4")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    compileJava.get().dependsOn(clean)
}

publishing {
    val publishData = PublishData(project)
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = rootProject.name
            version = publishData.getVersion()

            from(components["java"])
        }
    }

    repositories {
        maven {
            authentication {
                credentials(PasswordCredentials::class) {
                    username = System.getenv("MAVEN_USERNAME") ?: (project.findProperty("oraxenUsername") as? String ?: "defaultUsername")
                    password = System.getenv("MAVEN_PASSWORD") ?: (project.findProperty("oraxenPassword") as? String ?: "defaultPassword")
                }
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }

            url = uri(publishData.getRepository())
            name = "oraxen"
            this.isAllowInsecureProtocol = true
        }
    }
}

class PublishData(private val project: Project) {
    var type: Type = getReleaseType()
    var hashLength: Int = 7

    private fun getReleaseType(): Type {
        val branch = getCheckedOutBranch()
        println("Branch: $branch")
        return when {
            branch.contentEquals("master") -> Type.RELEASE
            branch.contentEquals("develop") -> Type.SNAPSHOT
            else -> Type.DEV
        }
    }

    private fun getCheckedOutGitCommitHash(): String =
        System.getenv("GITHUB_SHA")?.substring(0, hashLength) ?: "local"

    private fun getCheckedOutBranch(): String =
        System.getenv("GITHUB_REF")?.replace("refs/heads/", "") ?: grgitService.service.get().grgit.branch.current().name

    fun getVersion(): String = getVersion(false)

    fun getVersion(appendCommit: Boolean): String =
        type.append(getVersionString(), appendCommit, getCheckedOutGitCommitHash())

    private fun getVersionString(): String =
        (rootProject.version as String).replace("-SNAPSHOT", "").replace("-DEV", "")

    fun getRepository(): String = type.repo

    enum class Type(private val append: String, val repo: String, private val addCommit: Boolean) {
        RELEASE("", "http://repo.oraxen.com:8080/releases/", false),
        DEV("-DEV", "http://repo.oraxen.com:8080/development/", true),
        SNAPSHOT("-SNAPSHOT", "http://repo.oraxen.com:8080/snapshots/", true);

        fun append(name: String, appendCommit: Boolean, commitHash: String): String =
            name.plus(append).plus(if (appendCommit && addCommit) "-".plus(commitHash) else "")
    }
}
