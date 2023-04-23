package io.th0rgal.protectionlib;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class NPCCompatibility extends ProtectionCompatibility {
    public NPCCompatibility(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
    }

    /**
     * @param npc Entity that need's to be checked
     * @return true if the entity is a npc
     */
    public abstract boolean isNPC(Entity npc);
}
