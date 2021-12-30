package io.th0rgal.protectionlib.compatibilities;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * @author TopeEstLa
 */
public class SuperiorSkyblockCompat extends ProtectionCompatibility {


    public SuperiorSkyblockCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        return SuperiorSkyblockAPI.getPlayer(player).isInsideIsland();
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        return SuperiorSkyblockAPI.getPlayer(player).isInsideIsland();
    }
}
