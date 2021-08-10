package io.th0rgal.protectionlib.compatibilities;

import com.flowpowered.math.vector.Vector3i;
import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.Subject;
import com.griefdefender.api.Tristate;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.permission.flag.Flags;

import io.th0rgal.protectionlib.ProtectionCompatibility;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GriefDefenderCompat extends ProtectionCompatibility {

    public GriefDefenderCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    @Override
    public boolean canBuild(Player player, Location target) {
        final Vector3i vector = Vector3i.from(target.getBlockX(), target.getBlockY(), target.getBlockZ());
        final Claim claim = GriefDefender.getCore().getClaimManager(target.getWorld().getUID()).getClaimAt(vector);
        final Subject subject = GriefDefender.getCore().getSubject(player.getUniqueId().toString());
        final Tristate canBuild = GriefDefender.getPermissionManager().getActiveFlagPermissionValue(claim, subject,
                Flags.BLOCK_PLACE, player, target.getBlock(), null, true);

        return canBuild == Tristate.TRUE;
    }

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    @Override
    public boolean canBreak(Player player, Location target) {
        final Vector3i vector = Vector3i.from(target.getBlockX(), target.getBlockY(), target.getBlockZ());
        final Claim claim = GriefDefender.getCore().getClaimManager(target.getWorld().getUID()).getClaimAt(vector);
        final Subject subject = GriefDefender.getCore().getSubject(player.getUniqueId().toString());
        final Tristate canBreak = GriefDefender.getPermissionManager().getActiveFlagPermissionValue(claim, subject,
                Flags.BLOCK_BREAK, player, target.getBlock(), null, true);

        return canBreak == Tristate.TRUE;
    }
}
