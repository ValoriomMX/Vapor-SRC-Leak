package dev.astro.net.utils;

import org.bukkit.*;

public class BaseConstants
{
    public static String PLAYER_WITH_NAME_OR_UUID_NOT_FOUND;
    
    static {
        BaseConstants.PLAYER_WITH_NAME_OR_UUID_NOT_FOUND = ChatColor.GOLD + "Player with name or UUID '" + ChatColor.WHITE + "%1$s" + ChatColor.GOLD + "' not found.";
    }
}
