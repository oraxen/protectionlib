package io.th0rgal.protectionlib.compatibilities;

import io.th0rgal.protectionlib.ProtectionCompatibility;
import me.angeschossen.lands.api.LandsIntegration;
import me.angeschossen.lands.api.flags.type.Flags;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.land.LandWorld;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class LandsCompat extends ProtectionCompatibility {

    private static LandsIntegration landsIntegration;

    public LandsCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
        landsIntegration = LandsIntegration.of(mainPlugin);
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    @Override
    public boolean canBuild(Player player, Location target) {
        return hasFlag(target, player, Flags.BLOCK_PLACE);
    }

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    @Override
    public boolean canBreak(Player player, Location target) {
        return hasFlag(target, player, Flags.BLOCK_BREAK);
    }

    /**
     * @param player Player looking to interact with a block
     * @param target Place where the player seeks to interact with a block
     * @return true if he can interact with the block
     */
    @Override
    public boolean canInteract(Player player, Location target) {
        return hasFlag(target, player, Flags.INTERACT_GENERAL);
    }

    /**
     * @param player Player looking to use an item
     * @param target Place where the player seeks to use an item at a location
     * @return true if he can use the item at the location
     */
    @Override
    public boolean canUse(Player player, Location target) {
        return hasFlag(target, player, Flags.INTERACT_GENERAL);
    }

    /**
     * Checks if a player's role has a flag at the given position.
     * This does check wilderness flags (/lands admin menu) as well.
     *
     * @param location Location of interaction
     * @param player   Player that seeks to do stuff
     * @param flag     The Lands flag
     * @return false if not allowed
     */
    private boolean hasFlag(Location location, Player player, RoleFlag flag) {
        LandWorld landWorld = landsIntegration.getWorld(location.getWorld());
        return landWorld == null || landWorld.hasRoleFlag(landsIntegration.getLandPlayer(player.getUniqueId()), location, flag, null, true);
    }
}
