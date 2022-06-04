package io.th0rgal.protectionlib;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ProtectionCompatibility {

    private final JavaPlugin mainPlugin;
    private final Plugin plugin;

    public ProtectionCompatibility(JavaPlugin mainPlugin, Plugin plugin) {
        this.mainPlugin = mainPlugin;
        this.plugin = plugin;
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    public abstract boolean canBuild(Player player, Location target);

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    public abstract boolean canBreak(Player player, Location target);

    /**
     * @param player Player looking to interact
     * @param target Place where the player seeks to interact
     * @return true if he can break the block
     */
    public abstract boolean canInteract(Player player, Location target);

    /**
     * @param player Player looking to use an item
     * @param target Place where the player tried using the item
     * @return true if player can use item
     */
    public abstract boolean canUse(Player player, Location target);

    public Plugin getMainPlugin() {
        return mainPlugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
