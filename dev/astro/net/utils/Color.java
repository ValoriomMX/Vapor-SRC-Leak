package dev.astro.net.utils;

import org.bukkit.*;
import java.util.*;

public class Color
{
    public static String translate(final String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    
    public List<String> translateFromArray(final List<String> text) {
        final List<String> messages = new ArrayList<String>();
        for (final String string : text) {
            messages.add(translate(string));
        }
        return messages;
    }
}
