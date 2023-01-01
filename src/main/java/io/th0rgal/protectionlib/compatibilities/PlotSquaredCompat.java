package io.th0rgal.protectionlib.compatibilities;

import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.PlotSquared;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import io.th0rgal.protectionlib.ProtectionCompatibility;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PlotSquaredCompat extends ProtectionCompatibility {


    public PlotSquaredCompat(JavaPlugin mainPlugin, Plugin plugin) {
        super(mainPlugin, plugin);
    }

    @Override
    public boolean canBuild(Player player, Location target) {
        Plot plot = getPlotFromLocation(target);
        if (plot == null) return true;
        return plot.isAdded(player.getUniqueId());
    }

    @Override
    public boolean canBreak(Player player, Location target) {
        Plot plot = getPlotFromLocation(target);
        if (plot == null) return true;
        return plot.isDenied(player.getUniqueId());
    }

    private Plot getPlotFromLocation(Location location) {
        com.plotsquared.core.location.Location plotLoc = adaptBukkitLocation(location);
        if (plotLoc == null) return null;
        return Plot.getPlot(plotLoc);
    }

    private com.plotsquared.core.location.Location adaptBukkitLocation(Location location) {
        if (!location.isWorldLoaded()) return null;
        assert location.getWorld() != null;
        return com.plotsquared.core.location.Location.at(
                location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
