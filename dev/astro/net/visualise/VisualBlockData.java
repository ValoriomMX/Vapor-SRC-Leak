package dev.astro.net.visualise;

import org.bukkit.material.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

public class VisualBlockData extends MaterialData
{
    public VisualBlockData(final Material type) {
        super(type);
    }
    
    public VisualBlockData(final Material type, final byte data) {
        super(type, data);
    }
    
    public Material getBlockType() {
        return this.getItemType();
    }
    
    @Deprecated
    public Material getItemType() {
        return super.getItemType();
    }
    
    @Deprecated
    public ItemStack toItemStack() {
        throw new UnsupportedOperationException("This is a VisualBlock data");
    }
    
    @Deprecated
    public ItemStack toItemStack(final int amount) {
        throw new UnsupportedOperationException("This is a VisualBlock data");
    }
}
