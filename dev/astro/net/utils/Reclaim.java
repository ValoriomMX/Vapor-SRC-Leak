package dev.astro.net.utils;

public class Reclaim
{
    private String name;
    private String permission;
    private String[] commands;
    
    public Reclaim(final String name, final String permission, final String[] commands) {
        this.name = name;
        this.permission = permission;
        this.commands = commands;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public String[] getCommands() {
        return this.commands;
    }
}
