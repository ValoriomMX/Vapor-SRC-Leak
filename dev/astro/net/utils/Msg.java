package dev.astro.net.utils;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;

public class Msg
{
    public static char HEART;
    public static char KRUZIC;
    public static char DOUBLE_ARROW_RIGHT;
    public static char DOUBLE_ARROW_LEFT;
    public static String NO_PERMISSION;
    public static String NO_CONSOLE;
    public static String CONSOLE;
    public static String BIG_LINE;
    
    static {
        Msg.HEART = '\u2764';
        Msg.KRUZIC = '\ufffd';
        Msg.DOUBLE_ARROW_RIGHT = '»';
        Msg.DOUBLE_ARROW_LEFT = '«';
        Msg.NO_PERMISSION = Color.translate("&fUnknown command. Type \"/help\" for help.");
        Msg.NO_CONSOLE = Color.translate("&cNo console.");
        Msg.CONSOLE = Color.translate("&4&lCONSOLE");
        Msg.BIG_LINE = Color.translate("&7&m---------------");
    }
    
    public static boolean checkOffline(final CommandSender sender, final String args) {
        final Player offline = Bukkit.getPlayer(args);
        if (offline == null) {
            sender.sendMessage(Color.translate("&cNo player with the name \"" + args + "\" found."));
            return true;
        }
        return false;
    }
    
    public static void sendMessage(final String text) {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player player = onlinePlayers[i];
            if (player != null) {
                player.sendMessage(Color.translate(text));
            }
        }
        logConsole(text);
    }
    
    public static void sendMessage(final String text, final String permission) {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player player = onlinePlayers[i];
            if (player != null && player.hasPermission(permission)) {
                player.sendMessage(Color.translate(text));
            }
        }
        logConsole(text);
    }
    
    public static void log(final CommandSender sender, final String text) {
        Command.broadcastCommandMessage(sender, text);
    }
    
    public static void logConsole(final String text) {
        Bukkit.getConsoleSender().sendMessage(Color.translate(text));
    }
}
