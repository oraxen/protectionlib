package io.th0rgal.protectionlib.compatibilities;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldGuardCompat extends ProtectionCompatibility {

    private final RegionContainer regionContainer;
    private final WorldGuard worldGuard;

    public WorldGuardCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
        worldGuard = WorldGuard.getInstance();
        regionContainer = worldGuard.getPlatform().getRegionContainer();
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    @Override
    public boolean canBuild(Player player, Location target) {
        LocalPlayer localPlayer = ((WorldGuardPlugin) getPlugin()).wrapPlayer(player);
        return regionContainer.createQuery().testBuild(BukkitAdapter.adapt(target), localPlayer, Flags.BLOCK_PLACE)
                || worldGuard.getPlatform()
                .getSessionManager()
                .hasBypass(localPlayer, BukkitAdapter.adapt(player.getWorld())
                );
    }

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    @Override
    public boolean canBreak(Player player, Location target) {
        LocalPlayer localPlayer = ((WorldGuardPlugin) getPlugin()).wrapPlayer(player);
        return regionContainer.createQuery().testBuild(BukkitAdapter.adapt(target), localPlayer, Flags.BLOCK_BREAK)
                || worldGuard.getPlatform()
                .getSessionManager()
                .hasBypass(localPlayer, BukkitAdapter.adapt(player.getWorld())
                );
    }

    /**
     * @param player Player looking to interact with a block
     * @param target Place where the player seeks to interact with a block
     * @return true if he can interact with the block
     */
    @Override
    public boolean canInteract(Player player, Location target) {
        LocalPlayer localPlayer = ((WorldGuardPlugin) getPlugin()).wrapPlayer(player);
        return regionContainer.createQuery().testBuild(BukkitAdapter.adapt(target), localPlayer, Flags.INTERACT)
                || worldGuard.getPlatform()
                .getSessionManager()
                .hasBypass(localPlayer, BukkitAdapter.adapt(player.getWorld())
                );
    }

    /**
     * @param player Player looking to use an item
     * @param target Place where the player seeks to use an item at a location
     * @return true if he can use the item at the location
     */
    public boolean canUse(Player player, Location target) {
        LocalPlayer localPlayer = ((WorldGuardPlugin) getPlugin()).wrapPlayer(player);
        return regionContainer.createQuery().testBuild(BukkitAdapter.adapt(target), localPlayer, Flags.USE)
                || worldGuard.getPlatform()
                .getSessionManager()
                .hasBypass(localPlayer, BukkitAdapter.adapt(player.getWorld())
                );
    }
}
