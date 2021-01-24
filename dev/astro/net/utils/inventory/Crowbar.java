package dev.astro.net.utils.inventory;

import org.bukkit.inventory.*;
import org.bukkit.*;
import com.google.common.base.*;
import org.bukkit.inventory.meta.*;
import java.util.*;

public class Crowbar
{
    public static int MAX_SPAWNER_USES;
    public static int MAX_END_FRAME_USES;
    public static Material CROWBAR_TYPE;
    public static String CROWBAR_NAME;
    public static String SPAWNER_USE_TAG;
    public static String END_FRAME_USE_TAG;
    private static String LORE_FORMAT;
    private int endFrameUses;
    private int spawnerUses;
    private ItemStack stack;
    private boolean needsMetaUpdate;
    
    static {
        Crowbar.MAX_SPAWNER_USES = 1;
        Crowbar.MAX_END_FRAME_USES = 6;
        Crowbar.CROWBAR_TYPE = Material.GOLD_HOE;
        Crowbar.CROWBAR_NAME = String.valueOf(String.valueOf(ChatColor.RED.toString())) + ChatColor.ITALIC + "Crowbar";
        Crowbar.SPAWNER_USE_TAG = "Spawner Uses";
        Crowbar.END_FRAME_USE_TAG = "End Frame Uses";
        Crowbar.LORE_FORMAT = ChatColor.YELLOW + "%1$s: " + ChatColor.RESET + "%2$s/%3$s";
    }
    
    public Crowbar() {
        this(Crowbar.MAX_SPAWNER_USES, Crowbar.MAX_END_FRAME_USES);
    }
    
    public Crowbar(final int spawnerUses, final int endFrameUses) {
        Preconditions.checkArgument(spawnerUses > 0 || endFrameUses > 0, (Object)"Cannot create a crowbar with empty uses");
        this.stack = new ItemStack(Crowbar.CROWBAR_TYPE, 1);
        this.setSpawnerUses(Math.min(Crowbar.MAX_SPAWNER_USES, spawnerUses));
        this.setEndFrameUses(Math.min(Crowbar.MAX_END_FRAME_USES, endFrameUses));
    }
    
    public static Optional<Crowbar> fromStack(final ItemStack stack) {
        if (stack == null || !stack.hasItemMeta()) {
            return (Optional<Crowbar>)Optional.absent();
        }
        final ItemMeta meta = stack.getItemMeta();
        if (!meta.hasDisplayName() || !meta.hasLore() || !meta.getDisplayName().equals(Crowbar.CROWBAR_NAME)) {
            return (Optional<Crowbar>)Optional.absent();
        }
        final Crowbar crowbar = new Crowbar();
        final List<String> loreList = (List<String>)meta.getLore();
        for (String lore : loreList) {
            lore = ChatColor.stripColor(lore);
            for (int length = lore.length(), i = 0; i < length; ++i) {
                final char character = lore.charAt(i);
                if (Character.isDigit(character)) {
                    final int amount = Integer.parseInt(String.valueOf(character));
                    if (lore.startsWith(Crowbar.END_FRAME_USE_TAG)) {
                        crowbar.setEndFrameUses(amount);
                        break;
                    }
                    if (lore.startsWith(Crowbar.SPAWNER_USE_TAG)) {
                        crowbar.setSpawnerUses(amount);
                        break;
                    }
                }
            }
        }
        return (Optional<Crowbar>)Optional.of((Object)crowbar);
    }
    
    public int getEndFrameUses() {
        return this.endFrameUses;
    }
    
    public void setEndFrameUses(final int uses) {
        if (this.endFrameUses != uses) {
            this.endFrameUses = Math.min(Crowbar.MAX_END_FRAME_USES, uses);
            this.needsMetaUpdate = true;
        }
    }
    
    public int getSpawnerUses() {
        return this.spawnerUses;
    }
    
    public void setSpawnerUses(final int uses) {
        if (this.spawnerUses != uses) {
            this.spawnerUses = Math.min(Crowbar.MAX_SPAWNER_USES, uses);
            this.needsMetaUpdate = true;
        }
    }
    
    public ItemStack getItemIfPresent() {
        final Optional<ItemStack> optional = this.toItemStack();
        return (ItemStack)(optional.isPresent() ? optional.get() : new ItemStack(Material.AIR, 1));
    }
    
    public Optional<ItemStack> toItemStack() {
        if (this.needsMetaUpdate) {
            final double maxDurability;
            double curDurability = maxDurability = Crowbar.CROWBAR_TYPE.getMaxDurability();
            final double increment = curDurability / (Crowbar.MAX_SPAWNER_USES + (double)Crowbar.MAX_END_FRAME_USES);
            curDurability -= increment * (this.spawnerUses + (double)this.endFrameUses);
            if (Math.abs(curDurability - maxDurability) == 0.0) {
                return (Optional<ItemStack>)Optional.absent();
            }
            final ItemMeta meta = this.stack.getItemMeta();
            meta.setDisplayName(Crowbar.CROWBAR_NAME);
            meta.setLore((List)Arrays.asList(String.format(Crowbar.LORE_FORMAT, Crowbar.SPAWNER_USE_TAG, this.spawnerUses, Crowbar.MAX_SPAWNER_USES), String.format(Crowbar.LORE_FORMAT, Crowbar.END_FRAME_USE_TAG, this.endFrameUses, Crowbar.MAX_END_FRAME_USES)));
            this.stack.setItemMeta(meta);
            this.stack.setDurability((short)curDurability);
            this.needsMetaUpdate = false;
        }
        return (Optional<ItemStack>)Optional.of((Object)this.stack);
    }
}
