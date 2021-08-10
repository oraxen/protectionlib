package io.th0rgal.protectionlib.compatibilities;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.perms.PermissibleAction;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class FactionsUuidCompat extends ProtectionCompatibility {

    private final FPlayers fPlayers;
    private final Board board;

    public FactionsUuidCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
        this.fPlayers = FPlayers.getInstance();
        this.board = Board.getInstance();
    }

    /**
     * @param player Player looking to place a block
     * @param target Place where the player seeks to place a block
     * @return true if he can put the block
     */
    @Override
    public boolean canBuild(Player player, Location target) {
        return board.getFactionAt(new FLocation(target))
                .hasAccess(fPlayers.getByPlayer(player), PermissibleAction.BUILD);
    }

    /**
     * @param player Player looking to break a block
     * @param target Place where the player seeks to break a block
     * @return true if he can break the block
     */
    @Override
    public boolean canBreak(Player player, Location target) {
        return board.getFactionAt(new FLocation(target))
                .hasAccess(fPlayers.getByPlayer(player), PermissibleAction.DESTROY);
    }
}