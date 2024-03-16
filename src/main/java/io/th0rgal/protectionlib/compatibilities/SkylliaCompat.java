package io.th0rgal.protectionlib.compatibilities;

import fr.euphyllia.skyllia.api.SkylliaAPI;
import fr.euphyllia.skyllia.api.skyblock.Island;
import fr.euphyllia.skyllia.api.skyblock.PermissionManager;
import fr.euphyllia.skyllia.api.skyblock.Players;
import fr.euphyllia.skyllia.api.skyblock.model.Position;
import fr.euphyllia.skyllia.api.skyblock.model.RoleType;
import fr.euphyllia.skyllia.api.skyblock.model.permissions.Permissions;
import fr.euphyllia.skyllia.api.skyblock.model.permissions.PermissionsIsland;
import fr.euphyllia.skyllia.api.skyblock.model.permissions.PermissionsType;
import fr.euphyllia.skyllia.api.utils.helper.RegionHelper;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SkylliaCompat extends ProtectionCompatibility {

    public SkylliaCompat(JavaPlugin m, Plugin p) {
        super(m, p);
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        return this.permissionCheck(player, target, PermissionsType.ISLAND, PermissionsIsland.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        return this.permissionCheck(player, target, PermissionsType.ISLAND, PermissionsIsland.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(Player player, Location target) {
        return this.permissionCheck(player, target, PermissionsType.ISLAND, PermissionsIsland.INTERACT_ENTITIES);
    }

    @Override
    public boolean canUse(Player player, Location target) {
        return this.permissionCheck(player, target, PermissionsType.ISLAND, PermissionsIsland.INTERACT);
    }

    private boolean permissionCheck(Player player, Location location, PermissionsType permissionsType, Permissions permissions) {
        Chunk chunk = location.getChunk();
        if (Boolean.FALSE.equals(SkylliaAPI.isWorldSkyblock(chunk.getWorld().getName()))) {
            return true;
        }
        Position position = RegionHelper.getRegionInChunk(chunk.getX(), chunk.getZ());
        Island island = SkylliaAPI.getIslandByPosition(position);
        if (island == null) {
            return false;
        }
        Players players = island.getMember(player.getUniqueId());
        RoleType playerRole = players.getRoleType();
        PermissionManager permissionManager = new PermissionManager(island.getPermission(permissionsType, playerRole));
        return permissionManager.hasPermission(permissions.getPermissionValue());
    }
}
