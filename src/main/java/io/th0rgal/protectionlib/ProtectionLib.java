package io.th0rgal.protectionlib;

import io.th0rgal.protectionlib.compatibilities.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class ProtectionLib {

    private final static Set<ProtectionCompatibility> compatibilities = new HashSet<>();

    @SuppressWarnings("Convert2MethodRef")
    public static void init(JavaPlugin plugin) {
        handleCompatibility("WorldGuard", plugin, (m, p) -> new WorldGuardCompat(m, p));
        handleCompatibility("Towny", plugin, (m, p) -> new TownyCompat(m, p));
        handleCompatibility("Factions", plugin, (m, p) -> new FactionsUuidCompat(m, p));
        handleCompatibility("Lands", plugin, (m, p) -> new LandsCompat(m, p));
        handleCompatibility("PlotSquared", plugin, (m, p) -> new PlotSquaredCompat(m, p));
        handleCompatibility("CrashClaim", plugin, (m, p) -> new CrashClaimCompat(m, p));


        handleCompatibility("Citizens",plugin,(m,p) -> new CitizensCompat(m,p));
    }

    public static boolean canBuild(Player player, Location target) {
        return compatibilities.stream().allMatch(compatibility -> compatibility.canBuild(player, target));
    }

    public static boolean canBreak(Player player, Location target) {
        return compatibilities.stream().allMatch(compatibility -> compatibility.canBreak(player, target));
    }

    public static boolean canInteract(Player player, Location target) {
        return compatibilities.stream().allMatch(compatibility -> compatibility.canInteract(player, target));
    }

    public static boolean canUse(Player player, Location target) {
        return compatibilities.stream().allMatch(compatibility -> compatibility.canUse(player, target));
    }


    public static boolean isNPC(Entity npc){
        return compatibilities.stream().anyMatch(compatibility -> compatibility.isNPC(npc));
    }


    private static void handleCompatibility(String pluginName, JavaPlugin mainPlugin, CompatibilityConstructor constructor) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin != null) {
            compatibilities.add(constructor.create(mainPlugin, plugin));
        }
    }

    @FunctionalInterface
    private interface CompatibilityConstructor {
        ProtectionCompatibility create(JavaPlugin mainPlugin, Plugin plugin);
    }

}
