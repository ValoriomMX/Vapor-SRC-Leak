package dev.astro.net.utils;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.*;
import net.minecraft.server.v1_7_R4.*;
import java.lang.reflect.*;

public class ScoreboardTeamPacketMod
{
    private PacketPlayOutScoreboardTeam packet;
    
    public ScoreboardTeamPacketMod(final String name, final String prefix, final String suffix, final Collection players, final int paramInt) {
        this.packet = new PacketPlayOutScoreboardTeam();
        this.setField("a", name);
        this.setField("h", paramInt);
        if (paramInt == 0 || paramInt == 2) {
            this.setField("b", name);
            this.setField("c", prefix);
            this.setField("d", suffix);
            this.setField("i", 1);
        }
        if (paramInt == 0) {
            this.addAll(players);
        }
    }
    
    public ScoreboardTeamPacketMod(final String name, Collection players, final int paramInt) {
        this.packet = new PacketPlayOutScoreboardTeam();
        if (players == null) {
            players = new ArrayList();
        }
        this.setField("a", name);
        this.setField("h", paramInt);
        this.addAll(players);
    }
    
    public void sendToPlayer(final Player bukkitPlayer) {
        ((CraftPlayer)bukkitPlayer).getHandle().playerConnection.sendPacket((Packet)this.packet);
    }
    
    private void setField(final String field, final Object value) {
        try {
            final Field fieldObject = this.packet.getClass().getDeclaredField(field);
            fieldObject.setAccessible(true);
            fieldObject.set(this.packet, value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void addAll(final Collection col) {
        try {
            final Field fieldObject = this.packet.getClass().getDeclaredField("g");
            fieldObject.setAccessible(true);
            ((Collection)fieldObject.get(this.packet)).addAll(col);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
