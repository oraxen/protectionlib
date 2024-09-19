package io.th0rgal.protectionlib.compatibilities;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ResidenceCompat extends ProtectionCompatibility {

    Residence plugin = Residence.getInstance();

    public ResidenceCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
    }

    private boolean canDo(Player player, Location target, Flags flag) {
        ClaimedResidence res = plugin.getResidenceManager().getByLoc(target);
        return res == null || res.getPermissions().playerHas(player, flag, false);
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    @Override
    public boolean canBuild(Player player, Location target) {
        return canDo(player, target, Flags.build);
    }

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    @Override
    public boolean canBreak(Player player, Location target) {
        return canDo(player, target, Flags.destroy);
    }

    /**
     * @param player Player looking to interact with a block
     * @param target Place where the player seeks to interact with a block
     * @return true if he can interact with the block
     */
    @Override
    public boolean canInteract(Player player, Location target) {
        // No single interact flag, so just check if player is on their own island
        return canDo(player, target, Flags.use);
    }

    /**
     * @param player Player looking to use an item
     * @param target Place where the player seeks to use an item at a location
     * @return true if he can use the item at the location
     */
    public boolean canUse(Player player, Location target) {
        // No single use flag, so just check if player is on their own island
        return canDo(player, target, Flags.use);
    }
}
