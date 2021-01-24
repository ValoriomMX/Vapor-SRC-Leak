package dev.astro.net.utils.inventory;

import dev.astro.net.*;
import org.bukkit.plugin.java.*;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.util.*;

public class Menu
{
    private String title;
    private int rows;
    private HashMap<Integer, ItemStack> content;
    private HashMap<Integer, ItemAction> commands;
    private Inventory inventory;
    private ItemAction gaction;
    private boolean runempty;
    
    public Menu(final String title, final int rows, final ItemStack[] contents) {
        this(title, rows);
        this.setContents(contents);
    }
    
    public Menu(final String title, final int rows) {
        this.title = "";
        this.rows = 3;
        this.content = new HashMap<Integer, ItemStack>();
        this.commands = new HashMap<Integer, ItemAction>();
        this.runempty = false;
        if (rows < 1 || rows > 6) {
            throw new IndexOutOfBoundsException("Menu can only have between 1 and 6 rows.");
        }
        this.title = title;
        this.rows = rows;
        this.setListener(Vapor.getInstance());
    }
    
    private void setListener(final JavaPlugin pl) {
        pl.getServer().getPluginManager().registerEvents((Listener)new Listener() {
            @EventHandler
            public void onInvClick(final InventoryClickEvent event) {
                final Player player = (Player)event.getWhoClicked();
                final Inventory inv = event.getInventory();
                final ItemStack item = event.getCurrentItem();
                final int slot = event.getRawSlot();
                final InventoryAction a = event.getAction();
                if ((item == null || item.getType() == Material.AIR) && !Menu.this.runempty) {
                    return;
                }
                if (inv.getName().equals(Menu.this.title) && inv.equals(Menu.this.inventory) && slot <= Menu.this.rows * 9 - 1) {
                    event.setCancelled(true);
                    if (Menu.this.hasAction(slot)) {
                        Menu.this.commands.get(slot).run(player, inv, item, slot, a);
                    }
                    if (Menu.this.gaction != null) {
                        Menu.this.gaction.run(player, inv, item, slot, a);
                    }
                }
            }
        }, (Plugin)pl);
    }
    
    @Deprecated
    public boolean hasAction(final int slot) {
        return this.commands.containsKey(slot);
    }
    
    @Deprecated
    public void setAction(final int slot, final ItemAction action) {
        this.commands.put(slot, action);
    }
    
    public void setGlobalAction(final ItemAction action) {
        this.gaction = action;
    }
    
    public void removeGlobalAction() {
        this.gaction = null;
    }
    
    @Deprecated
    public void removeAction(final int slot) {
        if (this.commands.containsKey(slot)) {
            this.commands.remove(slot);
        }
    }
    
    public void runWhenEmpty(final boolean state) {
        this.runempty = state;
    }
    
    public int nextOpenSlot() {
        int h = 0;
        for (final Integer i : this.content.keySet()) {
            if (i > h) {
                h = i;
            }
        }
        for (int j = 0; j <= h; ++j) {
            if (!this.content.containsKey(j)) {
                return j;
            }
        }
        return h + 1;
    }
    
    public void setContents(final ItemStack[] contents) throws ArrayIndexOutOfBoundsException {
        if (contents.length > this.rows * 9) {
            throw new ArrayIndexOutOfBoundsException("setContents() : Contents are larger than inventory.");
        }
        this.content.clear();
        for (int i = 0; i < contents.length; ++i) {
            if (contents[i] != null && contents[i].getType() != Material.AIR) {
                this.content.put(i, contents[i]);
            }
        }
    }
    
    public void addItem(final ItemStack item) {
        if (this.nextOpenSlot() > this.rows * 9 - 1) {
            Vapor.getInstance().getLogger().info("addItem() : Inventory is full.");
            return;
        }
        this.setItem(this.nextOpenSlot(), item);
    }
    
    public void setItem(final int slot, final ItemStack item) throws IndexOutOfBoundsException {
        if (slot < 0 || slot > this.rows * 9 - 1) {
            throw new IndexOutOfBoundsException("setItem() : Slot is outside inventory.");
        }
        if (item == null || item.getType() == Material.AIR) {
            this.removeItem(slot);
            return;
        }
        this.content.put(slot, item);
    }
    
    public void fill(final ItemStack item) {
        for (int i = 0; i < this.rows * 9; ++i) {
            this.content.put(i, item);
        }
    }
    
    public void fillRange(final int s, final int e, final ItemStack item) throws IndexOutOfBoundsException {
        if (e <= s) {
            throw new IndexOutOfBoundsException("fillRange() : Ending index must be less than starting index.");
        }
        if (s < 0 || s > this.rows * 9 - 1) {
            throw new IndexOutOfBoundsException("fillRange() : Starting index is outside inventory.");
        }
        if (e < 0 || e > this.rows * 9 - 1) {
            throw new IndexOutOfBoundsException("fillRange() : Ending index is outside inventory.");
        }
        for (int i = s; i <= e; ++i) {
            this.content.put(i, item);
        }
    }
    
    public void removeItem(final int slot) {
        if (this.content.containsKey(slot)) {
            this.content.remove(slot);
        }
    }
    
    public ItemStack getItem(final int slot) {
        if (this.content.containsKey(slot)) {
            return this.content.get(slot);
        }
        return null;
    }
    
    public void replaceItem(final Integer slot, final ItemStack itemStack) {
        this.inventory.setItem((int)slot, itemStack);
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int rows() {
        return this.rows;
    }
    
    public void build() {
        (this.inventory = Bukkit.createInventory((InventoryHolder)null, this.rows * 9, this.title)).clear();
        for (final Map.Entry<Integer, ItemStack> entry : this.content.entrySet()) {
            this.inventory.setItem((int)entry.getKey(), (ItemStack)entry.getValue());
        }
    }
    
    public Inventory getMenu() {
        this.build();
        return this.inventory;
    }
    
    public void showMenu(final Player player) {
        player.openInventory(this.getMenu());
    }
    
    public ItemStack[] getContents() {
        return this.getMenu().getContents();
    }
    
    public interface ItemAction
    {
        void run(final Player p0, final Inventory p1, final ItemStack p2, final int p3, final InventoryAction p4);
    }
}
