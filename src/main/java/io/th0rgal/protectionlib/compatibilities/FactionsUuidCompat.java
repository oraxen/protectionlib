package io.th0rgal.protectionlib.compatibilities;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.perms.PermissibleAction;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FactionsUuidCompat extends ProtectionCompatibility {

    private final FPlayers fPlayers;
    private final Board board;

    public FactionsUuidCompat(Plugin plugin) {
        super(plugin);
        this.fPlayers = FPlayers.getInstance();
        this.board = Board.getInstance();
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        return board.getFactionAt(new FLocation(target))
                .hasAccess(fPlayers.getByPlayer(player), PermissibleAction.BUILD);
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        return board.getFactionAt(new FLocation(target))
                .hasAccess(fPlayers.getByPlayer(player), PermissibleAction.DESTROY);
    }
}