package dev.astro.net.utils;

import org.bukkit.*;
import dev.astro.net.*;
import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

public abstract class Menu implements Listener
{
    private Inventory inventory;
    
    public Menu(final String name, final int slots) {
        this.inventory = Bukkit.createInventory((InventoryHolder)null, 9 * slots, ChatColor.translateAlternateColorCodes('&', name));
        Vapor.getInstance().getServer().getPluginManager().registerEvents((Listener)this, (Plugin)Vapor.getInstance());
    }
    
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getInventory().equals(this.getInventory()) && event.getCurrentItem() != null && this.getInventory().contains(event.getCurrentItem()) && event.getWhoClicked() instanceof Player) {
            this.onClick((Player)event.getWhoClicked(), event.getCurrentItem());
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (event.getInventory().equals(this.getInventory()) && event.getPlayer() instanceof Player) {
            this.onClose((Player)event.getPlayer());
        }
    }
    
    public void addItem(final ItemStack itemStack) {
        this.inventory.addItem(new ItemStack[] { itemStack });
    }
    
    public void set(final int n, final ItemStack mat) {
        this.inventory.setItem(n, mat);
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public String getName() {
        return this.inventory.getName();
    }
    
    public void openInventory(final Player player) {
        player.openInventory(this.inventory);
    }
    
    public void onClose(final Player player) {
    }
    
    public abstract void onClick(final Player p0, final ItemStack p1);
}
