package dev.astro.net.utils;

import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class ItemUtils
{
    public static String serialize(final ItemStack item) {
        final StringBuilder builder = new StringBuilder();
        builder.append(item.getType().toString());
        if (item.getDurability() != 0) {
            builder.append(":" + item.getDurability());
        }
        builder.append(" " + item.getAmount());
        for (final Enchantment enchant : item.getEnchantments().keySet()) {
            builder.append(" " + enchant.getName() + ":" + item.getEnchantments().get(enchant));
        }
        final String name = getName(item);
        if (name != null) {
            builder.append(" name:" + name);
        }
        final String lore = getLore(item);
        if (lore != null) {
            builder.append(" lore:" + lore);
        }
        final String owner = getOwner(item);
        if (owner != null) {
            builder.append(" owner:" + owner);
        }
        return builder.toString();
    }
    
    public static ItemStack deserialize(final String serializedItem) {
        final String[] strings = serializedItem.split(" ");
        final Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
        final ItemStack item = new ItemStack(Material.AIR);
        final String[] array = strings;
        final int length = array.length;
        int i = 0;
        while (i < length) {
            final String str = array[i];
            final String[] args = str.split(":");
            if (Material.matchMaterial(args[0]) != null && item.getType() == Material.AIR) {
                item.setType(Material.matchMaterial(args[0]));
                if (args.length == 2) {
                    item.setDurability(Short.parseShort(args[1]));
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        if (item.getType() == Material.AIR) {
            Bukkit.getLogger().info("Could not find a valid material for the item in \"" + serializedItem + "\"");
            return null;
        }
        String[] array2;
        for (int length2 = (array2 = strings).length, j = 0; j < length2; ++j) {
            final String str2 = array2[j];
            final String[] args2 = str2.split(":", 2);
            if (isNumber(args2[0])) {
                item.setAmount(Integer.parseInt(args2[0]));
            }
            if (args2.length != 1) {
                if (args2[0].equalsIgnoreCase("name")) {
                    setName(item, ChatColor.translateAlternateColorCodes('&', args2[1]));
                }
                else if (args2[0].equalsIgnoreCase("lore")) {
                    setLore(item, ChatColor.translateAlternateColorCodes('&', args2[1]));
                }
                else if (args2[0].equalsIgnoreCase("rgb")) {
                    setArmorColor(item, args2[1]);
                }
                else if (args2[0].equalsIgnoreCase("owner")) {
                    setOwner(item, args2[1]);
                }
                else if (Enchantment.getByName(args2[0].toUpperCase()) != null) {
                    enchants.put(Enchantment.getByName(args2[0].toUpperCase()), Integer.parseInt(args2[1]));
                }
            }
        }
        item.addUnsafeEnchantments((Map)enchants);
        return item.getType().equals((Object)Material.AIR) ? null : item;
    }
    
    private static String getOwner(final ItemStack item) {
        if (!(item.getItemMeta() instanceof SkullMeta)) {
            return null;
        }
        return ((SkullMeta)item.getItemMeta()).getOwner();
    }
    
    private static void setOwner(final ItemStack item, final String owner) {
        try {
            final SkullMeta meta = (SkullMeta)item.getItemMeta();
            meta.setOwner(owner);
            item.setItemMeta((ItemMeta)meta);
        }
        catch (Exception ex) {}
    }
    
    private static String getName(final ItemStack item) {
        if (!item.hasItemMeta()) {
            return null;
        }
        if (!item.getItemMeta().hasDisplayName()) {
            return null;
        }
        return item.getItemMeta().getDisplayName().replace(" ", "_").replace('§', '&');
    }
    
    private static void setName(final ItemStack item, String name) {
        name = name.replace("_", " ");
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }
    
    private static String getLore(final ItemStack item) {
        if (!item.hasItemMeta()) {
            return null;
        }
        if (!item.getItemMeta().hasLore()) {
            return null;
        }
        final StringBuilder builder = new StringBuilder();
        final List<String> lore = (List<String>)item.getItemMeta().getLore();
        for (int ind = 0; ind < lore.size(); ++ind) {
            builder.append(String.valueOf((ind > 0) ? "|" : "") + lore.get(ind).replace(" ", "_").replace('§', '&'));
        }
        return builder.toString();
    }
    
    private static void setLore(final ItemStack item, String lore) {
        lore = lore.replace("_", " ");
        final ItemMeta meta = item.getItemMeta();
        meta.setLore((List)Arrays.asList(lore.split("\\|")));
        item.setItemMeta(meta);
    }
    
    private static Color getArmorColor(final ItemStack item) {
        if (!(item.getItemMeta() instanceof LeatherArmorMeta)) {
            return null;
        }
        return ((LeatherArmorMeta)item.getItemMeta()).getColor();
    }
    
    private static void setArmorColor(final ItemStack item, final String str) {
        try {
            final String[] colors = str.split("\\|");
            final int red = Integer.parseInt(colors[0]);
            final int green = Integer.parseInt(colors[1]);
            final int blue = Integer.parseInt(colors[2]);
            final LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
            item.setItemMeta((ItemMeta)meta);
        }
        catch (Exception ex) {}
    }
    
    private static boolean isNumber(final String str) {
        try {
            Integer.parseInt(str);
        }
        catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }
}
