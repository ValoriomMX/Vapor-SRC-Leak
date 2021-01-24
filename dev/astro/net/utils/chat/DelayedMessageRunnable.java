package dev.astro.net.utils.chat;

import org.bukkit.scheduler.*;
import org.bukkit.entity.*;

public class DelayedMessageRunnable extends BukkitRunnable
{
    private Player player;
    private String message;
    
    public DelayedMessageRunnable(final Player player, final String message) {
        this.player = player;
        this.message = message;
    }
    
    public void run() {
        this.player.sendMessage(this.message);
    }
}
