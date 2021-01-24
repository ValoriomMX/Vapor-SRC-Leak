package dev.astro.net.utils;

import org.bukkit.*;
import org.bukkit.entity.*;

public class Message
{
    public static void sendMessage(final String message) {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player online = onlinePlayers[i];
            online.sendMessage(Color.translate(message));
        }
    }
    
    public static void sendMessage(final String message, final String permission) {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player online = onlinePlayers[i];
            if (online.hasPermission(permission)) {
                online.sendMessage(message);
            }
        }
    }
    
    public static void sendMessageWithoutPlayer(final Player player, final String message) {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player online = onlinePlayers[i];
            if (online != player) {
                online.sendMessage(message);
            }
        }
    }
    
    public static void sendMessageWithoutPlayer(final Player player, final String message, final String permission) {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player online = onlinePlayers[i];
            if (online != player && online.hasPermission(permission)) {
                online.sendMessage(message);
            }
        }
    }
}
