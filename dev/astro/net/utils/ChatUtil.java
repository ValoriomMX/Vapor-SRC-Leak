package dev.astro.net.utils;

import com.google.common.collect.*;
import org.bukkit.command.*;
import com.google.common.base.*;
import org.bukkit.entity.*;
import net.md_5.bungee.api.chat.*;
import org.bukkit.*;
import org.apache.commons.lang.*;
import java.util.*;

public class ChatUtil
{
    public static String MENU_BAR;
    public static String CHAT_BAR;
    public static String SB_BAR;
    private static ImmutableMap<Object, Object> CHAT_DYE_COLOUR_MAP;
    
    static {
        ChatUtil.MENU_BAR = String.valueOf(String.valueOf(ChatColor.STRIKETHROUGH.toString())) + "--------------------------------";
        ChatUtil.CHAT_BAR = String.valueOf(String.valueOf(ChatColor.STRIKETHROUGH.toString())) + "------------------------------------------------";
        ChatUtil.SB_BAR = String.valueOf(String.valueOf(ChatColor.STRIKETHROUGH.toString())) + "----------------------";
        ChatUtil.CHAT_DYE_COLOUR_MAP = (ImmutableMap<Object, Object>)ImmutableMap.builder().put((Object)ChatColor.AQUA, (Object)DyeColor.LIGHT_BLUE).put((Object)ChatColor.BLACK, (Object)DyeColor.BLACK).put((Object)ChatColor.BLUE, (Object)DyeColor.LIGHT_BLUE).put((Object)ChatColor.DARK_AQUA, (Object)DyeColor.CYAN).put((Object)ChatColor.DARK_BLUE, (Object)DyeColor.BLUE).put((Object)ChatColor.DARK_GRAY, (Object)DyeColor.GRAY).put((Object)ChatColor.DARK_GREEN, (Object)DyeColor.GREEN).put((Object)ChatColor.DARK_PURPLE, (Object)DyeColor.PURPLE).put((Object)ChatColor.DARK_RED, (Object)DyeColor.RED).put((Object)ChatColor.GOLD, (Object)DyeColor.ORANGE).put((Object)ChatColor.GRAY, (Object)DyeColor.SILVER).put((Object)ChatColor.GREEN, (Object)DyeColor.LIME).put((Object)ChatColor.LIGHT_PURPLE, (Object)DyeColor.MAGENTA).put((Object)ChatColor.RED, (Object)DyeColor.RED).put((Object)ChatColor.WHITE, (Object)DyeColor.WHITE).put((Object)ChatColor.YELLOW, (Object)DyeColor.YELLOW).build();
    }
    
    public static DyeColor toDyeColor(final ChatColor colour) {
        return (DyeColor)ChatUtil.CHAT_DYE_COLOUR_MAP.get((Object)colour);
    }
    
    public static net.md_5.bungee.api.ChatColor fromBukkit(final ChatColor chatColor) {
        return net.md_5.bungee.api.ChatColor.values()[chatColor.ordinal()];
    }
    
    public static boolean endsWith(final String word, final String... suffix) {
        for (final String message : suffix) {
            if (word.toLowerCase().endsWith(message)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean contains(final String word, final String... suffix) {
        for (final String message : suffix) {
            if (word.contains(message)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean equalsIgnoreCase(final String word, final String... suffix) {
        for (final String message : suffix) {
            if (word.equalsIgnoreCase(message)) {
                return true;
            }
        }
        return false;
    }
    
    public static String formatMessage(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static List<String> formatMessage(final List<String> messages) {
        final List<String> buffered = new ArrayList<String>();
        for (final String message : messages) {
            buffered.add(formatMessage("&r" + message));
        }
        return buffered;
    }
    
    public static String[] formatMessage(final String... messages) {
        return formatMessage(Arrays.asList(messages)).stream().toArray(String[]::new);
    }
    
    public static void sendMessageWithPermission(final CommandSender commandSender, final String permission, final String... value) {
        Preconditions.checkNotNull((Object)commandSender, (Object)"Sender cannot be null");
        Preconditions.checkNotNull((Object)commandSender, (Object)"Permission cannot be null");
        Preconditions.checkNotNull((Object)value, (Object)"Argument cannot be null");
        if (commandSender.hasPermission(permission)) {
            commandSender.sendMessage(formatMessage(value));
        }
    }
    
    public static void sendMessage(final CommandSender commandSender, final BaseComponent[] value) {
        Preconditions.checkNotNull((Object)commandSender, (Object)"Sender cannot be null");
        Preconditions.checkNotNull((Object)value, (Object)"Argument cannot be null");
        if (commandSender instanceof Player) {
            ((Player)commandSender).spigot().sendMessage(value);
        }
        else {
            commandSender.sendMessage(TextComponent.toLegacyText(value));
        }
    }
    
    public static void sendMessage(final CommandSender commandSender, final String... value) {
        Preconditions.checkNotNull((Object)commandSender, (Object)"Sender cannot be null");
        Preconditions.checkNotNull((Object)value, (Object)"Argument cannot be null");
        commandSender.sendMessage(formatMessage(value));
    }
    
    public static void sendMessage(final Player player, final String... value) {
        Preconditions.checkNotNull((Object)player, (Object)"Player cannot be null");
        Preconditions.checkNotNull((Object)value, (Object)"Argument cannot be null");
        player.sendMessage(formatMessage(value));
    }
    
    public static void broadcastWithPermission(final String permission, final String... value) {
        Preconditions.checkNotNull((Object)value, (Object)"Permission cannot be null");
        Preconditions.checkNotNull((Object)value, (Object)"Argument cannot be null");
        Bukkit.getOnlinePlayers();
        Bukkit.getConsoleSender().sendMessage(formatMessage(value));
    }
    
    public static void broadcast(final String... value) {
        Preconditions.checkNotNull((Object)value, (Object)"Argument cannot be null");
        Bukkit.getOnlinePlayers();
        Bukkit.getConsoleSender().sendMessage(formatMessage(value));
    }
    
    public static void broadcastSender(final String... value) {
        Preconditions.checkNotNull((Object)value, (Object)"Argument cannot be null");
        Bukkit.getConsoleSender().sendMessage(formatMessage(value));
    }
    
    public static void info(final String... value) {
        Preconditions.checkNotNull((Object)value, (Object)"Argument cannot be null");
        Bukkit.getConsoleSender().sendMessage(formatMessage(value));
    }
    
    public static String capitalizeFullyWords(final String string) {
        return WordUtils.capitalizeFully(string);
    }
    
    public static List<String> splitText(final int length, final String text, final String linePrefix, final String wordSuffix) {
        if (text.length() <= length) {
            return Collections.singletonList(String.valueOf(String.valueOf(linePrefix)) + text);
        }
        final List<String> lines = new ArrayList<String>();
        final String[] split = text.split(" ");
        StringBuilder builder = new StringBuilder(linePrefix);
        for (int i = 0; i < split.length; ++i) {
            if (builder.length() + split[i].length() >= length) {
                lines.add(builder.toString());
                builder = new StringBuilder(linePrefix);
            }
            builder.append(split[i]).append(wordSuffix);
            if (i == split.length - 1) {
                builder.replace(builder.length() - wordSuffix.length(), builder.length(), "");
            }
        }
        if (builder.length() != 0) {
            lines.add(builder.toString());
        }
        return lines;
    }
    
    public static net.md_5.bungee.api.ChatColor toBungee(final ChatColor color) {
        switch (color) {
            case BLACK: {
                return net.md_5.bungee.api.ChatColor.BLACK;
            }
            case DARK_BLUE: {
                return net.md_5.bungee.api.ChatColor.DARK_BLUE;
            }
            case DARK_GREEN: {
                return net.md_5.bungee.api.ChatColor.DARK_GREEN;
            }
            case DARK_AQUA: {
                return net.md_5.bungee.api.ChatColor.DARK_AQUA;
            }
            case DARK_RED: {
                return net.md_5.bungee.api.ChatColor.DARK_RED;
            }
            case DARK_PURPLE: {
                return net.md_5.bungee.api.ChatColor.DARK_PURPLE;
            }
            case GOLD: {
                return net.md_5.bungee.api.ChatColor.GOLD;
            }
            case GRAY: {
                return net.md_5.bungee.api.ChatColor.GRAY;
            }
            case DARK_GRAY: {
                return net.md_5.bungee.api.ChatColor.DARK_GRAY;
            }
            case BLUE: {
                return net.md_5.bungee.api.ChatColor.BLUE;
            }
            case GREEN: {
                return net.md_5.bungee.api.ChatColor.GREEN;
            }
            case AQUA: {
                return net.md_5.bungee.api.ChatColor.AQUA;
            }
            case RED: {
                return net.md_5.bungee.api.ChatColor.RED;
            }
            case LIGHT_PURPLE: {
                return net.md_5.bungee.api.ChatColor.LIGHT_PURPLE;
            }
            case YELLOW: {
                return net.md_5.bungee.api.ChatColor.YELLOW;
            }
            case WHITE: {
                return net.md_5.bungee.api.ChatColor.WHITE;
            }
            case MAGIC: {
                return net.md_5.bungee.api.ChatColor.MAGIC;
            }
            case BOLD: {
                return net.md_5.bungee.api.ChatColor.BOLD;
            }
            case STRIKETHROUGH: {
                return net.md_5.bungee.api.ChatColor.STRIKETHROUGH;
            }
            case UNDERLINE: {
                return net.md_5.bungee.api.ChatColor.UNDERLINE;
            }
            case ITALIC: {
                return net.md_5.bungee.api.ChatColor.ITALIC;
            }
            case RESET: {
                return net.md_5.bungee.api.ChatColor.RESET;
            }
            default: {
                throw new IllegalArgumentException("Unrecognised Bukkit colour " + color.name() + ".");
            }
        }
    }
}
