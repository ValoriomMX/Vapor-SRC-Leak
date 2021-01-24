package dev.astro.net.utils;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import java.util.*;

public class ItemStackUtils
{
    public static void removeOneItem(final Player player) {
        if (player.getItemInHand().getAmount() > 1) {
            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
        }
        else {
            player.getInventory().setItemInHand(new ItemStack(Material.AIR));
        }
    }
    
    public static ItemStack setItemName(final ItemStack item, final String name) {
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }
    
    public static ItemStack setItemNameAndLore(final ItemStack item, final String name, final List<String> lore) {
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore((List)lore);
        item.setItemMeta(itemMeta);
        return item;
    }
}
