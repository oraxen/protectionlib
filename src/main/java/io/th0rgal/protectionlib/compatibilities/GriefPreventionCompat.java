package io.th0rgal.protectionlib.compatibilities;

import io.th0rgal.protectionlib.ProtectionCompatibility;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class GriefPreventionCompat extends ProtectionCompatibility {

        public GriefPreventionCompat(JavaPlugin mainPlugin, Plugin plugin) {
            super(mainPlugin, plugin);
        }

        /**
        * @param player Player looking to place a block
        * @param target Place where the player seeks to place a block
        * @return true if he can put the block
        */
        @Override
        public boolean canBuild(Player player, Location target) {
            return checkPermission(player, target, ClaimPermission.Build);
        }

        /**
        * @param player Player looking to break a block
        * @param target Place where the player seeks to break a block
        * @return true if he can break the block
        */
        @Override
        public boolean canBreak(Player player, Location target) {
            return checkPermission(player, target, ClaimPermission.Build);
        }

        /**
        * @param player Player looking to interact with a block
        * @param target Place where the player seeks to interact with a block
        * @return true if he can interact with the block
        */
        @Override
        public boolean canInteract(Player player, Location target) {
            return checkPermission(player, target, ClaimPermission.Access);
        }

    /**
     * @param player Player looking to use an item
     * @param target Place where the player seeks to use an item at a location
     * @return true if he can use the item at the location
     */
    @Override
    public boolean canUse(Player player, Location target) {
        return checkPermission(player, target, ClaimPermission.Access);
    }

    private boolean checkPermission(Player player, Location target, ClaimPermission permission) {
        PlayerData playerData = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(target, false, playerData.lastClaim);
        if (claim == null || playerData.ignoreClaims) return true;

        playerData.lastClaim = claim;
        return claim.checkPermission(player, permission, null) == null;
    }
}
