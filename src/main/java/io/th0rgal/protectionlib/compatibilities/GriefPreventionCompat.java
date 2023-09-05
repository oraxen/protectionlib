package io.th0rgal.protectionlib.compatibilities;

import io.th0rgal.protectionlib.ProtectionCompatibility;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class GriefPreventionCompat extends ProtectionCompatibility {

    private static GriefPrevention griefPrevention;

    public GriefPreventionCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
        griefPrevention = (GriefPrevention) Bukkit.getPluginManager().getPlugin("GriefPrevention");
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        return griefPrevention.allowBuild(player, target) == null;
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        return griefPrevention.allowBreak(player, target.getBlock(), target) == null;
    }

    @Override
    public boolean canInteract(Player player, Location target) {
        return griefPrevention.allowBuild(player, target) == null;
    }

    @Override
    public boolean canUse(Player player, Location target) {
        return griefPrevention.allowBuild(player, target) == null;
    }
}
