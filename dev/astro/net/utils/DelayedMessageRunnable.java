package dev.astro.net.utils;

import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.*;
import org.bukkit.plugin.*;

public class DelayedMessageRunnable extends BukkitRunnable
{
    private Player player;
    private String message;
    
    public DelayedMessageRunnable(final JavaPlugin plugin, final Player player, final String message) {
        this.player = player;
        this.message = message;
        this.runTask((Plugin)plugin);
    }
    
    public void run() {
        this.player.sendMessage(this.message);
    }
}
