package io.th0rgal.protectionlib.compatibilities;

import io.th0rgal.protectionlib.ProtectionCompatibility;
import net.william278.husktowns.api.BukkitHuskTownsAPI;
import net.william278.husktowns.api.HuskTownsAPI;
import net.william278.husktowns.claim.Position;
import net.william278.husktowns.libraries.cloplib.operation.OperationType;
import net.william278.husktowns.user.OnlineUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class HuskTownsCompat extends ProtectionCompatibility {
    private final HuskTownsAPI commonAPI;
    private final BukkitHuskTownsAPI bukkitAPI;

    public HuskTownsCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
        commonAPI = HuskTownsAPI.getInstance();
        bukkitAPI = BukkitHuskTownsAPI.getInstance();
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        return isOperationAllowed(player, target, OperationType.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        return isOperationAllowed(player, target, OperationType.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(Player player, Location target) {
        OperationType type = target.getBlock().getType().isBlock() ? OperationType.BLOCK_INTERACT : OperationType.ENTITY_INTERACT;
        return isOperationAllowed(player, target, type);
    }

    @Override
    public boolean canUse(Player player, Location target) {
        return isOperationAllowed(player, target, OperationType.BLOCK_INTERACT);
    }

    private boolean isOperationAllowed(@NotNull Player player, @NotNull Location location, @NotNull OperationType type) {
        OnlineUser user = bukkitAPI.getOnlineUser(player);
        Position position = bukkitAPI.getPosition(location);
        return commonAPI.isOperationAllowed(user, type, position);
    }
}
