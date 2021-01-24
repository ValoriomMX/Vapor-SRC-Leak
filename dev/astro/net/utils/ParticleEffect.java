package dev.astro.net.utils;

import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.*;
import javax.annotation.*;
import com.google.common.base.*;
import org.bukkit.*;
import net.minecraft.server.v1_7_R4.*;

public enum ParticleEffect
{
    HUGE_EXPLODE("HUGE_EXPLODE", 0, "hugeexplosion", 0), 
    LARGE_EXPLODE("LARGE_EXPLODE", 1, "largeexplode", 1), 
    FIREWORK_SPARK("FIREWORK_SPARK", 2, "fireworksSpark", 2), 
    AIR_BUBBLE("AIR_BUBBLE", 3, "bubble", 3), 
    SUSPEND("SUSPEND", 4, "suspend", 4), 
    DEPTH_SUSPEND("DEPTH_SUSPEND", 5, "depthSuspend", 5), 
    TOWN_AURA("TOWN_AURA", 6, "townaura", 6), 
    CRITICAL_HIT("CRITICAL_HIT", 7, "crit", 7), 
    MAGIC_CRITICAL_HIT("MAGIC_CRITICAL_HIT", 8, "magicCrit", 8), 
    MOB_SPELL("MOB_SPELL", 9, "mobSpell", 9), 
    MOB_SPELL_AMBIENT("MOB_SPELL_AMBIENT", 10, "mobSpellAmbient", 10), 
    SPELL("SPELL", 11, "spell", 11), 
    INSTANT_SPELL("INSTANT_SPELL", 12, "instantSpell", 12), 
    BLUE_SPARKLE("BLUE_SPARKLE", 13, "witchMagic", 13), 
    NOTE_BLOCK("NOTE_BLOCK", 14, "note", 14), 
    ENDER("ENDER", 15, "portal", 15), 
    ENCHANTMENT_TABLE("ENCHANTMENT_TABLE", 16, "enchantmenttable", 16), 
    EXPLODE("EXPLODE", 17, "explode", 17), 
    FIRE("FIRE", 18, "flame", 18), 
    LAVA_SPARK("LAVA_SPARK", 19, "lava", 19), 
    FOOTSTEP("FOOTSTEP", 20, "footstep", 20), 
    SPLASH("SPLASH", 21, "splash", 21), 
    LARGE_SMOKE("LARGE_SMOKE", 22, "largesmoke", 22), 
    CLOUD("CLOUD", 23, "cloud", 23), 
    REDSTONE_DUST("REDSTONE_DUST", 24, "reddust", 24), 
    SNOWBALL_HIT("SNOWBALL_HIT", 25, "snowballpoof", 25), 
    DRIP_WATER("DRIP_WATER", 26, "dripWater", 26), 
    DRIP_LAVA("DRIP_LAVA", 27, "dripLava", 27), 
    SNOW_DIG("SNOW_DIG", 28, "snowshovel", 28), 
    SLIME("SLIME", 29, "slime", 29), 
    HEART("HEART", 30, "heart", 30), 
    ANGRY_VILLAGER("ANGRY_VILLAGER", 31, "angryVillager", 31), 
    GREEN_SPARKLE("GREEN_SPARKLE", 32, "happyVillager", 32), 
    ICONCRACK("ICONCRACK", 33, "iconcrack", 33), 
    TILECRACK("TILECRACK", 34, "tilecrack", 34);
    
    private String name;
    @Deprecated
    private int id;
    
    private ParticleEffect(final String s, final int n, final String name, final int id) {
        this.name = name;
        this.id = id;
    }
    
    @Deprecated
    String getName() {
        return this.name;
    }
    
    @Deprecated
    public int getId() {
        return this.id;
    }
    
    public void display(final Player player, final float x, final float y, final float z, final float speed, final int amount) {
        this.display(player, x, y, z, 0.0f, 0.0f, 0.0f, speed, amount);
    }
    
    public void display(final Player player, final float x, final float y, final float z, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount) {
        final Packet packet = (Packet)this.createPacket(x, y, z, offsetX, offsetY, offsetZ, speed, amount);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }
    
    public void display(final Player player, final Location location, final float speed, final int amount) {
        this.display(player, location, 0.0f, 0.0f, 0.0f, speed, amount);
    }
    
    public void display(final Player player, final Location location, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount) {
        final Packet packet = (Packet)this.createPacket(location, offsetX, offsetY, offsetZ, speed, amount);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }
    
    public void broadcast(final float x, final float y, final float z, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount) {
        final Packet packet = (Packet)this.createPacket(x, y, z, offsetX, offsetY, offsetZ, speed, amount);
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player player = onlinePlayers[i];
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
        }
    }
    
    public void broadcast(final Location location, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount) {
        this.broadcast(location, offsetX, offsetY, offsetZ, speed, amount, null, null);
    }
    
    public void broadcast(final Location location, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount, @Nullable final Player source) {
        this.broadcast(location, offsetX, offsetY, offsetZ, speed, amount, source, null);
    }
    
    public void broadcast(final Location location, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount, @Nullable final Player source, @Nullable final Predicate<Player> predicate) {
        final Packet packet = (Packet)this.createPacket(location, offsetX, offsetY, offsetZ, speed, amount);
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player player = onlinePlayers[i];
            if ((source == null || player.canSee(source)) && (predicate == null || predicate.apply((Object)player))) {
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }
    
    public void sphere(@Nullable final Player player, final Location location, final float radius) {
        this.sphere(player, location, radius, 20.0f, 2);
    }
    
    public void sphere(@Nullable final Player player, final Location location, final float radius, final float density, final int intensity) {
        Preconditions.checkNotNull((Object)location, (Object)"Location cannot be null");
        Preconditions.checkArgument(radius >= 0.0f, (Object)"Radius must be positive");
        Preconditions.checkArgument(density >= 0.0f, (Object)"Density must be positive");
        Preconditions.checkArgument(intensity >= 0, (Object)"Intensity must be positive");
        final float deltaPitch = 180.0f / density;
        final float deltaYaw = 360.0f / density;
        final World world = location.getWorld();
        for (int i = 0; i < density; ++i) {
            for (int j = 0; j < density; ++j) {
                final float pitch = -90.0f + j * deltaPitch;
                final float yaw = -180.0f + i * deltaYaw;
                final float x = radius * MathHelper.sin(-yaw * 0.017453292f - 3.1415927f) * -MathHelper.cos(-pitch * 0.017453292f) + (float)location.getX();
                final float y = radius * MathHelper.sin(-pitch * 0.017453292f) + (float)location.getY();
                final float z = radius * MathHelper.cos(-yaw * 0.017453292f - 3.1415927f) * -MathHelper.cos(-pitch * 0.017453292f) + (float)location.getZ();
                final Location target = new Location(world, (double)x, (double)y, (double)z);
                if (player == null) {
                    this.broadcast(target, 0.0f, 0.0f, 0.0f, 0.0f, intensity);
                }
                else {
                    this.display(player, target, 0.0f, 0.0f, 0.0f, 0.0f, intensity);
                }
            }
        }
    }
    
    private PacketPlayOutWorldParticles createPacket(final Location location, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount) {
        return this.createPacket((float)location.getX(), (float)location.getY(), (float)location.getZ(), offsetX, offsetY, offsetZ, speed, amount);
    }
    
    private PacketPlayOutWorldParticles createPacket(final float x, final float y, final float z, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount) {
        Preconditions.checkArgument(speed >= 0.0f, (Object)"Speed must be positive");
        Preconditions.checkArgument(amount > 0, (Object)"Cannot use less than one particle.");
        return new PacketPlayOutWorldParticles(this.name, x, y, z, offsetX, offsetY, offsetZ, speed, amount);
    }
}
