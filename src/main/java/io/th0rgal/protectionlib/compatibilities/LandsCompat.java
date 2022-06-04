package io.th0rgal.protectionlib.compatibilities;

import io.th0rgal.protectionlib.ProtectionCompatibility;
import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.land.Land;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class LandsCompat extends ProtectionCompatibility {

    private static LandsIntegration landsIntegration;

    public LandsCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
        landsIntegration = new LandsIntegration(mainPlugin);
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    @Override
    public boolean canBuild(Player player, Location target) {
        Land land = landsIntegration.getLand(target);
        return land == null || land.getTrustedPlayers().stream().anyMatch(playerUUID -> playerUUID.equals(player.getUniqueId()));
    }

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    @Override
    public boolean canBreak(Player player, Location target) {
        Land land = landsIntegration.getLand(target);
        return land == null || land.getTrustedPlayers().stream().anyMatch(playerUUID -> playerUUID.equals(player.getUniqueId()));
    }

    /**
     * @param player Player looking to interact
     * @param target Place where the player seeks to interact
     * @return true if he can interact
     */
    @Override
    public boolean canInteract(Player player, Location target) {
        Land land = landsIntegration.getLand(target);
        return land == null || land.getTrustedPlayers().stream().anyMatch(playerUUID -> playerUUID.equals(player.getUniqueId()));
    }
}
