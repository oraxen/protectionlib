package io.th0rgal.protectionlib.compatibilities;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TownyCompat extends ProtectionCompatibility {

    public TownyCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    @Override
    public boolean canBuild(Player player, Location target) {
        return PlayerCacheUtil.getCachePermission(player, target, target.getBlock().getType(),
                TownyPermission.ActionType.BUILD);
    }

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    @Override
    public boolean canBreak(Player player, Location target) {
        return PlayerCacheUtil.getCachePermission(player, target, target.getBlock().getType(),
                TownyPermission.ActionType.DESTROY);
    }

    /**
     * @param player Player looking to interact with a block
     * @param target Place where the player seeks to interact with a block
     * @return true if he can interact with the block
     */
    @Override
    public boolean canInteract(Player player, Location target) {
        return PlayerCacheUtil.getCachePermission(player, target, target.getBlock().getType(),
                TownyPermission.ActionType.SWITCH);
    }
}
