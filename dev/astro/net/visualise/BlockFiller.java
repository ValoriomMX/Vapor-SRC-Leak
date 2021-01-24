package dev.astro.net.visualise;

import org.bukkit.entity.*;
import org.bukkit.*;
import com.google.common.collect.*;
import java.util.*;

abstract class BlockFiller
{
    VisualBlockData generate(final Player player, final World world, final int x, final int y, final int z) {
        return this.generate(player, new Location(world, (double)x, (double)y, (double)z));
    }
    
    abstract VisualBlockData generate(final Player p0, final Location p1);
    
    ArrayList<VisualBlockData> bulkGenerate(final Player player, final Iterable<Location> locations) {
        final ArrayList<VisualBlockData> data = new ArrayList<VisualBlockData>(Iterables.size((Iterable)locations));
        for (final Location location : locations) {
            data.add(this.generate(player, location));
        }
        return data;
    }
}
