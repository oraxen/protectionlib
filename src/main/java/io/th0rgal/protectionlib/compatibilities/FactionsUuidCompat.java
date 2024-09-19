package io.th0rgal.protectionlib.compatibilities;

import com.massivecraft.factions.FactionsPlugin;
import com.massivecraft.factions.listeners.FactionsBlockListener;
import com.massivecraft.factions.listeners.FactionsPlayerListener;
import com.massivecraft.factions.perms.PermissibleActions;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class FactionsUuidCompat extends ProtectionCompatibility {
    private static FactionsPlugin factions;
    public FactionsUuidCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
        factions = FactionsPlugin.getInstance();
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    @Override
    public boolean canBuild(Player player, Location target) {
        return !factions.worldUtil().isEnabled(target.getWorld()) || FactionsBlockListener.playerCanBuildDestroyBlock(player, target, PermissibleActions.BUILD, false);
    }

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    @Override
    public boolean canBreak(Player player, Location target) {
        return !factions.worldUtil().isEnabled(target.getWorld()) || FactionsBlockListener.playerCanBuildDestroyBlock(player, target, PermissibleActions.DESTROY, false);
    }

    /**
     * @param player Player looking to interact with a block
     * @param target Place where the player seeks to interact with a block
     * @return true if he can interact with the block
     */
    @Override
    public boolean canInteract(Player player, Location target) {
        return !factions.worldUtil().isEnabled(target.getWorld()) || FactionsPlayerListener.canUseBlock(player, target.getBlock().getType(), target, true);
    }

    /**
     * @param player Player looking to use an item
     * @param target Place where the player seeks to use an item at a location
     * @return true if he can use the item at the location
     */
    public boolean canUse(Player player, Location target) {
        return !factions.worldUtil().isEnabled(target.getWorld()) || FactionsPlayerListener.canInteractHere(player, target);
    }
}
