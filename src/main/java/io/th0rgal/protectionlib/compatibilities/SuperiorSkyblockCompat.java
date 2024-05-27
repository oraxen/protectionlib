package io.th0rgal.protectionlib.compatibilities;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class SuperiorSkyblockCompat extends ProtectionCompatibility {
    public SuperiorSkyblockCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        return hasPermission(player, target, "BUILD");
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        return hasPermission(player, target, "BREAK");
    }

    @Override
    public boolean canInteract(Player player, Location target) {
        return hasPermission(player, target, "INTERACT");
    }

    @Override
    public boolean canUse(Player player, Location target) {
        return hasPermission(player, target, "USE");
    }

    private boolean hasPermission(@NotNull Player player, @NotNull Location location, @NotNull String privilege) {
        Island island = SuperiorSkyblockAPI.getIslandAt(location);
        if (island == null) return true;
        return island.hasPermission(SuperiorSkyblockAPI.getPlayer(player), IslandPrivilege.getByName(privilege));
    }
}
