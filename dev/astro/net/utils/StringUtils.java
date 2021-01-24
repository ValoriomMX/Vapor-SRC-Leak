package dev.astro.net.utils;

import java.util.*;
import org.bukkit.potion.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.*;

public class StringUtils
{
    public static String formatMilisecondsToSeconds(final Long time) {
        final float seconds = (time + 0.0f) / 1000.0f;
        final String string = String.format("%1$.1f", seconds);
        return string;
    }
    
    public static String formatMilisecondsToMinutes(final Long time) {
        final int seconds = (int)(time / 1000L % 60L);
        final int minutes = (int)(time / 1000L / 60L);
        final String string = String.format("%02d:%02d", minutes, seconds);
        return string;
    }
    
    public static String formatSecondsToMinutes(final int time) {
        final int seconds = time % 60;
        final int minutes = time / 60;
        final String string = String.format("%02d:%02d", minutes, seconds);
        return string;
    }
    
    public static String formatSecondsToHours(final int time) {
        final int hours = time / 3600;
        final int minutes = time % 3600 / 60;
        final int seconds = time % 60;
        final String string = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return string;
    }
    
    public static String formatMinutes(final int time) {
        final int minutes = time / 60;
        final String string = String.format("%02d", minutes);
        return string;
    }
    
    public static String formatInt(final int i) {
        final int r = i * 1000;
        final int sec = r / 1000 % 60;
        final int min = r / 60000 % 60;
        final int h = r / 3600000 % 24;
        return String.valueOf(String.valueOf(String.valueOf((h > 0) ? new StringBuilder(String.valueOf(h)).append(":").toString() : ""))) + ((min < 10) ? ("0" + min) : Integer.valueOf(min)) + ":" + ((sec < 10) ? ("0" + sec) : Integer.valueOf(sec));
    }
    
    public static String getEffectNamesList(final ArrayList<PotionEffect> effects) {
        final StringBuilder names = new StringBuilder();
        for (final PotionEffect effect : effects) {
            names.append(getPotionEffectName(effect.getType())).append(", ");
        }
        if (names.length() != 0) {
            names.delete(names.length() - 2, names.length());
        }
        return names.toString();
    }
    
    public static String getPotionEffectName(final PotionEffectType type) {
        final String name;
        final String s;
        final String s2;
        switch (s2 = (s = (name = type.getName()))) {
            case "POISON": {
                return "Poison";
            }
            case "NIGHT_VISION": {
                return "Night Vision";
            }
            case "SLOW_DIGGING": {
                return "Slow Digging";
            }
            case "WITHER": {
                return "Wither";
            }
            case "INCREASE_DAMAGE": {
                return "Strength";
            }
            case "BLINDNESS": {
                return "Blindness";
            }
            case "WATER_BREATHING": {
                return "Water Breathing";
            }
            case "REGENERATION": {
                return "Regeneration";
            }
            case "ABSORPTION": {
                return "Absorption";
            }
            case "FAST_DIGGING": {
                return "Haste";
            }
            case "HEALTH_BOOST": {
                return "Health Boost";
            }
            case "HARM": {
                return "Instant Damage";
            }
            case "HEAL": {
                return "Instant Health";
            }
            case "JUMP": {
                return "Jump";
            }
            case "SLOW": {
                return "Slowness";
            }
            case "WEAKNESS": {
                return "Weakness";
            }
            case "SPEED": {
                return "Speed";
            }
            case "SATURATION": {
                return "Saturation";
            }
            case "DAMAGE_RESISTANCE": {
                return "Resistance";
            }
            case "INVISIBILITY": {
                return "Invisibility";
            }
            case "FIRE_RESISTANCE": {
                return "Fire Resistance";
            }
            case "CONFUSION": {
                return "Confusion";
            }
            case "HUNGER": {
                return "Hunger";
            }
            default:
                break;
        }
        return "";
    }
    
    public static String getEntityName(final Entity entity) {
        final String name;
        final String s;
        final String s2;
        switch (s2 = (s = (name = entity.getType().name()))) {
            case "VILLAGER": {
                return "Villager";
            }
            case "PLAYER": {
                return "Player";
            }
            case "SILVERFISH": {
                return "Silverfish";
            }
            case "SPIDER": {
                return "Spider";
            }
            case "ENDERMAN": {
                return "Enderman";
            }
            case "WITHER": {
                return "Wither";
            }
            case "ZOMBIE": {
                return "Zombie";
            }
            case "SKELETON": {
                return "Skeleton";
            }
            case "PIG_ZOMBIE": {
                return "Pig Zombie";
            }
            case "IRON_GOLEM": {
                return "Iron Golem";
            }
            case "WOLF": {
                return "Wolf";
            }
            case "CAVE_SPIDER": {
                return "Cave Spider";
            }
            case "BLAZE": {
                return "Blaze";
            }
            case "SLIME": {
                return "Slime";
            }
            case "WITCH": {
                return "Witch";
            }
            case "MAGMA_CUBE": {
                return "Magma Cube";
            }
            case "CREEPER": {
                return "Creeper";
            }
            default:
                break;
        }
        return "";
    }
    
    public static String getWorldName(final Location location) {
        String worldName = "";
        final World world = location.getWorld();
        if (world.getEnvironment().equals((Object)World.Environment.NORMAL)) {
            worldName = "World";
        }
        else if (world.getEnvironment().equals((Object)World.Environment.NETHER)) {
            worldName = "Nether";
        }
        else if (world.getEnvironment().equals((Object)World.Environment.THE_END)) {
            worldName = "End";
        }
        else {
            worldName = world.getName();
        }
        return worldName;
    }
    
    public static Object getTime(final int seconds) {
        if (seconds < 60) {
            return String.valueOf(String.valueOf(String.valueOf(seconds))) + "s";
        }
        final int minutes = seconds / 60;
        final int s = 60 * minutes;
        final int secondsLeft = seconds - s;
        if (minutes < 60) {
            if (secondsLeft > 0) {
                return String.valueOf(String.valueOf(String.valueOf(String.valueOf(minutes))) + "m " + secondsLeft + "s");
            }
            return String.valueOf(String.valueOf(String.valueOf(String.valueOf(minutes))) + "m");
        }
        else {
            if (minutes < 1440) {
                String time = "";
                final int hours = minutes / 60;
                time = String.valueOf(String.valueOf(String.valueOf(hours))) + "h";
                final int inMins = 60 * hours;
                final int leftOver = minutes - inMins;
                if (leftOver >= 1) {
                    time = String.valueOf(String.valueOf(String.valueOf(time))) + " " + leftOver + "m";
                }
                if (secondsLeft > 0) {
                    time = String.valueOf(String.valueOf(String.valueOf(time))) + " " + secondsLeft + "s";
                }
                return time;
            }
            String time = "";
            final int days = minutes / 1440;
            time = String.valueOf(String.valueOf(String.valueOf(days))) + "d";
            final int inMins = 1440 * days;
            final int leftOver = minutes - inMins;
            if (leftOver >= 1) {
                if (leftOver < 60) {
                    time = String.valueOf(String.valueOf(String.valueOf(time))) + " " + leftOver + "m";
                }
                else {
                    final int hours2 = leftOver / 60;
                    time = String.valueOf(String.valueOf(String.valueOf(time))) + " " + hours2 + "h";
                    final int hoursInMins = 60 * hours2;
                    final int minsLeft = leftOver - hoursInMins;
                    if (leftOver >= 1) {
                        time = String.valueOf(String.valueOf(String.valueOf(time))) + " " + minsLeft + "m";
                    }
                }
            }
            if (secondsLeft > 0) {
                time = String.valueOf(String.valueOf(String.valueOf(time))) + " " + secondsLeft + "s";
            }
            return time;
        }
    }
    
    public static Integer getPing(final Player player) {
        return ((CraftPlayer)player).getHandle().ping;
    }
}
