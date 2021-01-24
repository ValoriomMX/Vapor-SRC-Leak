package dev.astro.net.utils.chat;

import org.bukkit.entity.*;
import dev.astro.net.utils.*;
import org.bukkit.*;
import org.bukkit.command.*;

public class Messager
{
    public static void player(final Player player, final String msg) {
        player.sendMessage(Color.translate(msg));
    }
    
    public static void console(final String msg) {
        Bukkit.getConsoleSender().sendMessage(Color.translate(msg));
    }
    
    public static void broadcast(final String msg) {
        Bukkit.broadcastMessage(Color.translate(msg));
    }
    
    public static void warn(final String msg) {
        Bukkit.getLogger().warning(msg);
    }
    
    public static void commandSender(final CommandSender sender, final String string) {
        sender.sendMessage(Color.translate(string));
    }
    
    public static void notFound(final Player player, final String string) {
        player(player, "&cPlayer '&f" + string + "&c' not found!");
    }
    
    public static void noPerms(final Player player) {
        player(player, "&cYou dont have permission!");
    }
}
