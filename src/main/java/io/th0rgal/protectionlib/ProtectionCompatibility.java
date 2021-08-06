package io.th0rgal.protectionlib;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class ProtectionCompatibility {

    private final Plugin plugin;

    public ProtectionCompatibility(Plugin plugin) {
        this.plugin = plugin;
    }

    public abstract boolean canBuild(Player player, Location target);

    public abstract boolean canBreak(Player player, Location target);

    public Plugin getPlugin() {
        return plugin;
    }
}
