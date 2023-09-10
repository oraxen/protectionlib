package io.th0rgal.protectionlib.compatibilities;

import io.th0rgal.protectionlib.ProtectionCompatibility;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
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
            return GriefPrevention.instance.allowBuild(player, target) == null;
        }

        /**
        * @param player Player looking to break a block
        * @param target Place where the player seeks to break a block
        * @return true if he can break the block
        */
        @Override
        public boolean canBreak(Player player, Location target) {
            return GriefPrevention.instance.allowBreak(player, target.getBlock(), target) == null;
        }

        /**
        * @param player Player looking to interact with a block
        * @param target Place where the player seeks to interact with a block
        * @return true if he can interact with the block
        */
        @Override
        public boolean canInteract(Player player, Location target) {
            return GriefPrevention.instance.allowBuild(player, target) == null;
        }

    /**
     * @param player Player looking to use an item
     * @param target Place where the player seeks to use an item at a location
     * @return true if he can use the item at the location
     */
    @Override
    public boolean canUse(Player player, Location target) {
        return GriefPrevention.instance.allowBuild(player, target) == null;
    }
}
