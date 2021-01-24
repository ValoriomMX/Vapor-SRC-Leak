package dev.astro.net.utils.inventory.item;

import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;
import java.lang.reflect.*;

public class EnchantGlow extends EnchantmentWrapper
{
    private static Enchantment glow;
    
    public EnchantGlow(final int paramInt) {
        super(paramInt);
    }
    
    public boolean canEnchantItem(final ItemStack paramItemStack) {
        return true;
    }
    
    public boolean conflictsWith(final Enchantment paramEnchantment) {
        return false;
    }
    
    public EnchantmentTarget getItemTarget() {
        return null;
    }
    
    public int getMaxLevel() {
        return 10;
    }
    
    public String getName() {
        return "Glow";
    }
    
    public int getStartLevel() {
        return 1;
    }
    
    public static Enchantment getGlow() {
        if (EnchantGlow.glow != null) {
            return EnchantGlow.glow;
        }
        try {
            final Field localField = Enchantment.class.getDeclaredField("acceptingNew");
            localField.setAccessible(true);
            localField.set(null, true);
            Enchantment.registerEnchantment(EnchantGlow.glow = (Enchantment)new EnchantGlow(255));
        }
        catch (Exception ex) {}
        return EnchantGlow.glow;
    }
    
    public static void addGlow(final ItemStack paramItemStack) {
        paramItemStack.addEnchantment(getGlow(), 1);
    }
}
