package io.th0rgal.protectionlib;

import io.th0rgal.protectionlib.compatibilities.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class ProtectionLib {

    private final static Set<ProtectionCompatibility> compatibilities = new HashSet<>();
    static boolean debug = false;

    @SuppressWarnings("Convert2MethodRef")
    public static void init(JavaPlugin plugin) {
        handleCompatibility("WorldGuard", plugin, (m, p) -> new WorldGuardCompat(m, p));
        handleCompatibility("Towny", plugin, (m, p) -> new TownyCompat(m, p));
        handleCompatibility("Factions", plugin, (m, p) -> new FactionsUuidCompat(m, p));
        handleCompatibility("Lands", plugin, (m, p) -> new LandsCompat(m, p));
        handleCompatibility("PlotSquared", plugin, (m, p) -> new PlotSquaredCompat(m, p));
        handleCompatibility("CrashClaim", plugin, (m, p) -> new CrashClaimCompat(m, p));
        handleCompatibility("GriefPrevention", plugin, (m, p) -> new GriefPreventionCompat(m, p));
        handleCompatibility("HuskClaims", plugin, (m, p) -> new HuskClaimCompat(m, p));
        handleCompatibility("BentoBox", plugin, (m, p) -> new BentoBoxCompat(m, p));
    }

    public static void setDebug(boolean debug) {
        ProtectionLib.debug = debug;
    }

    public static boolean getDebug() {
        return debug;
    }

    public static boolean canBuild(Player player, Location target) {
        try {
            return compatibilities.stream().allMatch(compatibility -> compatibility.canBuild(player, target));
        } catch (Exception e) {
            if (debug) e.printStackTrace();
            return false;
        }
    }

    public static boolean canBreak(Player player, Location target) {
        try {
            return compatibilities.stream().allMatch(compatibility -> compatibility.canBreak(player, target));
        } catch (Exception e) {
            if (debug) e.printStackTrace();
            return false;
        }
    }

    public static boolean canInteract(Player player, Location target) {
        try {
            return compatibilities.stream().allMatch(compatibility -> compatibility.canInteract(player, target));
        } catch (Exception e) {
            if (debug) e.printStackTrace();
            return false;
        }
    }

    public static boolean canUse(Player player, Location target) {
        try {
            return compatibilities.stream().allMatch(compatibility -> compatibility.canUse(player, target));
        } catch (Exception e) {
            if (debug) e.printStackTrace();
            return false;
        }
    }

    private static void handleCompatibility(String pluginName, JavaPlugin mainPlugin, CompatibilityConstructor constructor) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin != null) {
            if (pluginName.equals("Factions") && !checkFactionsCompat()) return;
            compatibilities.add(constructor.create(mainPlugin, plugin));
        }
    }

    @FunctionalInterface
    private interface CompatibilityConstructor {
        ProtectionCompatibility create(JavaPlugin mainPlugin, Plugin plugin);
    }

    private static boolean checkFactionsCompat() {
        try {
            Class.forName("com.massivecraft.factions.perms.PermissibleActions");
            return true;
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().warning("It seems a Factions plugin is installed, but it is not FactionsUUID.");
            Bukkit.getLogger().warning("ProtectionLib will not be able to handle Factions protection.");
            return false;
        }
    }

}
