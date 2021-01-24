package dev.astro.net.utils;

import org.bukkit.configuration.serialization.*;
import org.bukkit.*;
import java.util.*;

public class SerializableLocation implements ConfigurationSerializable
{
    private int x;
    private int y;
    private int z;
    private float yaw;
    private float pitch;
    private World world;
    private Location location;
    
    public SerializableLocation(final Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
        this.world = location.getWorld();
        this.location = location;
    }
    
    public SerializableLocation(final Map<String, Object> map) {
        this.x = map.get("x");
        this.y = map.get("y");
        this.z = map.get("z");
        this.yaw = Float.valueOf(map.get("yaw"));
        this.pitch = Float.valueOf(map.get("pitch"));
        this.world = Bukkit.getWorld((String)map.get("world"));
        this.location = new Location(this.world, (double)this.x, (double)this.y, (double)this.z, this.yaw, this.pitch);
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public void setWorld(final World world) {
        this.world = world;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getZ() {
        return this.z;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public static SerializableLocation deserialize(final Map<String, Object> map) {
        return new SerializableLocation(map);
    }
    
    public Map<String, Object> serialize() {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("x", this.x);
        map.put("y", this.y);
        map.put("z", this.z);
        map.put("yaw", String.valueOf(this.yaw));
        map.put("pitch", String.valueOf(this.pitch));
        map.put("world", this.world.getName());
        return map;
    }
}
