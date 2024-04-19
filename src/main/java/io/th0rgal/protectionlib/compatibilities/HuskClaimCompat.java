package io.th0rgal.protectionlib.compatibilities;

import io.th0rgal.protectionlib.ProtectionCompatibility;
import net.william278.huskclaims.api.BukkitHuskClaimsAPI;
import net.william278.huskclaims.api.HuskClaimsAPI;
import net.william278.huskclaims.libraries.cloplib.operation.OperationType;
import net.william278.huskclaims.position.Position;
import net.william278.huskclaims.trust.TrustLevel;
import net.william278.huskclaims.user.OnlineUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class HuskClaimCompat extends ProtectionCompatibility {

    private final HuskClaimsAPI huskClaimsCommon;
    private final BukkitHuskClaimsAPI huskClaimsBukkit;

    public HuskClaimCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
        huskClaimsCommon = HuskClaimsAPI.getInstance();
        huskClaimsBukkit = BukkitHuskClaimsAPI.getInstance();
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        OnlineUser onlineUser = huskClaimsCommon.getOnlineUser(player.getUniqueId());
        Position position = huskClaimsBukkit.getPosition(target);

        Optional<TrustLevel> trustLevel = huskClaimsBukkit.getTrustLevelAt(position, onlineUser);
        return trustLevel.isPresent() && trustLevel.get().getFlags().contains(OperationType.BLOCK_PLACE);
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        OnlineUser onlineUser = huskClaimsCommon.getOnlineUser(player.getUniqueId());
        Position position = huskClaimsBukkit.getPosition(target);

        Optional<TrustLevel> trustLevel = huskClaimsBukkit.getTrustLevelAt(position, onlineUser);
        return trustLevel.isPresent() && trustLevel.get().getFlags().contains(OperationType.BLOCK_BREAK);
    }

    @Override
    public boolean canInteract(Player player, Location target) {
        OnlineUser onlineUser = huskClaimsCommon.getOnlineUser(player.getUniqueId());
        Position position = huskClaimsBukkit.getPosition(target);

        OperationType operationType = target.getBlock().getType().isBlock() ? OperationType.BLOCK_INTERACT : OperationType.ENTITY_INTERACT;
        Optional<TrustLevel> trustLevel = huskClaimsBukkit.getTrustLevelAt(position, onlineUser);
        return trustLevel.isPresent() && trustLevel.get().getFlags().contains(operationType);
    }

    @Override
    public boolean canUse(Player player, Location target) {
        OnlineUser onlineUser = huskClaimsCommon.getOnlineUser(player.getUniqueId());
        Position position = huskClaimsBukkit.getPosition(target);

        Optional<TrustLevel> trustLevel = huskClaimsBukkit.getTrustLevelAt(position, onlineUser);
        return trustLevel.isPresent() && trustLevel.get().getFlags().contains(OperationType.BLOCK_INTERACT);
    }
}
