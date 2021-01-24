package dev.astro.net.utils;

import java.util.*;
import org.bukkit.entity.*;
import dev.astro.net.*;
import org.bukkit.craftbukkit.v1_7_R4.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.*;
import org.bukkit.*;
import net.minecraft.server.v1_7_R4.*;

public class HorseNMS extends Handler
{
    private HashMap<Player, Integer> horses;
    
    public HorseNMS(final Vapor plugin) {
        super(plugin);
        this.horses = new HashMap<Player, Integer>();
    }
    
    public void sitPlayer(final Player player) {
        final Location l = player.getLocation();
        final EntityHorse horse = new EntityHorse((World)((CraftWorld)l.getWorld()).getHandle());
        final EntityCreeper creeper = new EntityCreeper((World)((CraftWorld)l.getWorld()).getHandle());
        creeper.setLocation(l.getX(), l.getY(), l.getZ(), 0.0f, 0.0f);
        creeper.setInvisible(true);
        horse.setLocation(l.getX(), l.getY(), l.getZ(), 0.0f, 0.0f);
        horse.setInvisible(true);
        final PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving)creeper);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
        this.horses.put(player, creeper.getId());
        final PacketPlayOutAttachEntity sit = new PacketPlayOutAttachEntity(0, (Entity)((CraftPlayer)player).getHandle(), (Entity)creeper);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)sit);
    }
    
    public void unsitPlayer(final Player player) {
        if (this.horses.get(player) != null) {
            final PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { this.horses.get(player) });
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
        }
    }
}
