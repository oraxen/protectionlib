package io.th0rgal.protectionlib.compatibilities;

import com.massivecraft.factions.listeners.FactionsBlockListener;
import com.massivecraft.factions.listeners.FactionsPlayerListener;
import com.massivecraft.factions.perms.PermissibleActions;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import net.crashcraft.crashclaim.CrashClaim;
import net.crashcraft.crashclaim.api.CrashClaimAPI;
import net.crashcraft.crashclaim.permissions.PermissionRoute;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CrashClaimCompat extends ProtectionCompatibility {
    private static CrashClaimAPI crashClaim;
    public CrashClaimCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
        crashClaim = new CrashClaimAPI((CrashClaim) plugin);
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    @Override
    public boolean canBuild(Player player, Location target) {
        return crashClaim.getClaim(target) == null || crashClaim.getClaim(target).hasPermission(player.getUniqueId(), target, PermissionRoute.BUILD);
    }

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    @Override
    public boolean canBreak(Player player, Location target) {
        return crashClaim.getClaim(target) == null || crashClaim.getClaim(target).hasPermission(player.getUniqueId(), target, PermissionRoute.BUILD);
    }

    /**
     * @param player Player looking to interact with a block
     * @param target Place where the player seeks to interact with a block
     * @return true if he can interact with the block
     */
    @Override
    public boolean canInteract(Player player, Location target) {
        return crashClaim.getClaim(target) == null || crashClaim.getClaim(target).hasPermission(player.getUniqueId(), target, PermissionRoute.INTERACTIONS);
    }

    /**
     * @param player Player looking to use an item
     * @param target Place where the player seeks to use an item at a location
     * @return true if he can use the item at the location
     */
    public boolean canUse(Player player, Location target) {
        return crashClaim.getClaim(target) == null || crashClaim.getClaim(target).hasPermission(player.getUniqueId(), target, PermissionRoute.INTERACTIONS);
    }
}
