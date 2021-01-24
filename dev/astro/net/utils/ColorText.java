package dev.astro.net.utils;

import org.bukkit.*;
import java.util.*;

public class ColorText
{
    public static String translate(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static List<String> translate(final List<String> message) {
        for (int i = 0; i < message.size(); ++i) {
            message.set(i, translate(message.get(i)));
        }
        return message;
    }
}
