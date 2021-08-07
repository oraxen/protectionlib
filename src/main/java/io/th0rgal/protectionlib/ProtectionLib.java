package io.th0rgal.protectionlib;

import io.th0rgal.protectionlib.compatibilities.FactionsUuidCompat;
import io.th0rgal.protectionlib.compatibilities.LandsCompat;
import io.th0rgal.protectionlib.compatibilities.TownyCompat;
import io.th0rgal.protectionlib.compatibilities.WorldGuardCompat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

public class ProtectionLib {

    private final static Set<ProtectionCompatibility> compatibilities = new HashSet<>();

    public static void init() {
        handleCompatibility("WorldGuard", WorldGuardCompat::new);
        handleCompatibility("Towny", TownyCompat::new);
        handleCompatibility("Factions", FactionsUuidCompat::new);
        handleCompatibility("Lands", LandsCompat::new);
    }

    public static boolean canBuild(Player player, Location target) {
        return compatibilities.stream().allMatch(compatibility -> compatibility.canBuild(player, target));
    }

    public static boolean canBreak(Player player, Location target) {
        return compatibilities.stream().allMatch(compatibility -> compatibility.canBreak(player, target));
    }

    private static void handleCompatibility(String pluginName, CompatibilityConstructor constructor) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin != null) {
            compatibilities.add(constructor.create(plugin));
        }
    }

    @FunctionalInterface
    private interface CompatibilityConstructor {
        ProtectionCompatibility create(Plugin plugin);
    }

}
