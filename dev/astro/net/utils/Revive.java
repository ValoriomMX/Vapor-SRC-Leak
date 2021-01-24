package dev.astro.net.utils;

public class Revive
{
    private String permission;
    private long cooldown;
    private String broadcast;
    
    public Revive(final String permission, final int cooldown, final String broadcast) {
        this.permission = permission;
        this.cooldown = cooldown;
        this.broadcast = broadcast;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public long getCooldown() {
        return this.cooldown;
    }
    
    public String getBroadcast() {
        return this.broadcast;
    }
}
