package dev.astro.net.utils.chat;

import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class ItemBuilder
{
    private Material type;
    private String name;
    private String[] lore;
    private int amount;
    private short data;
    private boolean unbreakable;
    
    public ItemBuilder(final Material type, final String name, final String... lore) {
        this.data = 0;
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.amount = 1;
    }
    
    public ItemBuilder(final Material type, final String name, final boolean unbreakable, final String... lore) {
        this.data = 0;
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.amount = 1;
        this.unbreakable = unbreakable;
    }
    
    public ItemBuilder(final Material type, final String name, final int amount, final String... lore) {
        this.data = 0;
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.amount = amount;
    }
    
    public ItemBuilder(final Material type, final String name, final int amount, final short data, final String... lore) {
        this.data = 0;
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.amount = amount;
        this.data = data;
    }
    
    public ItemBuilder(final Material type, final String name, final int amount, final short data, final List<String> lore) {
        this.data = 0;
        this.type = type;
        this.name = name;
        this.lore = lore.toArray(new String[lore.size()]);
        this.amount = amount;
        this.data = data;
    }
    
    public ItemBuilder(final ItemStack display, final String... lore) {
        this.data = 0;
        this.type = display.getType();
        this.name = display.getItemMeta().getDisplayName();
        this.lore = lore;
        this.amount = display.getAmount();
        this.data = display.getDurability();
    }
    
    public ItemStack getItem() {
        final List<String> lore = new ArrayList<String>();
        Arrays.stream(this.lore).forEach(itemLore -> lore.add(ChatColor.translateAlternateColorCodes('&', itemLore)));
        final ItemStack item = new ItemStack(this.type, this.amount, this.data);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(this.name);
        meta.setLore((List)lore);
        meta.spigot().setUnbreakable(this.unbreakable);
        item.setItemMeta(meta);
        return item;
    }
}
