package io.th0rgal.protectionlib.compatibilities;

import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import p1xel.nobuildplus.API.NBPAPI;
import p1xel.nobuildplus.Flags;
import p1xel.nobuildplus.NoBuildPlus;

public class NoBuildPlusCompat extends ProtectionCompatibility {

    NoBuildPlus plugin = NoBuildPlus.getInstance();

    NBPAPI api = plugin.getAPI();

    public NoBuildPlusCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        return !api.canExecute(target.getWorld().getName(), Flags.build);
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        return !api.canExecute(target.getWorld().getName(), Flags.destroy);
    }

    @Override
    public boolean canInteract(Player player, Location target) {
        return !api.canExecute(target.getWorld().getName(), Flags.use);
    }

    @Override
    public boolean canUse(Player player, Location target) {
        return !api.canExecute(target.getWorld().getName(), Flags.use);
    }


}
