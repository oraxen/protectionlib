<h1 align="center">
  <br>
  <img src="/logo.svg?raw=true" alt="ProtectionLib" width="256">
  <br>
</h1>


<h4 align="center">❌ A unified library for WorldGuard, Towny, Factions and other protections API</h4>

# What's new?

ProtectionLib introduces two methods to check if a player can build or break blocks at a specific location.

```java
ProtectionLib.canBuild(player,location);
ProtectionLib.canBreak(player,location);
```

# Get ProtectionLib

1) Add the jitpack repo

```groovy
   maven { url 'https://jitpack.io' }
```

2) Shade ProtectionLib to your plugin

```groovy
   implementation 'com.github.oraxen:protectionlib:master-SNAPSHOT'
```

3) Init ProtectionLib in your plugin onEnable()

```groovy
   ProtectionLib.init();
```

4) Add softdepend to plugin.yml

```yaml
   softdepend: [ WorldGuard, Towny, Factions ]
```