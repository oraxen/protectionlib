package io.th0rgal.protectionlib.compatibilities;

import io.th0rgal.protectionlib.NPCCompatibility;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CitizensCompat extends NPCCompatibility {


    public CitizensCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
    }


    @Override
    public boolean isNPC(Entity npc) {
        return CitizensAPI.getNPCRegistry().isNPC(npc);
    }
}
