package io.th0rgal.protectionlib.compatibilities;

import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TownyCompat extends ProtectionCompatibility {

    public TownyCompat(Plugin plugin) {
        super(plugin);
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        return PlayerCacheUtil.getCachePermission(player, target, target.getBlock().getType(),
                TownyPermission.ActionType.BUILD);
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        return PlayerCacheUtil.getCachePermission(player, target, target.getBlock().getType(),
                TownyPermission.ActionType.DESTROY);
    }
}