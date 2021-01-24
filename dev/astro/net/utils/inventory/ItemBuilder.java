package dev.astro.net.utils.inventory;

import org.bukkit.inventory.*;
import com.google.common.base.*;
import java.util.*;
import org.bukkit.enchantments.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class ItemBuilder
{
    private ItemStack stack;
    private ItemMeta meta;
    
    public ItemBuilder(final Material material) {
        this(material, 1);
    }
    
    public ItemBuilder(final Material material, final int amount) {
        this(material, amount, (byte)0);
    }
    
    public ItemBuilder(final ItemStack stack) {
        Preconditions.checkNotNull((Object)stack, (Object)"ItemStack cannot be null");
        this.stack = stack;
    }
    
    public ItemBuilder(final Material material, final int amount, final byte data) {
        Preconditions.checkNotNull((Object)material, (Object)"Material cannot be null");
        Preconditions.checkArgument(amount > 0, (Object)"Amount must be positive");
        this.stack = new ItemStack(material, amount, (short)data);
    }
    
    public ItemBuilder displayName(final String name) {
        if (this.meta == null) {
            this.meta = this.stack.getItemMeta();
        }
        this.meta.setDisplayName(name);
        return this;
    }
    
    public ItemBuilder loreLine(final String line) {
        if (this.meta == null) {
            this.meta = this.stack.getItemMeta();
        }
        final boolean hasLore = this.meta.hasLore();
        final List<String> lore = hasLore ? this.meta.getLore() : new ArrayList<String>();
        lore.add(hasLore ? lore.size() : 0, line);
        this.lore(line);
        return this;
    }
    
    public ItemBuilder lore(final String... lore) {
        if (this.meta == null) {
            this.meta = this.stack.getItemMeta();
        }
        this.meta.setLore((List)Arrays.asList(lore));
        return this;
    }
    
    public ItemBuilder enchant(final Enchantment enchantment, final int level) {
        return this.enchant(enchantment, level, true);
    }
    
    public ItemBuilder enchant(final Enchantment enchantment, final int level, final boolean unsafe) {
        if (unsafe && level >= enchantment.getMaxLevel()) {
            this.stack.addUnsafeEnchantment(enchantment, level);
        }
        else {
            this.stack.addEnchantment(enchantment, level);
        }
        return this;
    }
    
    public ItemBuilder data(final short data) {
        this.stack.setDurability(data);
        return this;
    }
    
    public ItemStack build() {
        if (this.meta != null) {
            this.stack.setItemMeta(this.meta);
        }
        return this.stack;
    }
    
    public ItemBuilder setLeatherArmorColor(final Color color) {
        try {
            final LeatherArmorMeta im = (LeatherArmorMeta)this.stack.getItemMeta();
            im.setColor(color);
            this.stack.setItemMeta((ItemMeta)im);
        }
        catch (ClassCastException ex) {}
        return this;
    }
    
    public ItemBuilder addUnsafeEnchantment(final Enchantment ench, final int level) {
        this.stack.addUnsafeEnchantment(ench, level);
        return this;
    }
    
    public ItemStack toItemStack() {
        return this.stack;
    }
}
