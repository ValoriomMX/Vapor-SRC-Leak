package dev.astro.net.utils.inventory.item;

import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import java.util.*;

public interface ItemDB
{
    void reloadItemDatabase();
    
    ItemStack getPotion(final String p0);
    
    ItemStack getPotion(final String p0, final int p1);
    
    ItemStack getItem(final String p0);
    
    ItemStack getItem(final String p0, final int p1);
    
    String getName(final ItemStack p0);
    
    String getPrimaryName(final ItemStack p0);
    
    String getNames(final ItemStack p0);
    
    List<ItemStack> getMatching(final Player p0, final String[] p1);
}
