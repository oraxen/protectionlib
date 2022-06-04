package io.th0rgal.protectionlib.compatibilities;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.perms.PermissibleActions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FactionsUuidCompatFix {

    public static boolean canBuild(Board board, FPlayers fPlayers, Player player, Location target) {
        return board.getFactionAt(new FLocation(target))
                .hasAccess(fPlayers.getByPlayer(player), PermissibleActions.BUILD, new FLocation(target));
    }

    public static boolean canBreak(Board board, FPlayers fPlayers, Player player, Location target) {
        return board.getFactionAt(new FLocation(target))
                .hasAccess(fPlayers.getByPlayer(player), PermissibleActions.DESTROY, new FLocation(target));
    }

    public static boolean canInteract(Board board, FPlayers fPlayers, Player player, Location target) {
        return board.getFactionAt(new FLocation(target))
                .hasAccess(fPlayers.getByPlayer(player), PermissibleActions.ITEM, new FLocation(target));
    }
}
