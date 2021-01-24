package dev.astro.net.utils;

import java.util.*;
import org.bukkit.event.inventory.*;
import dev.astro.net.utils.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.scheduler.*;
import dev.astro.net.*;
import org.bukkit.plugin.*;
import org.bukkit.*;

public class InventoryMaker
{
    private List<Inventory2D> inventories;
    private String title;
    private int rowOffset;
    private int rows;
    private int offset;
    private int page;
    
    public InventoryMaker(final String title, final int rows) {
        this(title, rows, 0);
    }
    
    public InventoryMaker(final String title, final boolean bool, final int rows) {
        this(title, rows, 0);
    }
    
    public InventoryMaker(final String title, final int rows, final int rowOffset) {
        this.inventories = new LinkedList<Inventory2D>();
        this.title = Color.translate(title);
        this.rows = rows;
        this.rowOffset = rowOffset;
    }
    
    public Inventory2D getCurrentUI() {
        return this.inventories.get(this.page);
    }
    
    public Inventory getCurrentPage() {
        if (this.inventories.size() == 0) {
            this.createNewInventory();
        }
        return this.inventories.get(this.page).toInventory();
    }
    
    public ClickableItem getItem(final int slot) {
        if (this.inventories.size() == 0) {
            this.createNewInventory();
        }
        final Inventory2D lastInventory = this.inventories.get(this.inventories.size() - 1);
        return lastInventory.getItem(slot);
    }
    
    public int getSize() {
        return this.rows * 9;
    }
    
    private void createNewInventory() {
        final Inventory2D inventory = new Inventory2D(this.title, this.rows, this.rowOffset);
        if (this.inventories.size() > 0) {
            inventory.setItem(0, this.rows - 1, new AbstractClickableItem() {
                @Override
                public void onClick(final InventoryClickEvent event) {
                    final InventoryMaker this$0 = InventoryMaker.this;
                    InventoryMaker.access$1(this$0, this$0.page + 1);
                    try {
                        final Inventory2D inventory2D = InventoryMaker.this.inventories.get(InventoryMaker.this.page);
                        if (inventory2D == null) {
                            final InventoryMaker this$2 = InventoryMaker.this;
                            InventoryMaker.access$1(this$2, this$2.page - 1);
                        }
                        else {
                            event.getWhoClicked().openInventory(InventoryMaker.this.getCurrentPage());
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        final InventoryMaker this$3 = InventoryMaker.this;
                        InventoryMaker.access$1(this$3, this$3.page - 1);
                    }
                }
                
                @Override
                public ItemStack getItemStack() {
                    return new ItemBuilder(Material.ARROW).displayName("&cPage #" + InventoryMaker.this.inventories.size()).build();
                }
            });
            if (inventory.currentY == this.rows - 1 && inventory.currentX == -1) {
                final Inventory2D inventory2D = inventory;
                Inventory2D.access$3(inventory2D, inventory2D.currentX + 1);
            }
        }
        this.inventories.add(inventory);
    }
    
    public void setItem(final int x, final int y, final ClickableItem item) {
        if (this.inventories.size() == 0) {
            this.createNewInventory();
        }
        final Inventory2D lastInventory = this.inventories.get(this.inventories.size() - 1);
        lastInventory.setItem(x - 1, y - 1, item);
    }
    
    public void setItem(final int slot, final ClickableItem item) {
        if (this.inventories.size() == 0) {
            this.createNewInventory();
        }
        final Inventory2D lastInventory = this.inventories.get(this.inventories.size() - 1);
        lastInventory.setItem(slot, item);
    }
    
    public void addItem(final ClickableItem item) {
        if (this.inventories.size() == 0) {
            this.createNewInventory();
        }
        final Inventory2D lastInventory = this.inventories.get(this.inventories.size() - 1);
        if (lastInventory.currentY == this.rows - 1 && lastInventory.currentX >= 7 - this.offset) {
            lastInventory.setItem(8, this.rows - 1, new AbstractClickableItem() {
                @Override
                public void onClick(final InventoryClickEvent event) {
                    final InventoryMaker this$0 = InventoryMaker.this;
                    InventoryMaker.access$1(this$0, this$0.page + 1);
                    try {
                        final Inventory2D inventory2D = InventoryMaker.this.inventories.get(InventoryMaker.this.page);
                        if (inventory2D == null) {
                            final InventoryMaker this$2 = InventoryMaker.this;
                            InventoryMaker.access$1(this$2, this$2.page - 1);
                        }
                        else {
                            event.getWhoClicked().openInventory(InventoryMaker.this.getCurrentPage());
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        final InventoryMaker this$3 = InventoryMaker.this;
                        InventoryMaker.access$1(this$3, this$3.page - 1);
                    }
                }
                
                @Override
                public ItemStack getItemStack() {
                    return new ItemBuilder(Material.ARROW).displayName("&cPage #" + InventoryMaker.this.inventories.size() + 1).build();
                }
            });
            this.createNewInventory();
            this.addItem(item);
        }
        else {
            final Inventory2D inventory2D = lastInventory;
            final Inventory2D inventory2D2 = lastInventory;
            final int n = inventory2D2.currentX + 1;
            Inventory2D.access$3(inventory2D2, n);
            inventory2D.setItem(n + this.offset, lastInventory.currentY, item);
        }
        if (lastInventory.currentX >= 8 - this.offset) {
            Inventory2D.access$3(lastInventory, this.offset - 1);
            final Inventory2D inventory2D3 = lastInventory;
            Inventory2D.access$4(inventory2D3, inventory2D3.currentY + 1);
        }
    }
    
    public void removeItem(final int slot) {
        final Inventory2D inventory2D = this.inventories.get(this.page);
        this.setItem(slot, null);
        for (int i = slot + 1; i < this.getSize(); ++i) {
            final ClickableItem item = this.getItem(i);
            this.setItem(i - 1, item);
            this.setItem(i, null);
        }
        if (inventory2D.currentX >= 0) {
            final Inventory2D inventory2D2 = inventory2D;
            Inventory2D.access$3(inventory2D2, inventory2D2.currentX - 1);
        }
        else if (inventory2D.currentY > 0) {
            final Inventory2D inventory2D3 = inventory2D;
            Inventory2D.access$4(inventory2D3, inventory2D3.currentY - 1);
            Inventory2D.access$3(inventory2D, 7);
        }
    }
    
    static /* synthetic */ void access$1(final InventoryMaker inventoryMaker, final int page) {
        inventoryMaker.page = page;
    }
    
    public static class EmptyClickableItem
    {
        private ItemStack itemStack;
        
        public EmptyClickableItem(final ItemStack itemStack) {
            this.itemStack = itemStack;
        }
    }
    
    public abstract static class AbstractClickableItem implements ClickableItem
    {
        private ItemStack itemStack;
        
        public AbstractClickableItem() {
            this.itemStack = this.itemStack;
        }
    }
    
    public static class InventoryMakerHolder implements InventoryHolder
    {
        private InventoryMaker InventoryMaker;
        private String title;
        private int slots;
        
        private InventoryMakerHolder(final InventoryMaker InventoryMaker, final String title, final int slots) {
            this.InventoryMaker = InventoryMaker;
            this.title = title;
            this.slots = slots;
        }
        
        public Inventory getInventory() {
            return this.InventoryMaker.getCurrentPage();
        }
        
        public InventoryMaker getInventoryMaker() {
            return this.InventoryMaker;
        }
    }
    
    public class Inventory2D
    {
        private ClickableItem[][] items;
        private String title;
        private int rows;
        private Inventory cachedInventory;
        private int currentX;
        private int currentY;
        
        public Inventory2D(final String title, final int rows, final int rowOffset) {
            this.currentX = -1;
            this.currentY = rowOffset;
            this.title = title;
            this.rows = rows;
            this.items = new ClickableItem[9][this.rows];
        }
        
        public void setItem(final int x, final int y, final ClickableItem clickableItem) {
            this.items[x][y] = clickableItem;
            if (this.cachedInventory != null) {
                final int slot = y * 9 + x;
                new BukkitRunnable() {
                    public void run() {
                        Inventory2D.this.cachedInventory.setItem(slot, (clickableItem != null) ? clickableItem.getItemStack() : null);
                    }
                }.runTaskTimer((Plugin)Vapor.getInstance(), 0L, 5L);
            }
        }
        
        public void setItem(final int slot, final ClickableItem clickableItem) {
            final int y = Math.abs(slot / 9);
            final int x = -(y * 9 - slot);
            this.setItem(x, y, clickableItem);
        }
        
        public ClickableItem getItem(final int slot) {
            final int y = Math.abs(slot / 9);
            final int x = -(y * 9 - slot);
            if (this.items.length <= x) {
                return null;
            }
            final ClickableItem[] items = this.items[x];
            if (items.length <= y) {
                return null;
            }
            return items[y];
        }
        
        public Inventory toInventory() {
            if (this.cachedInventory != null) {
                return this.cachedInventory;
            }
            final Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder)new InventoryMakerHolder(InventoryMaker.this, this.title, this.rows * 9, null), this.rows * 9, this.title);
            for (int y = 0; y < this.rows; ++y) {
                for (int x = 0; x < 9; ++x) {
                    final int slot = y * 9 + x;
                    final ClickableItem item = this.items[x][y];
                    if (item != null) {
                        new BukkitRunnable() {
                            public void run() {
                                try {
                                    inventory.setItem(slot, item.getItemStack());
                                }
                                catch (Exception ex) {}
                            }
                        }.runTaskTimer((Plugin)Vapor.getInstance(), 0L, 5L);
                    }
                }
            }
            return this.cachedInventory = inventory;
        }
        
        static /* synthetic */ void access$3(final Inventory2D inventory2D, final int currentX) {
            inventory2D.currentX = currentX;
        }
        
        static /* synthetic */ void access$4(final Inventory2D inventory2D, final int currentY) {
            inventory2D.currentY = currentY;
        }
    }
    
    public interface ClickableItem
    {
        void onClick(final InventoryClickEvent p0);
        
        ItemStack getItemStack();
    }
}
