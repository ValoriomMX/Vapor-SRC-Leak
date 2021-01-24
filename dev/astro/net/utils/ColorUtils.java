package dev.astro.net.utils;

import org.bukkit.*;
import org.apache.commons.lang.*;
import java.util.*;

public class ColorUtils
{
    public String translateFromString(final String text) {
        return StringEscapeUtils.unescapeJava(ChatColor.translateAlternateColorCodes('&', text));
    }
    
    public List<String> translateFromArray(final List<String> text) {
        final List<String> messages = new ArrayList<String>();
        for (final String string : text) {
            messages.add(this.translateFromString(string));
        }
        return messages;
    }
}
