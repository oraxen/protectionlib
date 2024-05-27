<h1 align="center">
  <br>
  <img src="/logo.svg?raw=true" alt="ProtectionLib" width="256">
  <br>
</h1>


<h4 align="center">
    ❌ A unified library for WorldGuard, Towny, Factions and other protections API</h4>

<p align="center">
    <a href="https://www.codefactor.io/repository/github/oraxen/protectionlib">
        <img src="https://www.codefactor.io/repository/github/oraxen/protectionlib/badge" alt="CodeFactor" />
    </a>
</p>


# What's new?

ProtectionLib introduces four methods to check if a player can build, break or interact with a block at a specific location.

```java
ProtectionLib.canBuild(player,location);
ProtectionLib.canBreak(player,location);
ProtectionLib.canInteract(player,location);
ProtectionLib.canUse(player,location);
```

# Get ProtectionLib

1) Add the jitpack repo

```groovy
   maven { url 'https://jitpack.io' }
```

2) Shade ProtectionLib to your plugin

```groovy
   implementation 'com.github.oraxen:protectionlib:RELEASE_VERSION'
```

3) Init ProtectionLib in your plugin onEnable()

```java
   ProtectionLib.init(this);
```

4) Add softdepend to plugin.yml

```yaml
   softdepend: [ WorldGuard, Towny, Factions, Lands, PlotSquared, CrashClaim, HuskTowns ]
```

# Supported plugins
- [WorldGuard](https://dev.bukkit.org/projects/worldguard) (Free)
- [Towny](https://www.spigotmc.org/resources/towny-advanced.72694/) (Free)
- [FactionsUUID](https://www.spigotmc.org/resources/factionsuuid.1035/) (Paid)
- [Lands](https://www.spigotmc.org/resources/lands-land-claim-plugin-grief-prevention-protection-gui-management-nations-wars-1-17-support.53313/) (Paid)
- [PlotSquared](https://www.spigotmc.org/resources/plotsquared.1177/) (Free)
- [CrashClaim](https://www.spigotmc.org/resources/crashclaim-claiming-plugin.94037/) (Paid)
- [HuskTowns](https://www.spigotmc.org/resources/92672) (Paid)
